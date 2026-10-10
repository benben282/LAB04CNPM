package com.example.LAB04.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.LAB04.models.Category;
import com.example.LAB04.models.Product;
import com.example.LAB04.services.CategoryService;
import com.example.LAB04.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class HomeCtronller {

    private final CategoryService categoryService;
    private final ProductService productService;

    @GetMapping("/")
    public String index(@RequestParam(name = "categoryId", required = false) Long categoryId, Model model) {
        // 1. Lấy tất cả danh mục từ CSDL để hiển thị ra thanh bấm
        List<Category> categories = categoryService.getAllCategories();
        
        // 2. Lấy danh sách sản phẩm (Lọc theo danh mục nếu người dùng bấm chọn)
        List<Product> products = productService.getAllProducts();
        if (categoryId != null) {
            products = products.stream()
                    .filter(p -> p.getCategory() != null && p.getCategory().getId().equals(categoryId))
                    .collect(Collectors.toList());
        }

        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        model.addAttribute("selectedCategoryId", categoryId);

        return "index"; // File index.html giao diện trang chủ
    }
}