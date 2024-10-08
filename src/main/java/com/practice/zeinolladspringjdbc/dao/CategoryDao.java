package com.practice.zeinolladspringjdbc.dao;

import com.practice.zeinolladspringjdbc.model.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> findAll();
    Category findById(int id);
    Category create(Category category);
}

// controller -> service -> dao -> DB
// controller -> dao -> DB