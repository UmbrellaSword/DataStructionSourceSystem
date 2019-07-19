package com.ruolan.spring.test;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Example {
    @RequestMapping(value="/home",method = RequestMethod.GET)
    public String home (){
        return "/layui/WEB/main";
    }
    @RequestMapping(value="/experiment",method = RequestMethod.GET)
    public String experiment (){
        return "/layui/WEB/experiment";
    }

}
