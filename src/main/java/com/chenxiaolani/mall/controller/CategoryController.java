package com.chenxiaolani.mall.controller;

import com.chenxiaolani.mall.common.ApiRestResponse;
import com.chenxiaolani.mall.common.Constant;
import com.chenxiaolani.mall.exception.LeMallException;
import com.chenxiaolani.mall.exception.LeMallExceptionEnum;
import com.chenxiaolani.mall.model.pojo.User;
import com.chenxiaolani.mall.model.request.AddCategoryReq;
import com.chenxiaolani.mall.service.CategoryService;
import com.chenxiaolani.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * 目录controller
 */
@Controller
public class CategoryController {

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;


    @PostMapping("admin/category/add")
    @ResponseBody
    public ApiRestResponse addCategory(HttpSession session, @Valid @RequestBody AddCategoryReq addCategoryReq) {
//        if (addCategoryReq.getName() == null ||
//                addCategoryReq.getOrderNum() == null ||
//                addCategoryReq.getParentId() == null ||
//                addCategoryReq.getType() == null) {
//            return ApiRestResponse.error(LeMallExceptionEnum.PARAMS_NOT_NULL);
//        }
        User currentUser = (User) session.getAttribute(Constant.LE_MALL_USER);
        // 校验是否登录
        if (currentUser == null) {
            return ApiRestResponse.error(LeMallExceptionEnum.NEED_LOGIN);
        }
        // 校验是否是管理员
        boolean adminRole = userService.checkAdminRole(currentUser);
        if (adminRole) {
            // 执行操作
            categoryService.add(addCategoryReq);
            return ApiRestResponse.success();
        } else {
            return ApiRestResponse.error(LeMallExceptionEnum.NEED_ADMIN);
        }
    }
}
