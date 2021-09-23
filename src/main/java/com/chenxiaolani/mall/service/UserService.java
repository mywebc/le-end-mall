package com.chenxiaolani.mall.service;

import com.chenxiaolani.mall.exception.LeMallException;
import com.chenxiaolani.mall.model.pojo.User;

/**
 * UserService
 */
public interface UserService {
    User getUser();

    void register(String username, String password) throws LeMallException;
}
