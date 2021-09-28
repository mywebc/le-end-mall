package com.chenxiaolani.mall.filter;

import com.chenxiaolani.mall.common.ApiRestResponse;
import com.chenxiaolani.mall.common.Constant;
import com.chenxiaolani.mall.exception.LeMallExceptionEnum;
import com.chenxiaolani.mall.model.pojo.Category;
import com.chenxiaolani.mall.model.pojo.User;
import com.chenxiaolani.mall.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class AdminFilter implements Filter {

    @Autowired
    UserService userService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute(Constant.LE_MALL_USER);
        // 校验是否登录
        if (currentUser == null) {
            PrintWriter out = new HttpServletResponseWrapper((HttpServletResponse) servletResponse).getWriter();
            out.write("{\n" +
                    "    \"status\": 1007,\n" +
                    "    \"msg\": \"NEED_LOGIN\",\n" +
                    "    \"data\": null\n" +
                    "}");
            out.flush();
            out.close();
            return;
        }
        // 校验是否是管理员
        boolean adminRole = userService.checkAdminRole(currentUser);
        if (adminRole) {
            // 执行下一步操作
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            PrintWriter out = new HttpServletResponseWrapper((HttpServletResponse) servletResponse).getWriter();
            out.write("{\n" +
                    "    \"status\": 1009,\n" +
                    "    \"msg\": \"NEED_ADMIN\",\n" +
                    "    \"data\": null\n" +
                    "}");
            out.flush();
            out.close();
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
