package com.kangyonggan.service.impl;

import com.kangyonggan.model.Category;
import com.kangyonggan.service.CategoryService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author kangyonggan
 * @since 2016/05/24
 */
@Service
@Transactional
public class CategoryServiceImpl extends BaseService<Category> implements CategoryService {

    @Override
    public List<Category> findAllCategories() {
        return super.select(new Category());
    }

    @Override
    public List<Category> findTreeCategories() {
        Category category = new Category();
        category.setIsDeleted((byte) 0);

        List<Category> categories = new ArrayList();
        recursionTreeList(super.select(category), categories, 0L);

        return categories;
    }

    @Override
    public Category getCategory(Long id) {
        return super.selectByPrimaryKey(id);
    }

    @Override
    public void saveCategory(Category category) {
        category.setCreatedTime(new Date());
        category.setUpdatedTime(new Date());

        super.insertSelective(category);
    }

    @Override
    public void updateCategory(Category category) {
        category.setUpdatedTime(new Date());

        super.updateByPrimaryKeySelective(category);
    }

    @Override
    public void deleteCategory(Long id) {
        super.deleteByPrimaryKey(id);
    }

    @Override
    public Category findCategoryByCode(String code) {
        Category category = new Category();
        category.setCode(code);

        return super.selectOne(category);
    }

    private List<Category> recursionTreeList(List<Category> from, List<Category> toList, Long parentId) {
        if (CollectionUtils.isEmpty(from)) {
            return null;
        }

        for (int i = 0; i < from.size(); i++) {
            Category category = from.get(i);
            if (parentId.equals(category.getPid())) {
                List<Category> childrens = new ArrayList<Category>();
                category.setChildrens(childrens);
                toList.add(category);
                recursionTreeList(from, childrens, category.getId());
            }
        }
        return toList;
    }
}
