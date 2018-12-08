package com.dky.controller;

import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {
    @GetMapping("/home")
    public String home() {
        return "hello home!";
    }

    @GetMapping("/user/info")
    public JSONObject getUserInfo(@RequestParam String token) {
        System.out.println(token);
        JSONObject result = new JSONObject();
        JSONObject obj = new JSONObject();
        result.put("roles","admin");
        result.put("name","hello");
        result.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        obj.put("data",result);
        obj.put("code",200);
        return obj;
    }


    @GetMapping("/user/test")
    public JSONObject getUserTest(HttpServletRequest request) {
        String header = request.getHeader("X-Token");
        System.out.println(header);
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        result.put("code",200);
        data.put("username","admin");
        List<String> list = new ArrayList<>();
        list.add("order1");
        list.add("order2");
        data.put("orderList",list);
        result.put("data", data);
        return result;
    }
}
