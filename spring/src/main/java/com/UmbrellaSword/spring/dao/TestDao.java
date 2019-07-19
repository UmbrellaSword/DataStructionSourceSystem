package com.ruolan.spring.dao;

import com.ruolan.spring.datasource.DBConn;
import com.ruolan.spring.pojo.Experiment;
import com.ruolan.spring.pojo.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class TestDao {
    private static Connection conn = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
    private static final CallableStatement cs = null;
    public static List<Test> selectTest(int num,int inwhat) {
        conn = DBConn.conn();		//调用 DBconnection 类的 conn() 方法连接数据库
        String sql = null;
        if(inwhat == 0)
            sql="select  *  from  t_test_info order by rand() limit " + num;
        else
            sql="select  *  from  t_test_info WHERE Test_type = " + inwhat + " order by rand() limit " + num;
        List<Test> testList = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();		//将查询的结果放入ResultSet结果集中

            /**
             * 从结果集ResultSet中迭代取出查询结果并输出
             */

            while(rs.next()) {
                Test test = new Test();
                int test_id = rs.getInt("Test_ID");
                int test_type = rs.getInt("Test_type");
                String test_question = rs.getString("Test_question");
                String test_answer = rs.getString("Test_answer");
                String test_answerA = rs.getString("Test_answerA");
                String test_answerB = rs.getString("Test_answerB");
                String test_answerC = rs.getString("Test_answerC");
                String test_answerD = rs.getString("Test_answerD");
                test.setType(test_type);
                test.setId(test_id);
                test.setQuestion(test_question);
                test.setAnswer(test_answer);
                test.setAnswerA(test_answerA);
                test.setAnswerB(test_answerB);
                test.setAnswerC(test_answerC);
                test.setAnswerD(test_answerD);
                testList.add(test);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("操作失败o(╥﹏╥");
            e.printStackTrace();
        }finally {

        }
        return testList;
    }
}
