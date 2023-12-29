package session04demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import session04demo.entity.Category;
import session04demo.service.CategoryService;

import java.util.List;

@RestController
@CrossOrigin("+")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> categories() {
        List<Category> categories = categoryService.getAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    @PostMapping("/categories")
    public ResponseEntity<Category> create(@RequestBody Category category){
        Category categoryNew = categoryService.saveOrUpdate(category);
        return new ResponseEntity<>(categoryNew,HttpStatus.CREATED);
    }
    @GetMapping("/categories/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id){
        Category category = categoryService.findById(id);
        if (category != null){
            return new ResponseEntity<>(category,HttpStatus.OK);
        }
            return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);


    }
    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category){
        Category categoryUpdate = categoryService.findById(id);
        categoryUpdate.setCategoryName(category.getCategoryName());
        categoryUpdate.setStatus(categoryUpdate.isStatus());
        Category categoryNew = categoryService.saveOrUpdate(categoryUpdate);
        return new ResponseEntity<>(categoryNew,HttpStatus.OK);
    }
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
       if (categoryService.findById(id) != null){
           categoryService.deleteById(id);
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }else {
           return new ResponseEntity<>("{'mess':'Not Found'}",HttpStatus.NO_CONTENT);

       }
    }
}
