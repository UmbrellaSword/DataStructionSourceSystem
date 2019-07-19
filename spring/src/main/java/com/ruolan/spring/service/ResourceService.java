package com.ruolan.spring.service;

import com.ruolan.spring.dao.ResourceDao;
import com.ruolan.spring.dao.TeacherDao;
import com.ruolan.spring.pojo.Source;
import com.ruolan.spring.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {
    @Autowired
    ResourceDao resourceDao;
    @Autowired
    TeacherDao teacherDao;
    public boolean insertResource(String res_name,String teacher_id){
        if(resourceDao.insertResource(res_name,teacher_id)>0)
            return true;
        else
            return false;
    }

    public List<Source> selectResource(){
        List<Source> sourceList = resourceDao.selectSource();
        for(Source source : sourceList){
            source.setUpdate_teacher(teacherDao.selectTeacherName(source.getUpdate_teacher()));
        }
        return sourceList;
    }

    public boolean insertMyResource(String stuid,String resname) {
        if(resourceDao.insertMyResource(stuid,resourceDao.selectIdByNameOfResource(resname))>0)
            return true;
        else
            return false;
    }
    public List<Source> selectMyResource(String id){
        List<Source> sourceList = resourceDao.selectMySource(id);
        for(Source source : sourceList){
            source.setUpdate_teacher(teacherDao.selectTeacherName(source.getUpdate_teacher()));
        }
        return sourceList;
    }

    public List<Source> selectUptodateSource() {
        return resourceDao.selectUptodateSource();
    }
}
