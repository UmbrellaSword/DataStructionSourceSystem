package com.ruolan.spring.entity;

import org.springframework.stereotype.Component;

import java.sql.Date;
@Component
public class ExperimentWithScore {
    int id;//实验序号
    String name;//实验名称
    String summery;//实验简介
    String place;//实验地点

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

    public String getSummery() {
        return summery;
    }

    public void setSummery(String summery) {
        this.summery = summery;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    Date time;//实验时间
    String score;//实验成绩
}
