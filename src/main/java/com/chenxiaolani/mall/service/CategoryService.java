package com.chenxiaolani.mall.service;

import com.chenxiaolani.mall.model.pojo.Category;
import com.chenxiaolani.mall.model.request.AddCategoryReq;
import com.chenxiaolani.mall.model.vo.CategoryVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * CategoryService
 */
public interface CategoryService {
    void add(AddCategoryReq addCategoryReq);

    void update(Category updateCategory);

    void delete(Integer id);

    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

}
