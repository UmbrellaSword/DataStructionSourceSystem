package com.ruolan.spring.pojo;

import org.springframework.stereotype.Component;

@Component
public class Student {
    int id;//学号

    String name;//姓名

    String major;//专业

    String Email;//电子邮件

    String classroom;//班级

    String password;//密码

    String phone;//电话

    int teacher;//数据结构老师

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    int type;//身份类型

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getTeacher() {
        return teacher;
    }

    public void setTeacher(int teacher) {
        this.teacher = teacher;
    }




}
