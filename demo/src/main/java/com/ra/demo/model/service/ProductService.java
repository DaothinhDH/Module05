package com.ra.demo.model.service;

import com.ra.demo.model.entity.Category;
import com.ra.demo.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product saveOrUpdate(Product product);
    Product findById(Long id);
    void delete(Long id);
}
