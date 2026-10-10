package com.example.LAB04.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.LAB04.models.Category;
import com.example.LAB04.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;

    // 1. Lấy toàn bộ danh mục
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // 2. Tìm danh mục theo ID
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    // 3. Thêm một danh mục
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    // 4. Cập nhật danh mục
    public void updateCategory(Category category) {
        Category existingCategory = categoryRepository.findById(category.getId())
                .orElseThrow(() -> new IllegalStateException("Danh mục ID " + category.getId() + " không tồn tại."));
        existingCategory.setName(category.getName());
        categoryRepository.save(existingCategory);
    }

    // 5. Xóa danh mục theo ID
    public void deleteCategoryById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new IllegalStateException("Danh mục ID " + id + " không tồn tại.");
        }
        categoryRepository.deleteById(id);
    }
}