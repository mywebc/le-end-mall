package com.chenxiaolani.mall.service;

import com.chenxiaolani.mall.model.pojo.Category;
import com.chenxiaolani.mall.model.request.AddCategoryReq;

/**
 * CategoryService
 */
public interface CategoryService {
    void add(AddCategoryReq addCategoryReq);

    void update(Category updateCategory);
}
