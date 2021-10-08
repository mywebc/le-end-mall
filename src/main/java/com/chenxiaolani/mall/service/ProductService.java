package com.chenxiaolani.mall.service;

import com.chenxiaolani.mall.model.request.AddProductReq;
import org.springframework.stereotype.Service;

/**
 * ProductService
 */
@Service
public interface ProductService {

    void add(AddProductReq addProductReq);

}
