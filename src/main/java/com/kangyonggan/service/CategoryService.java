package com.kangyonggan.service;

import com.kangyonggan.model.Category;

import java.util.List;

/**
 * @author kangyonggan
 * @since 16/6/25
 */
public interface CategoryService {

    List<Category> findAllCategories();

    List<Category> findTreeCategories();

    Category getCategory(Long id);

    void saveCategory(Category category);

    void updateCategory(Category category);

    void deleteCategory(Long id);

    Category findCategoryByCode(String code);
}
