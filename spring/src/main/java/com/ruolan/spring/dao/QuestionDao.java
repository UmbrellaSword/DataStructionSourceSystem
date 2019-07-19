package com.ruolan.spring.dao;

import com.ruolan.spring.datasource.DBConn;
import com.ruolan.spring.pojo.Experiment;
import com.ruolan.spring.pojo.Question;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionDao {
    private static Connection conn = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
    private static final CallableStatement cs = null;

    /**
     * 提出一个问题
     * @param question
     * @return
     */
    public int insertQuestion(Question question){
        conn= DBConn.conn();
        int i = 0;
        String sql = "Insert into t_question_info (Que_ID,Que_topic,Que_content,Que_teacher,Que_stuid)values(?,?,?,?,?)";
        try {
            ps=conn.prepareStatement(sql);
            ps.setInt(1,question.getId());
            ps.setString(2,question.getTopic());
            ps.setString(3,question.getContent());
            ps.setInt(4,question.getTeacher_id());
            ps.setString(5,question.getStudent_id());
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

    /**
     * 添加某问题的回复
     * @param answer
     * @param id
     * @return
     */
    public int insertAnswer(String answer,String id){
        conn= DBConn.conn();
        int i = 0;
        String sql = "UPDATE t_question_info set Que_response = '"+answer+"' WHERE Que_ID = '"+id+"'";
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

    /**
     * 查询某教师的所有答疑
     * @param id
     * @return
     */
    public static List<Question> selectQuestion(int id) {
        conn = DBConn.conn();		//调用 DBconnection 类的 conn() 方法连接数据库
        String sql = "Select * from t_question_info where Que_teacher = '"+id+"' and Que_response is null" ;
        List<Question> questionList = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();		//将查询的结果放入ResultSet结果集中

            /**
             * 从结果集ResultSet中迭代取出查询结果并输出
             */

            while(rs.next()) {
                Question question = new Question();
                int que_ID = rs.getInt("Que_ID");
                String que_topic = rs.getString("Que_topic");
                String que_content = rs.getString("Que_content");
                int que_teacher = rs.getInt("Que_teacher");
                String que_stuid = rs.getString("Que_stuid");
                question.setId(que_ID);
                question.setTopic(que_topic);
                question.setContent(que_content);
                question.setTeacher_id(que_teacher);
                question.setStudent_id(que_stuid);
                questionList.add(question);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("操作失败o(╥﹏╥");
            e.printStackTrace();
        }finally {

        }
        return questionList;
    }
    public List<Question> selectMyQuestion(String id){
        conn = DBConn.conn();		//调用 DBconnection 类的 conn() 方法连接数据库
        String sql = "Select * from t_question_info where Que_stuid = '"+id+"'";
        List<Question> questionList = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();		//将查询的结果放入ResultSet结果集中

            /**
             * 从结果集ResultSet中迭代取出查询结果并输出
             */

            while(rs.next()) {
                Question question = new Question();
                int que_ID = rs.getInt("Que_ID");
                String que_topic = rs.getString("Que_topic");
                String que_content = rs.getString("Que_content");
                int que_teacher = rs.getInt("Que_teacher");
                String que_stuid = rs.getString("Que_stuid");
                String que_response = rs.getString("Que_response");
                question.setId(que_ID);
                question.setTopic(que_topic);
                question.setContent(que_content);
                question.setTeacher_id(que_teacher);
                question.setStudent_id(que_stuid);
                question.setResponse(que_response);
                questionList.add(question);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("操作失败o(╥﹏╥");
            e.printStackTrace();
        }finally {

        }
        return questionList;
    }

    public String selectStudentNameById(String id) {
        conn = DBConn.conn();		//调用 DBconnection 类的 conn() 方法连接数据库
        String sql = "select Stu_name from t_student_info where Stu_ID = '"+id+"'";
        String Stu_name = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();		//将查询的结果放入ResultSet结果集中

            /**
             * 从结果集ResultSet中迭代取出查询结果并输出
             */

            while(rs.next()) {
                Stu_name = rs.getString("Stu_name");
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("操作失败o(╥﹏╥");
            e.printStackTrace();
        }finally {

        }
        return Stu_name;
    }

    public List<Question> selectNotNullQuestion(String id) {
        conn = DBConn.conn();		//调用 DBconnection 类的 conn() 方法连接数据库
        String sql = "Select * from t_question_info where Que_teacher = '"+id+"' and Que_response is not null" ;
        List<Question> questionList = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();		//将查询的结果放入ResultSet结果集中

            /**
             * 从结果集ResultSet中迭代取出查询结果并输出
             */

            while(rs.next()) {
                Question question = new Question();
                int que_ID = rs.getInt("Que_ID");
                String que_topic = rs.getString("Que_topic");
                String que_content = rs.getString("Que_content");
                int que_teacher = rs.getInt("Que_teacher");
                String que_stuid = rs.getString("Que_stuid");
                String que_response = rs.getString("Que_response");
                question.setId(que_ID);
                question.setTopic(que_topic);
                question.setContent(que_content);
                question.setTeacher_id(que_teacher);
                question.setStudent_id(que_stuid);
                question.setResponse(que_response);
                questionList.add(question);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

        }
        return questionList;
    }
}
