package com.practice.zeinolladspringjdbc.dao.impl;

import com.practice.zeinolladspringjdbc.dao.CategoryDao;
import com.practice.zeinolladspringjdbc.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryDaoImpl implements CategoryDao {
    private final JdbcTemplate jdbcTemplate;

    // .queryforrowset
    // query - select zapros

    /*@Override
    public List<Category> findAll() {
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("select * from categories");

        List<Category> categories = new ArrayList<>();
        while (sqlRowSet.next()) {
            int id = sqlRowSet.getInt("id");
            String name = sqlRowSet.getString("name");

            Category category = new Category(id, name);
            categories.add(category);
        }
        return categories;
    }*/

    /*@Override
    public List<Category> findAll() {
        String sql = "select * from categories";

        *//*RowMapper<Category> rowMapper = new RowMapper<Category>() {
            @Override
            public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                return new Category(id, name);
            }
        };*//*

        RowMapper<Category> rowMapper = (rs, rowNum) -> {
            int id = rs.getInt("id");
            String name = rs.getString("name");

            return new Category(id, name);
        };

        return jdbcTemplate.query(sql, rowMapper());
    }*/

    @Override
    public List<Category> findAll() {
        String sql = "select * from categories";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    @Override
    public Category findById(int id) {
//        return jdbcTemplate.queryForObject("select * from categories where id = ?", this::mapRow, id);
        return jdbcTemplate.queryForStream("select * from categories where id = ?", this::mapRow, id)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Не найдено")); // + обработать и поменять статус
    }

    @Override
    public Category create(Category category) {
        /*String sql = "insert into categories(name) values (?)";
        jdbcTemplate.update(sql, category.getName());
        return category;*/

        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("categories")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> map = Map.of("name", category.getName());

        int id = insert.executeAndReturnKey(map).intValue();
        category.setId(id);
        return category;
    }

    Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");

        return new Category(id, name);
    }

    /*private RowMapper<Category> rowMapper() {
        RowMapper<Category> rowMapper = (rs, rowNum) -> {
            int id = rs.getInt("id");
            String name = rs.getString("name");

            return new Category(id, name);
        };
        return rowMapper;
    }*/
}