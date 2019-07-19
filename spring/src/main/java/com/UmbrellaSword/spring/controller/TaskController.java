package com.ruolan.spring.controller;

import com.ruolan.spring.entity.TaskWithScore;
import com.ruolan.spring.pojo.Task;
import com.ruolan.spring.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("Task")
public class TaskController {

    @Autowired
    TaskService taskService;
    @RequestMapping("/selectTaskByTeacher")
    @ResponseBody
    public List<Task> selectTaskByTeacher(HttpServletRequest httpServletRequest) throws SQLException {
        String id = httpServletRequest.getParameter("id");
        return taskService.selectTaskByTeacher(id);
    }

    @RequestMapping("/selectTodayTask")
    @ResponseBody
    public List<Task> selectTodayTask(){
        return taskService.selectTodayTask();
    }

    @RequestMapping("/insertTask")
    @ResponseBody
    public String insertTask(HttpServletRequest httpServletRequest){
        String id=httpServletRequest.getParameter("id");
        String content = httpServletRequest.getParameter("content");
        String time_out = httpServletRequest.getParameter("time_out");
        String time_in = httpServletRequest.getParameter("time_in");
        Task task = new Task();
        task.setTeacher_id(Integer.parseInt(id));
        task.setContent(content);
        task.setTime_out(java.sql.Date.valueOf(time_out));
        task.setTime_in(java.sql.Date.valueOf(time_in));
        return taskService.insertTask(task)+"";
    }

    @RequestMapping("/selectMyTaskWithScore")
    @ResponseBody
    public List<TaskWithScore> selectMyTaskWithScore(HttpServletRequest httpServletRequest) throws SQLException {
        String id = httpServletRequest.getParameter("id");
        return taskService.selectMyTaskWithScore(id);
    }
    @RequestMapping("/insertTaskRelation")
    @ResponseBody
    public String insertTaskRelation(HttpServletRequest httpServletRequest) throws SQLException {
        String id = httpServletRequest.getParameter("id");
        return taskService.insertTaskRelation(id)+"";
    }

    @RequestMapping("/insertGrade")
    @ResponseBody
    public String insertGrade(HttpServletRequest httpServletRequest){
        String id = httpServletRequest.getParameter("id");
        String grade = httpServletRequest.getParameter("grade");
        String taskid = httpServletRequest.getParameter("taskid");
        return taskService.insertGrade(id,grade,taskid)+"";
    }
}
