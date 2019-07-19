package com.ruolan.spring.service;

import com.ruolan.spring.dao.TeacherDao;
import com.ruolan.spring.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    TeacherDao teacherDao;
    public boolean insertTeacher(Teacher teacher){
        if(teacherDao.insertTeacher(teacher)>0)
            return true;
        else
            return false;
    }

    public List<Teacher> selectTeacher(){
        return teacherDao.selectTeacher();
    }

    public boolean checkTeacher(String id, String password) {
        if(teacherDao.checkTeacher(id,password)>0)
            return true;
        else return false;
    }

    public Teacher selectMyTeacher(String id) {
        return teacherDao.selectMyTeacher(id);
    }
}
