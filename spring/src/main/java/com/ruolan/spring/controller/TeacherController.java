package com.ruolan.spring.controller;

import com.ruolan.spring.pojo.Teacher;
import com.ruolan.spring.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("Teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    @RequestMapping("/insertTeacher")
    @ResponseBody
    public String insertTeacher(Teacher teacher){
        return teacherService.insertTeacher(teacher)+"";
    }

    @RequestMapping("/selectTeacher")
    @ResponseBody
    public List<Teacher> selectTeacher(){
        return teacherService.selectTeacher();
    }

    @RequestMapping("/selectMyTeacher")
    @ResponseBody
    public Teacher selectMyTeacher(HttpServletRequest httpServletRequest){
        String id = httpServletRequest.getParameter("id");
        return  teacherService.selectMyTeacher(id);
    }
}
