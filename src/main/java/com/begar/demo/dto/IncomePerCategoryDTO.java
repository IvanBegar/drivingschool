package com.begar.demo.dto;

public class IncomePerCategoryDTO {

    private String categoryName;
    private double incomePerCategory;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getIncomePerCategory() {
        return incomePerCategory;
    }

    public void setIncomePerCategory(double incomePerCategory) {
        this.incomePerCategory = incomePerCategory;
    }
}
