package com.ra.controller;

import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import com.ra.model.service.CategoryService;
import com.ra.model.service.ProductService;
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
    public String product(Model model){
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "product/product";
    }
    @GetMapping("/add-product")
    public String addProduct(Model model){
        Product product = new Product();
        List<Category> categories = categoryService.findAll();
        model.addAttribute("product", product);
        model.addAttribute("category",categories);
        return "product/add-product";
    }
    @PostMapping("/add-product")
    public String handleAddProduct(@ModelAttribute("product") Product product,  Model model){
        if (productService.saveOfUpdate(product)){
            return "redirect:/product";
        }
        return "redirect:add-product";
    }
    @GetMapping("/edit-product/{id}")
    public String editProduct(@PathVariable("id") Integer id,Model model){
        Product product = productService.findById(id);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("product",product);
        model.addAttribute("category",categories);
        return "product/edit-product";
    }
    @PostMapping("/edit-product")
    public String handleEdit(@ModelAttribute("product") Product product){
        productService.saveOfUpdate(product);
        return "redirect:/product";
    }
    @GetMapping("/ /{id}")
    public String delete(@PathVariable("id") Integer id){
        productService.delete(id);
        return "redirect:/product";
    }
}
