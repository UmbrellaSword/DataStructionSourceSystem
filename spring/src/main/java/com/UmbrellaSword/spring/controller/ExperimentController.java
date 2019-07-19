package com.ruolan.spring.controller;

import com.ruolan.spring.entity.ExperimentWithScore;
import com.ruolan.spring.entity.TaskWithScore;
import com.ruolan.spring.pojo.Experiment;
import com.ruolan.spring.pojo.Student;
import com.ruolan.spring.service.ExperimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("Experiment")
public class ExperimentController {
    @Autowired
    ExperimentService experimentService;

    @RequestMapping("/selectExperiment")
    @ResponseBody
    public List<Experiment> selectExperiment(){
        return experimentService.selectExperiment();
    }
    @RequestMapping("/selectUpToDateExperiment")
    @ResponseBody
    public Experiment selectUpToDateExperiment(){
        return experimentService.selectUpToDateExperiment();
    }

    @RequestMapping("/selectMyExperiment")
    @ResponseBody
    public List<Experiment> selectMyExperiment(HttpServletRequest httpServletRequest){
        String id = httpServletRequest.getParameter("id");
        return experimentService.selectMyExperiment(id);
    }
    @RequestMapping("/insertExperiment")
    @ResponseBody
    public String insertExperiment(HttpServletRequest httpServletRequest){
        String name = httpServletRequest.getParameter("name");
        String summery = httpServletRequest.getParameter("summery");
        String place = httpServletRequest.getParameter("place");
        String time = httpServletRequest.getParameter("time");
        Experiment experiment = new Experiment();
        experiment.setName(name);
        experiment.setSummery(summery);
        experiment.setPlace(place);
        experiment.setTime(java.sql.Date.valueOf(time));
        return experimentService.insertExperiment(experiment)+"";
    }
    @RequestMapping("/deleteExperiment")
    @ResponseBody
    public String deleteExperiment(HttpServletRequest httpServletRequest){
        String name=httpServletRequest.getParameter("name");
        return experimentService.deleteExperiment(name)+"";
    }

    @RequestMapping("/insertMyExperiment")
    @ResponseBody
    public String insertMyExperiment(HttpServletRequest httpServletRequest){
        String name=httpServletRequest.getParameter("name");
        String Stu_id = httpServletRequest.getParameter("stu_id");
        return experimentService.insertMyExperiment(name,Stu_id)+"";
    }
    @RequestMapping("/deleteMyExperiment")
    @ResponseBody
    public String deleteMyExperiment(HttpServletRequest httpServletRequest){
        String name=httpServletRequest.getParameter("name");
        String Stu_id = httpServletRequest.getParameter("stu_id");
        return experimentService.deleteMyExperiment(Stu_id,name)+"";
    }

    @RequestMapping("/insertGrade")
    @ResponseBody
    public String insertGrade(HttpServletRequest httpServletRequest){
        String id = httpServletRequest.getParameter("id");
        String grade = httpServletRequest.getParameter("grade");
        String expid = httpServletRequest.getParameter("expid");
        return experimentService.insertGrade(id,grade,expid)+"";
    }
    @RequestMapping("/selectMyExperimentWithScore")
    @ResponseBody
    public List<ExperimentWithScore> selectMyExperimentWithScore(HttpServletRequest httpServletRequest) throws SQLException {
        String id = httpServletRequest.getParameter("id");
        return experimentService.selectMyExperimentWithScore(id);
    }
}
