package com.ra.model.service;

import com.ra.model.entity.Category;
import com.ra.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    boolean saveOfUpdate(Product product);
    void delete(Integer id);
    Product findById(Integer id);
}
