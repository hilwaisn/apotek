package com.tugas5.apotek.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.tugas5.apotek.models.Category;
import com.tugas5.apotek.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public String category(Model model) {
        List<Category> category = categoryService.getAllCategory();
        model.addAttribute("category", category);
        return "category";
    }

    @GetMapping("/delete-category/{id}")
    public String deleteCategory(@PathVariable("id") Integer id) {
        categoryService.deleteById(id);
        return "redirect:/category";
    }

    @GetMapping("/add-category")
    public String addCategory(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "add-category";
    }

    @PostMapping("/save-category")
    public String saveCategory(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        return "redirect:/category";
    }

    @GetMapping("/update-category/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "update-category";
    }

    @PostMapping("/update-category/{id}")
    public String updateCategory(@PathVariable("id") Integer id, @ModelAttribute("category") Category category) {
        Category update = categoryService.findById(id);
        if (update != null) {
            update.setName(category.getName());
            update.setDescription(category.getDescription());
            categoryService.save(update);
        }
        return "redirect:/category";
    }
}