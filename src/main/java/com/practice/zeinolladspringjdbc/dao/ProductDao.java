package com.practice.zeinolladspringjdbc.dao;

import com.practice.zeinolladspringjdbc.model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> findAll();
    Product findById(int id);
    Product create(Product category);
}
