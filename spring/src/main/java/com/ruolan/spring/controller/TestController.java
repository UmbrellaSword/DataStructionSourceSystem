package com.ruolan.spring.controller;

import com.ruolan.spring.pojo.Test;
import com.ruolan.spring.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("Test")
public class TestController {
    @Autowired
    TestService testService;
    @RequestMapping("/selectTest")
    @ResponseBody
    public List<Test> selectTest(HttpServletRequest httpServletRequest){
        String num = httpServletRequest.getParameter("num");
        String type = httpServletRequest.getParameter("inwhat");
        return testService.selectTest(Integer.parseInt(num),Integer.parseInt(type));
    }
}
