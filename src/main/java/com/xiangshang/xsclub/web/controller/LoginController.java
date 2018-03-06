package com.xiangshang.xsclub.web.controller;

import com.xiangshang.xsclub.db.model.User;
import com.xiangshang.xsclub.service.UserService;
import com.xiangshang.xsclub.web.auth.AssertAuth;
import com.xiangshang.xsclub.web.auth.AuthHandler;
import com.xiangshang.xsclub.web.auth.Authentication;
import com.xiangshang.xsclub.web.auth.SecurityContextHolder;
import com.xiangshang.xsclub.web.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
@RequestMapping("/account")
public class LoginController {
    @Autowired
    private AuthHandler authHandler;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public Result accountLogin(String mobile, String password, HttpServletRequest request, HttpServletResponse response) {
        User user = userService.getUserByMobile(mobile);
        userService.passwordValid(user, password);

        Authentication authentication = new Authentication(user.getId(), user.getName(), new Date());
        authHandler.loginSuccess(request, response, authentication);
        return Result.OK();
    }

    @AssertAuth
    @GetMapping("/info")
    public Result accountInfo() {
        Long userId = SecurityContextHolder.getContext().getUserId();
        User user = userService.getUserById(userId);
        return Result.OK(user);
    }

    @AssertAuth
    @GetMapping("/logout")
    public Result accountLogout(HttpServletRequest request, HttpServletResponse response) {
        authHandler.logout(request, response);
        return Result.OK();
    }
}
