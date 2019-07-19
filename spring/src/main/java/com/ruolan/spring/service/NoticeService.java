package com.ruolan.spring.service;


import com.ruolan.spring.dao.NoticeDao;
import com.ruolan.spring.dao.StudentDao;
import com.ruolan.spring.dao.TeacherDao;
import com.ruolan.spring.pojo.Notice;
import com.ruolan.spring.pojo.Student;
import com.ruolan.spring.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeService {
    @Autowired
    NoticeDao noticeDao;
    @Autowired
    TeacherDao teacherDao;
    @Autowired
    StudentDao studentDao;
    public String ifnew(String Stuid) throws SQLException {
        if(noticeDao.ifnew(Stuid)>0)
            return "是";
        else
            return "否";
    }

    public List<Notice> selectNotice(String userid, String type) {
        List<Notice> noticeList = noticeDao.selectNotice(userid,type);
        List<Notice> noticeList2=new ArrayList<>();
        for(Notice notice:noticeList){
            notice.setFaqiren(teacherDao.selectTeacherName(notice.getFaqiren()));
            noticeList2.add(notice);
        }
        return noticeList2;
    }

    public boolean UpdatenoticereadyAll(String userid, String type) {
        if(noticeDao.UpdatenoticereadyAll(userid,type)>0)
            return true;
        else
            return false;
    }

    public boolean Updatenotice(String noticeid) {
        if(noticeDao.Updatenotice(noticeid)>0)
            return true;
        else
            return false;
    }

    public boolean DelNotice(int noticeid) {
        if(noticeDao.DelNotice(noticeid)>0)
            return true;
        else
            return false;
    }

    public boolean insertNotice(String id,String topic, String type, String stuid, String content) {
        if(noticeDao.insertNotice(studentDao.selectStudentByteacher(id),id,topic,type,stuid,content)>0)
            return true;
        else
            return false;
    }
}
