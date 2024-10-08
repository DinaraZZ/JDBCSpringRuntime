package com.practice.zeinolladspringjdbc.controller;

import com.practice.zeinolladspringjdbc.dao.CategoryDao;
import com.practice.zeinolladspringjdbc.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryDao categoryDao;

    @GetMapping
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @GetMapping("/{id}")
    public Category findById(@PathVariable int id) {
        return categoryDao.findById(id);
    }

    @PostMapping
    public Category create(@RequestBody Category category) {
        return categoryDao.create(category);
    }
}
