package com.chenxiaolani.mall.service;

import com.chenxiaolani.mall.exception.LeMallException;
import com.chenxiaolani.mall.model.pojo.User;

import java.security.NoSuchAlgorithmException;

/**
 * UserService
 */
public interface UserService {
    User getUser();

    void register(String username, String password) throws LeMallException, NoSuchAlgorithmException;

    User login(String username, String password) throws LeMallException;
}
