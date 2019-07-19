package com.ruolan.spring.dao;

import com.ruolan.spring.datasource.DBConn;
import com.ruolan.spring.entity.TaskWithScore;
import com.ruolan.spring.pojo.Experiment;
import com.ruolan.spring.pojo.Student;
import com.ruolan.spring.pojo.Task;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Component
public class TaskDao {
    private  Connection conn = null;
    private  PreparedStatement ps = null;
    private  ResultSet rs = null;
    private static final CallableStatement cs = null;

    /**
     * 查询某教师的所有作业
     * @param teacherid
     * @return
     */
    public List<Task> selectTask(String teacherid) throws SQLException {
        conn = DBConn.conn();
        String sql = "Select * from t_task_info where Task_teacher = "+teacherid;
        Statement stmt=null;
        List<Task> taskList = new LinkedList<>();
        try {
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);

            while(rs.next()){
                Task task = new Task();
                int task_id=rs.getInt("Task_ID");
                String task_content=rs.getString("Task_content");
                Date task_time_out = rs.getDate("Task_time_out");
                Date task_time_in = rs.getDate("Task_time_in");
                int task_teacher = rs.getInt("Task_teacher");
                task.setId(task_id);
                task.setContent(task_content);
                task.setTime_in(task_time_in);
                task.setTime_out(task_time_out);
                task.setTeacher_id(task_teacher);
                System.out.println(task.toString());
                taskList.add(task);
            }

        } catch (SQLException e) {
            System.out.println("查询出错！");
            e.printStackTrace();
        }finally {

            rs.close();
            stmt.close();
            conn.close();

        }
        return taskList;
    }

    /**
     * 查询本日的所有作业
     * @return
     */
    public List<Task> selectTodayTask(){
        conn = DBConn.conn();
        String sql = "select * from t_task_info where to_days(Task_time_out) = to_days(now());";
        List<Task> taskList = new LinkedList<>();
        try {
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();

            while(rs.next()){
                Task task = new Task();
                int task_id=rs.getInt("Task_ID");
                String task_content=rs.getString("Task_content");
                Date task_time_out = rs.getDate("Task_time_out");
                Date task_time_in = rs.getDate("Task_time_in");
                int task_teacher = rs.getInt("Task_teacher");
                task.setId(task_id);
                task.setContent(task_content);
                task.setTime_in(task_time_in);
                task.setTime_out(task_time_out);
                task.setTeacher_id(task_teacher);
                taskList.add(task);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

        }
        return taskList;
    }

    public int insertTask(Task task){
        conn=DBConn.conn();
        int i = 0;
        String sql = "Insert into t_task_info(Task_content,Task_time_out,Task_time_in,Task_teacher)values(?,?,?,?)";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,task.getContent());
            ps.setDate(2,task.getTime_out());
            ps.setDate(3,task.getTime_in());
            ps.setInt(4,task.getTeacher_id());
            i=ps.executeUpdate();			//执行sql语句
            System.out.println("插入成功(*￣︶￣)");
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

        }
        return i;
    }

    public List<TaskWithScore> selectMyTaskWithScore(String id) throws SQLException {
        conn = DBConn.conn();
        String sql = "select Task_ID,Task_content,Task_time_in,Task_time_out,ChTask_score from t_task_info a,t_choosetask_relation b where a.Task_ID=b.ChTask_taskid and Task_teacher='"+id+"' group by Task_ID";
        Statement stmt=null;
        List<TaskWithScore> taskList = new LinkedList<>();
        try {
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);

            while(rs.next()){
                TaskWithScore task = new TaskWithScore();
                int task_id=rs.getInt("Task_ID");
                String task_content=rs.getString("Task_content");
                Date task_time_out = rs.getDate("Task_time_out");
                Date task_time_in = rs.getDate("Task_time_in");
                String chTask_score = rs.getString("ChTask_score");
                task.setId(task_id);
                task.setContent(task_content);
                task.setTime_in(task_time_in);
                task.setTime_out(task_time_out);
                task.setScore(chTask_score);
                System.out.println(task.toString());
                taskList.add(task);
            }


        } catch (SQLException e) {
            System.out.println("查询出错！");
            e.printStackTrace();
        }finally {
            rs.close();
            stmt.close();
            conn.close();
        }
        return taskList;
    }

    public int insertTaskRelation(List<Student> studentList, List<Task> taskList) {
        conn=DBConn.conn();
        int i = 0;
        String sql = "insert into t_choosetask_relation(ChTask_stuid,ChTask_taskid)values";
        for(Student student:studentList)
            for(Task task:taskList){
                sql+="("+student.getId()+","+task.getId()+"),";
            }
            sql=sql.substring(0,sql.length()-1);
        try {
            ps=conn.prepareStatement(sql);
            i=ps.executeUpdate();			//执行sql语句
            System.out.println("插入成功(*￣︶￣)");
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

        }
        return i;
    }

    public int insertGrade(String id, String grade, String taskid) {
        conn= DBConn.conn();
        int i = 0;
        String sql = "update t_choosetask_relation set ChTask_score = '"+grade+"' where ChTask_taskid = '"+taskid+"' and ChTask_stuid='"+id+"'";
        try {
            ps=conn.prepareStatement(sql);
            //ps.setString(6,answer);
            i=ps.executeUpdate();			//执行sql语句
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

        }
        return i;
    }
}
