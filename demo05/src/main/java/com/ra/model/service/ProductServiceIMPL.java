package com.ra.model.service;

import com.ra.model.dao.ProductDao;
import com.ra.model.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceIMPL implements ProductService{
    @Autowired
    private ProductDao productDao;
    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public boolean saveOfUpdate(Product product) {
        return productDao.saveOfUpdate(product);
    }

    @Override
    public void delete(Integer id) {
        productDao.delete(id);
    }

    @Override
    public Product findById(Integer id) {
        return productDao.findById(id);
    }
}
