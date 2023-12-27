package com.ra.model.dao;

import com.ra.model.entity.Category;
import com.ra.model.entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> findAll();
    boolean saveOfUpdate(Product product);
    void delete(Integer id);
    Product findById(Integer id);
}
