package com.ruolan.spring.service;

import com.ruolan.spring.dao.ExperimentDao;
import com.ruolan.spring.entity.ExperimentWithScore;
import com.ruolan.spring.entity.TaskWithScore;
import com.ruolan.spring.pojo.Experiment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class ExperimentService {
    @Autowired
    ExperimentDao experimentDao;

    public List<Experiment> selectExperiment(){
        return experimentDao.selectExperiment();
    }

    public List<Experiment> selectMyExperiment(String id){
        return experimentDao.selectMyExperiment(id);
    }

    public boolean insertExperiment(Experiment experiment){
        if(experimentDao.insertExperiment(experiment)>0)
            return true;
        else
            return false;
    }

    public boolean deleteExperiment(String name){
        if(experimentDao.deleteExperiment(experimentDao.selectIdByName(name))>0)
            return true;
        else
            return false;
    }

    public Experiment selectUpToDateExperiment() {
        return experimentDao.selectUpToDateExperiment();
    }

    public boolean insertMyExperiment(String name,String Stu_id){
        if(experimentDao.insertMyExperiment(experimentDao.selectIdByName(name),Stu_id)>0){
            return true;
        }
        else return false;
    }

    public boolean deleteMyExperiment(String stuid,String name){
        String expid=experimentDao.selectIdByName(name);
        if(experimentDao.deleteMyExperiment(expid,stuid)>0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean insertGrade(String id,String grade,String expid) {
        if(experimentDao.insertGrade(id,grade,expid)>0)
            return true;
        else return false;
    }

    public List<ExperimentWithScore> selectMyExperimentWithScore(String id) {
        return experimentDao.selectMyExperimentWithScore(id);
    }
}
