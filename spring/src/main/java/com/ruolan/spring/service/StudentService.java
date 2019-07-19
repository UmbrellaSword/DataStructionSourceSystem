package com.ruolan.spring.service;

import com.ruolan.spring.dao.StudentDao;
import com.ruolan.spring.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentService {
    @Autowired
    StudentDao studentDao;
    public boolean insertStudent(Student student){
        if(studentDao.insertStudent(student)>0)
            return true;
        else
            return false;
    }
    public boolean updateStudent(Student student){
        if(studentDao.updateStudent(student)>0)
            return true;
        else
            return false;
    }

    public boolean checkStudent(String id, String password) {
        if(studentDao.checkStudent(id,password)>0)
            return true;
        else return false;
    }

    public List<Student> selectStudentByteacher(String teaid) {
        return studentDao.selectStudentByteacher(teaid);
    }
}
