package com.kangyonggan.service;

import com.kangyonggan.model.Category;

import java.util.List;

/**
 * @author kangyonggan
 * @since 16/6/25
 */
public interface CategoryService {

    List<Category> findAllCategories();

    Category getCategory(Long id);

    Category findCategoryByCode(String code);

    Category findTreeCategory();

    void saveCategory(Category category);

    void updateCategory(Category category);

    void deleteCategory(Long id);
}
