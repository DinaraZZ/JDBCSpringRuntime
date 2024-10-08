package com.practice.zeinolladspringjdbc.dao.impl;

import com.practice.zeinolladspringjdbc.dao.CategoryDao;
import com.practice.zeinolladspringjdbc.dao.ProductDao;
import com.practice.zeinolladspringjdbc.model.Category;
import com.practice.zeinolladspringjdbc.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {
    private final JdbcTemplate jdbcTemplate;
    private final CategoryDao categoryDao;

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query("select * from products", this::mapRow);
    }

    @Override
    public Product findById(int id) {
        return jdbcTemplate.queryForStream("select * from products where id = ?", this::mapRow, id)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Не найдено"));
    }

    @Override
    public Product create(Product product) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("products")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> map = Map.of("name", product.getName(),
                "price", product.getPrice(),
                "category_id", product.getCategory().getId());

        int id = insert.executeAndReturnKey(map).intValue();
        product.setId(id);
        return product;
    }

    Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        double price = rs.getDouble("price");
        int categoryId = rs.getInt("category_id");

        Category category = categoryDao.findById(categoryId);

        return new Product(id, name, price, category);
    }
}
