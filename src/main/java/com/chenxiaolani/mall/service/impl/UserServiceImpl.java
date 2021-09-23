package com.chenxiaolani.mall.service.impl;

import com.chenxiaolani.mall.exception.LeMallException;
import com.chenxiaolani.mall.exception.LeMallExceptionEnum;
import com.chenxiaolani.mall.model.dao.UserMapper;
import com.chenxiaolani.mall.model.pojo.User;
import com.chenxiaolani.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserServiceImpl
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUser() {
        return userMapper.selectByPrimaryKey(1);
    }

    @Override
    public void register(String username, String password) throws LeMallException {
        // 先查询是否重名
        User result = userMapper.selectByName(username);
        if (result != null) {
            throw new LeMallException(LeMallExceptionEnum.NAME_EXISTED);
        }
        // 写到数据库
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        int count = userMapper.insertSelective(user);
        if (count == 0) {
            throw new LeMallException(LeMallExceptionEnum.INSERT_FAILED);
        }
    }
}
