package com.ruolan.spring.controller;

import com.ruolan.spring.service.OtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("Other")
public class OtherController {
    @Autowired
    OtherService otherService;
    @RequestMapping("/selectTypeOfCode")
    @ResponseBody
    public List<String> selectTypeOfCode(){
        return otherService.selectTypeOfType();
    }
}
