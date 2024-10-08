package com.practice.zeinolladspringjdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private int id;
    private String name;
    private Double price;
    private Category category;
}
