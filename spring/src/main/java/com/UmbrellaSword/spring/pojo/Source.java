package com.ruolan.spring.pojo;

import java.util.Date;

public class Source {
    int id;//资源序号
    int type;//资源类型
    String name;//资源名称
    String update_teacher;//上传教师
    int download_time;//下载次数
    Date update_time;//上传时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdate_teacher() {
        return update_teacher;
    }

    public void setUpdate_teacher(String update_teacher) {
        this.update_teacher = update_teacher;
    }

    public int getDownload_time() {
        return download_time;
    }

    public void setDownload_time(int download_time) {
        this.download_time = download_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
