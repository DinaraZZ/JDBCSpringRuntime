package com.practice.zeinolladspringjdbc.controller;

import com.practice.zeinolladspringjdbc.dao.ProductDao;
import com.practice.zeinolladspringjdbc.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductDao productDao;

    @GetMapping
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable int id) {
        return productDao.findById(id);
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return productDao.create(product);
    }
}
