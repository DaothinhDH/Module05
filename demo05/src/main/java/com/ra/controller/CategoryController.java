package com.ra.controller;

import com.ra.model.entity.Category;
import com.ra.model.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public String index(Model model) {
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("list", categoryList);
        return "category/index";
    }
    @GetMapping("/add-category")
    public String addCategory(Model model){
        Category category =  new Category();
        model.addAttribute("category", category);
        return "category/add";
    }
    @PostMapping("/add-category")
    public String handelAdd(@ModelAttribute("category") Category category){
        categoryService.saveOfUpdate(category);
        return "redirect:/";
    }
    @GetMapping("/edit-category/{id}")
    public String edit(@PathVariable("id")Integer id ,Model model){
        Category category = categoryService.findById(id);
        model.addAttribute("category",category);
        return "category/edit";
    }
    @PostMapping("/edit-category")
    public String handleEdit(@ModelAttribute("category") Category category){
        categoryService.saveOfUpdate(category);
        return "redirect:/";
    }
    @GetMapping("/delete-category/{id}")
    public String delete(@PathVariable("id") Integer id){
        categoryService.delete(id);
        return "redirect:/";
    }
}
