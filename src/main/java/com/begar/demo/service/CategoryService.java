package com.begar.demo.service;

import com.begar.demo.entity.Category;
import com.begar.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.getCategories();
    }

    public Category getCategory(int id) {
        return categoryRepository.getCategory(id);
    }

    public void addCategory(Category category) {
        categoryRepository.addCategory(category);
    }

    public String updateCategory(Category category) {
        String result = "";
        if (categoryRepository.getCategory(category.getCategory_id()).getCategory_id() == 0) {
            result = "Category don`t exist!";
        } else {
            categoryRepository.updateCategory(category);
            result = "Category successfully updated";
        }
        return result;
    }

    public void deleteCategory(int id) {
        categoryRepository.deleteCategory(id);
    }
}
