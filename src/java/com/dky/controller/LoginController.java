package com.dky.controller;


import net.sf.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录controller
 */
@RestController
public class LoginController {
    @PostMapping("/user/logout")
    public JSONObject logout(HttpServletRequest request, HttpServletResponse response) {
        JSONObject message = new JSONObject();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request,response, authentication);
        }
        message.put("code", 200);
        return message;
    }
}
