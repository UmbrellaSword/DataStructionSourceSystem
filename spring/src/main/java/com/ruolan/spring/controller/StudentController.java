package com.ruolan.spring.controller;

import com.google.gson.Gson;
import com.ruolan.spring.pojo.Student;
import com.ruolan.spring.service.StudentService;
import com.ruolan.spring.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("Student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;
    @RequestMapping("/insertStudent")
    @ResponseBody
    public String insertstudent(HttpServletRequest httpServletRequest){
        int id = Integer.parseInt(httpServletRequest.getParameter("id"));
        String name = httpServletRequest.getParameter("name");
        String major = httpServletRequest.getParameter("major");
        String email =  httpServletRequest.getParameter("email");
        String classroom = httpServletRequest.getParameter("classroom");
        String password = httpServletRequest.getParameter("password");
        String phone = httpServletRequest.getParameter("phone");
        int teacher = Integer.parseInt(httpServletRequest.getParameter("teacherselected"));
        Student student = new Student();
        student.setId(id);
        student.setClassroom(classroom);
        student.setEmail(email);
        student.setMajor(major);
        student.setName(name);
        student.setPassword(password);
        student.setPhone(phone);
        student.setTeacher(teacher);
        student.setType(2);
        System.out.println(student.toString());
        return studentService.insertStudent(student)+"";
    }

    @RequestMapping("/updateStudent")
    @ResponseBody
    public String updatestudent(Student student){
        return studentService.updateStudent(student)+"";
    }

    @RequestMapping("/checkRole")
    @ResponseBody
    public Map<String,String> checkStudent(String id,String password){
        Map<String,String> map = new HashMap<>();
        if(studentService.checkStudent(id,password))
        {
            map.put("code","2");
            map.put("role","student");
            return map;
        }else if(teacherService.checkTeacher(id,password)){
            map.put("code","1");
            map.put("role","teacher");
            return map;
        }else{
            map.put("code","0");
            map.put("role",null);
            return map;
        }
    }

    @RequestMapping("/selectStudentByteacher")
    @ResponseBody
    public List<Student> selectStudentByteacher(HttpServletRequest httpServletRequest){
        String id = httpServletRequest.getParameter("id");
        return studentService.selectStudentByteacher(id);
    }

}
