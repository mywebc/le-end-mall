package com.chenxiaolani.mall.controller;

import com.chenxiaolani.mall.common.ApiRestResponse;
import com.chenxiaolani.mall.exception.LeMallException;
import com.chenxiaolani.mall.exception.LeMallExceptionEnum;
import com.chenxiaolani.mall.model.pojo.User;
import com.chenxiaolani.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.security.krb5.internal.APOptions;

import java.security.NoSuchAlgorithmException;

/**
 * 用户控制器
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/test")
    @ResponseBody
    public User personalPage() {
        return userService.getUser();
    }

    @PostMapping("/register")
    @ResponseBody
    public ApiRestResponse register(@RequestParam("username") String username, @RequestParam("password") String password) throws LeMallException, NoSuchAlgorithmException {
        if (StringUtils.isEmpty(username)) {
            return ApiRestResponse.error(LeMallExceptionEnum.NEED_USER_NAME);
        }
        if (StringUtils.isEmpty(password)) {
            return ApiRestResponse.error(LeMallExceptionEnum.NEED_PASSWORD);
        }
        if (password.length() < 8) {
            return ApiRestResponse.error(LeMallExceptionEnum.NEED_PASSWORD_TOO_SHORT);
        }

        userService.register(username, password);
        return ApiRestResponse.success();
    }

}
