package com.begar.demo.controller;

import com.begar.demo.entity.Category;
import com.begar.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/categories")
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/categories/{id}")
    public Category getCategory(@PathVariable int id) {
        return categoryService.getCategory(id);
    }

    @PostMapping("/categories")
    public void addCategory(@RequestBody Category category) {
        categoryService.addCategory(category);
    }

    @PutMapping("/categories")
    public String updateCategory(@RequestBody Category category) {
        return categoryService.updateCategory(category);
    }

    @PatchMapping("/categories/{id}")
    public void patchUpdate(@PathVariable int id, @RequestBody Map<Object, Object> fields) {
        Category category = categoryService.getCategory(id);
        fields.forEach((k,v) -> {
            Field field = ReflectionUtils.findField(Category.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, category, v);
        });
        categoryService.updateCategory(category);
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
    }
}
