package com.chenxiaolani.mall.service.impl;

import com.chenxiaolani.mall.exception.LeMallException;
import com.chenxiaolani.mall.exception.LeMallExceptionEnum;
import com.chenxiaolani.mall.model.dao.ProductMapper;
import com.chenxiaolani.mall.model.pojo.Product;
import com.chenxiaolani.mall.model.request.AddProductReq;
import com.chenxiaolani.mall.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Override
    public void add(AddProductReq addProductReq) {
        Product product = new Product();
        // 将传进来的参数自动赋值
        BeanUtils.copyProperties(addProductReq, product);
        Product productOld = productMapper.selectByName(addProductReq.getName());
        if (productOld != null) {
            throw new LeMallException(LeMallExceptionEnum.NAME_EXISTED);
        }
        int count = productMapper.insertSelective(product);
        if (count == 0) {
            throw new LeMallException(LeMallExceptionEnum.CREATE_FAILED);
        }
    }
}
