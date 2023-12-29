package session04demo.service;

import session04demo.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    Category saveOrUpdate(Category category);
    Category findById(Long id);
    void deleteById(Long id);
}
