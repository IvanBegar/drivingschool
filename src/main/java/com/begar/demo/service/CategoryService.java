package com.begar.demo.service;

import com.begar.demo.entity.Category;
import com.begar.demo.exception.DataException;
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

    public void updateCategory(Category category) {
        if (categoryRepository.getCategory(category.getCategory_id()).getCategory_id() == 0) {
            throw new DataException("Category don`t exist!");
        } else {
            categoryRepository.updateCategory(category);
        }
    }

    public void deleteCategory(int id) {
        categoryRepository.deleteCategory(id);
    }
}
