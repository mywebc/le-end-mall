package com.chenxiaolani.mall.controller;

import com.chenxiaolani.mall.common.ApiRestResponse;
import com.chenxiaolani.mall.common.Constant;
import com.chenxiaolani.mall.exception.LeMallExceptionEnum;
import com.chenxiaolani.mall.model.pojo.Category;
import com.chenxiaolani.mall.model.pojo.User;
import com.chenxiaolani.mall.model.request.AddCategoryReq;
import com.chenxiaolani.mall.model.request.UpdateCategoryReq;
import com.chenxiaolani.mall.service.CategoryService;
import com.chenxiaolani.mall.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("后台增加目录")
    @PostMapping("admin/category/add")
    @ResponseBody
    public ApiRestResponse addCategory(HttpSession session, @Valid @RequestBody AddCategoryReq addCategoryReq) {
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

    @ApiOperation("后台更新目录")
    @PostMapping("admin/category/update")
    @ResponseBody
    public ApiRestResponse updateCategory(HttpSession session, @Valid @RequestBody UpdateCategoryReq updateCategoryReq) {
        User currentUser = (User) session.getAttribute(Constant.LE_MALL_USER);
        // 校验是否登录
        if (currentUser == null) {
            return ApiRestResponse.error(LeMallExceptionEnum.NEED_LOGIN);
        }
        // 校验是否是管理员
        boolean adminRole = userService.checkAdminRole(currentUser);
        if (adminRole) {
            // 执行操作
            Category category = new Category();
            // 拷贝属性
            BeanUtils.copyProperties(updateCategoryReq, category);
            categoryService.update(category);
            return ApiRestResponse.success();
        } else {
            return ApiRestResponse.error(LeMallExceptionEnum.NEED_ADMIN);
        }
    }

    @ApiOperation("后台删除目录")
    @PostMapping("admin/category/delete")
    @ResponseBody
    public ApiRestResponse deleteCategory(@RequestParam Integer id) {
        categoryService.delete(id);
        return ApiRestResponse.success();
    }

    @ApiOperation("后台目录列表")
    @GetMapping("admin/category/list")
    @ResponseBody
    public ApiRestResponse listCategoryForAdmin(@RequestParam Integer pageNum,@RequestParam Integer pageSize) {
        PageInfo pageInfo = categoryService.listForAdmin(pageNum, pageSize);
        return ApiRestResponse.success(pageInfo);
    }

}
