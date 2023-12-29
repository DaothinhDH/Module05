package session04demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import session04demo.entity.Category;
import session04demo.respository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceIMPL implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category saveOrUpdate(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(Long id) {
        Optional <Category> categoryOptional = categoryRepository.findById(id);
        return categoryOptional.orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
