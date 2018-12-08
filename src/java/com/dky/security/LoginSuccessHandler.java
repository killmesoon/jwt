package com.dky.security;

import com.dky.jwt.JWTDemo;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义成功处理类
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        logger.info("登录成功");
        JSONObject message = new JSONObject();
        JSONObject obj = new JSONObject();httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        JWTDemo jwtDemo = new JWTDemo();
        String token = jwtDemo.createTokenWithClaim();
        obj.put("token",token); //这里生成token 使用jwt
        message.put("data",obj);
        message.put("code",200);
        httpServletResponse.getWriter().print(message);
    }
}
