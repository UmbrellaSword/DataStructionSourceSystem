package com.ruolan.spring.service;

import com.ruolan.spring.dao.StudentDao;
import com.ruolan.spring.dao.TaskDao;
import com.ruolan.spring.entity.TaskWithScore;
import com.ruolan.spring.pojo.Student;
import com.ruolan.spring.pojo.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskDao taskDao;
    @Autowired
    StudentDao studentDao;

    public List<Task> selectTaskByTeacher(String id) throws SQLException {
        return taskDao.selectTask(id);
    }

    public List<Task> selectTodayTask(){
        return taskDao.selectTodayTask();
    }

    public boolean insertTask(Task task){
        if(taskDao.insertTask(task)>0){
            return true;
        }
        else
            return false;
    }

    public List<TaskWithScore> selectMyTaskWithScore(String id) throws SQLException {
        return taskDao.selectMyTaskWithScore(id);
    }

    public boolean insertTaskRelation(String id) throws SQLException {
        List<Student> studentList = studentDao.selectStudentByteacher(id);
        System.out.println(studentList.size());
        List<Task> taskList = taskDao.selectTask(id);
        if(taskDao.insertTaskRelation(studentList,taskList)>0){
            return true;
        }
        else
            return false;
    }

    public boolean insertGrade(String id, String grade, String taskid) {
        if(taskDao.insertGrade(id,grade,taskid)>0){
            return true;
        }
        else
            return false;
    }
}
