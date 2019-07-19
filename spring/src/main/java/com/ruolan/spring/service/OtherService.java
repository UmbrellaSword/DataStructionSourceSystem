package com.ruolan.spring.service;

import com.ruolan.spring.dao.OtherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OtherService {
    @Autowired
    OtherDao otherDao;
    public List<String> selectTypeOfType(){
        return otherDao.selectTypeOfCode();
    }
}
