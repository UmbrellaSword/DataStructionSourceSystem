package com.ruolan.spring.pojo;

import java.sql.Date;

public class Notice {
    int noticeid;

    public int getNoticeid() {
        return noticeid;
    }

    public void setNoticeid(int noticeid) {
        this.noticeid = noticeid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getFaqiren() {
        return faqiren;
    }

    public void setFaqiren(String faqiren) {
        this.faqiren = faqiren;
    }

    public String getJieshouren() {
        return jieshouren;
    }

    public void setJieshouren(String jieshouren) {
        this.jieshouren = jieshouren;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNoticetext() {
        return noticetext;
    }

    public void setNoticetext(String noticetext) {
        this.noticetext = noticetext;
    }

    public String getIfnew() {
        return ifnew;
    }

    public void setIfnew(String ifnew) {
        this.ifnew = ifnew;
    }

    String type;
    Date time;
    String faqiren;
    String jieshouren;
    String title;
    String noticetext;
    String ifnew;
}
