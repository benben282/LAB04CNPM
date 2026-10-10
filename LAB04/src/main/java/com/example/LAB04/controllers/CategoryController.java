package com.example.LAB04.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.LAB04.models.Category;
import com.example.LAB04.services.CategoryService;

import java.util.List;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    // 1. Hiển thị danh sách danh mục
    @GetMapping
    public String listCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "categories/categories-list";
    }

    // 2. Hiển thị Form thêm mới
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("category", new Category());
        return "categories/add-category";
    }

    // 3. Luu Form thêm mới
    @PostMapping("/add")
    public String addCategory(@Valid @ModelAttribute("category") Category category, BindingResult result) {
        if (result.hasErrors()) {
            return "categories/add-category";
        }
        categoryService.addCategory(category);
        return "redirect:/categories";
    }

    // 4. Hiển thị Form cập nhật (Sửa)
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Category category = categoryService.getCategoryById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID danh mục không hợp lệ: " + id));
        model.addAttribute("category", category);
        return "categories/update-category";
    }

    // 5. Lưu Form cập nhật
    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable("id") Long id, @Valid @ModelAttribute("category") Category category, BindingResult result) {
        if (result.hasErrors()) {
            category.setId(id);
            return "categories/update-category";
        }
        categoryService.updateCategory(category);
        return "redirect:/categories";
    }

    // 6. Xóa danh mục
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategoryById(id);
        return "redirect:/categories";
    }
}