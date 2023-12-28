package com.ra.demo.controller;

import com.ra.demo.model.entity.Category;
import com.ra.demo.model.entity.Product;
import com.ra.demo.model.service.CategoryService;
import com.ra.demo.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/product")
    public String index(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "/product/index";
    }
    @GetMapping("/add-product")
    public String addProduct(Model model){
        Product product = new Product();
        List<Category> categories = categoryService.findAll();
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "/product/add-product";
    }
    @PostMapping("/add-product")
    public String HandleAddProduct(@ModelAttribute("product") Product product){
        productService.saveOrUpdate(product);
        return "redirect:/product";
    }
    @GetMapping("/edit-product/{id}")
    public String editProduct(@PathVariable("id")Long id,Model model){
       Product product =  productService.findById(id);
       List<Category> categories = categoryService.findAll();
       model.addAttribute("categories",categories);
        model.addAttribute("product",product);
        return "/product/edit-product";
    }
    @PostMapping("/edit-product")
    public String editProduct(@ModelAttribute("product") Product product){
        productService.saveOrUpdate(product);
        return "redirect:/product";
    }
    @GetMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        productService.delete(id);
        return "redirect:/product";
    }
}
