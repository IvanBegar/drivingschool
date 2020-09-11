package com.begar.demo.service;

import com.begar.demo.entity.Category;
import com.begar.demo.exception.NoDataException;
import com.begar.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategory(int id) {
        return categoryRepository.findById(id).orElseThrow(() -> new NoDataException("Category with id: " + id + " don't exist!"));
    }

    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public void updateCategory(Category category) {
        Category existingCategory = categoryRepository.findById(category.getCategory_id()).orElseThrow(
                () -> new NoDataException("Category with id: " + category.getCategory_id() + " don't exist!"));
        existingCategory.setName(category.getName());
        existingCategory.setStudyTime(category.getStudyTime());
        existingCategory.setPayment(category.getPayment());
        categoryRepository.save(existingCategory);
    }

    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }
}
