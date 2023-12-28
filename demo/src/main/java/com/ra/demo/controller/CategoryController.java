package com.ra.demo.controller;

import com.ra.demo.model.entity.Category;
import com.ra.demo.model.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/category")
    public String category(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "category/index";
    }
    @GetMapping("/add-category")
    public String addCategory(Model model){
        Category category = new Category();
        model.addAttribute("category",category);
        return "category/add-category";
    }
    @PostMapping("/add-category")
    public String handleAddCategory(@ModelAttribute("category") Category category){
     categoryService.saveOrUpdate(category);
     return "redirect:/category";
    }
    @GetMapping("/edit-category/{id}")
    public String editCategory(@PathVariable("id") Long id,Model model){
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "category/edit-category";
    }
    @PostMapping("/edit-category")
    public String handleEdit(@ModelAttribute("category") Category category){
        categoryService.saveOrUpdate(category);
        return "redirect:/category";
    }
    @GetMapping("/delete/{id}")
    public String handleDelete(@PathVariable("id") Long id){
        categoryService.delete(id);
        return "redirect:/category";
    }
}
