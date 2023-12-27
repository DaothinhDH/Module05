package com.ra.model.dao;

import com.ra.model.entity.Category;

import java.util.List;
import java.util.Locale;

public interface CategoryDAO {
    List<Category> findAll();
    boolean saveOfUpdate(Category category);
    void delete(Integer id);
    Category findById(Integer id);

}
