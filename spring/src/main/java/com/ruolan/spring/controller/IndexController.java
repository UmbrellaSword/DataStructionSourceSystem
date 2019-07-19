package com.ruolan.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class IndexController {
    @GetMapping
    @ResponseBody
    public void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("layui/WEB/login.html");
    }
}
