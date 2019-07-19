package com.ruolan.spring.service;

import com.ruolan.spring.dao.TestDao;
import com.ruolan.spring.pojo.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    @Autowired
    TestDao testDao;
    public List<Test> selectTest(int num,int type){
        return testDao.selectTest(num,type);
    }
}
