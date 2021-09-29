package com.chenxiaolani.mall.service.impl;

import com.chenxiaolani.mall.exception.LeMallException;
import com.chenxiaolani.mall.exception.LeMallExceptionEnum;
import com.chenxiaolani.mall.model.dao.CategoryMapper;
import com.chenxiaolani.mall.model.pojo.Category;
import com.chenxiaolani.mall.model.request.AddCategoryReq;
import com.chenxiaolani.mall.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CategoryServiceImpl
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public void add(AddCategoryReq addCategoryReq) {
        Category category = new Category();
        // 使用此方法可以自动拷贝，不用一个一个set
        BeanUtils.copyProperties(addCategoryReq, category);
        // 通过名字查找
        Category CategoryOld = categoryMapper.selectByName(addCategoryReq.getName());
        if (CategoryOld != null) {
            throw new LeMallException(LeMallExceptionEnum.NAME_EXISTED);
        }
        int count = categoryMapper.insertSelective(category);
        if (count == 0) {
            throw new LeMallException(LeMallExceptionEnum.CATEGORY_FAILED);
        }
    }

    @Override
    public void update(Category updateCategory) {
        if (updateCategory.getName() != null) {
            Category categoryOld = categoryMapper.selectByName(updateCategory.getName());
            if (categoryOld != null && !categoryOld.getId().equals(updateCategory.getId())) {
                // 根据名字查找出来的category, 并且id还不一样的话，说明重名了
                throw new LeMallException(LeMallExceptionEnum.NAME_EXISTED);
            }
            // 执行更新
            int count = categoryMapper.updateByPrimaryKeySelective(updateCategory);
            if (count == 0) {
                throw new LeMallException(LeMallExceptionEnum.UPDATE_FAILED);
            }
        }
    }

    @Override
    public void delete(Integer id) {
        Category categoryOld = categoryMapper.selectByPrimaryKey(id);
        if (categoryOld == null) {
            throw new LeMallException(LeMallExceptionEnum.DELETE_FAILED);
        }
        int count = categoryMapper.deleteByPrimaryKey(id);
        if (count == 0) {
            throw new LeMallException(LeMallExceptionEnum.DELETE_FAILED);
        }
    }


}
