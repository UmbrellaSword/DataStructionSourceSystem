package com.ruolan.spring.dao;

import com.ruolan.spring.datasource.DBConn;
import com.ruolan.spring.entity.ExperimentWithScore;
import com.ruolan.spring.pojo.Experiment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.*;
import java.sql.Date;
import java.util.*;


@Component
public class ExperimentDao {
    private static Connection conn = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
    private static final CallableStatement cs = null;
    @Autowired
    Experiment experiment;

    /**
     * 查询所有实验
     * @return
     */
    public static List<Experiment> selectExperiment() {
        conn = DBConn.conn();		//调用 DBconnection 类的 conn() 方法连接数据库
        String sql = "SELECT * FROM t_experiment_info";
        List<Experiment> experimentList = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();		//将查询的结果放入ResultSet结果集中

            /**
             * 从结果集ResultSet中迭代取出查询结果并输出
             */

            while(rs.next()) {
                Experiment experiment = new Experiment();
                int exp_id = rs.getInt("Exp_ID");
                String exp_name = rs.getString("Exp_name");
                String exp_summary = rs.getString("Exp_summary");
                String exp_place = rs.getString("Exp_place");
                Date exp_time = rs.getDate("Exp_time");
                experiment.setId(exp_id);
                experiment.setName(exp_name);
                experiment.setSummery(exp_summary);
                experiment.setPlace(exp_place);
                experiment.setTime(exp_time);
                experimentList.add(experiment);
            }
            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("操作失败o(╥﹏╥");
            e.printStackTrace();
        }finally {

        }
        return experimentList;
    }

    /**
     * 查询本人所选实验
     * @return
     */
    public static List<Experiment> selectMyExperiment(String id) {
        conn = DBConn.conn();		//调用 DBconnection 类的 conn() 方法连接数据库
        String sql = "Select * from t_experiment_info where Exp_ID in (select Chex_expid from t_chooseexperiment_relation where Chex_stuid="+id+")";
        List<Experiment> experimentList = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();		//将查询的结果放入ResultSet结果集中

            /**
             * 从结果集ResultSet中迭代取出查询结果并输出
             */

            while(rs.next()) {
                Experiment experiment = new Experiment();
                int exp_id = rs.getInt("Exp_ID");
                String exp_name = rs.getString("Exp_name");
                String exp_summary = rs.getString("Exp_summary");
                String exp_place = rs.getString("Exp_place");
                Date exp_time = rs.getDate("Exp_time");
                experiment.setId(exp_id);
                experiment.setName(exp_name);
                experiment.setSummery(exp_summary);
                experiment.setPlace(exp_place);
                experiment.setTime(exp_time);
                experimentList.add(experiment);
            }rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("操作失败o(╥﹏╥");
            e.printStackTrace();
        }finally {

        }
        return experimentList;
    }

    /**
     * 添加实验
     * @param experiment
     * @return
     */
    public static int insertExperiment(Experiment experiment){
        conn=DBConn.conn();
        int i = 0;
        String sql = "insert into t_experiment_info (Exp_name,Exp_summary,Exp_place,Exp_time) values(?,?,?,?)";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,experiment.getName());
            ps.setString(2,experiment.getSummery());
            ps.setString(3,experiment.getPlace());
            ps.setDate(4,experiment.getTime());
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
     * 删除某实验
     * @param pid
     * @return
     */
    public int deleteExperiment(String pid){
        conn = DBConn.conn();
        int i = 0;
        String sql = "Delete from t_experiment_info where Exp_id = '"+pid+"'";
        try {
            ps=conn.prepareStatement(sql);
            i = ps.executeUpdate();
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return i;
    }

    public Experiment selectUpToDateExperiment() {
        conn = DBConn.conn();		//调用 DBconnection 类的 conn() 方法连接数据库
        String sql = "select * from t_experiment_info s \n" +
                "ORDER BY ABS(NOW() - s.Exp_time) ASC\n" +
                "limit 1";
        Experiment experiment = new Experiment();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();		//将查询的结果放入ResultSet结果集中

            /**
             * 从结果集ResultSet中迭代取出查询结果并输出
             */

            while(rs.next()) {
                int exp_id = rs.getInt("Exp_ID");
                String exp_name = rs.getString("Exp_name");
                String exp_summary = rs.getString("Exp_summary");
                String exp_place = rs.getString("Exp_place");
                Date exp_time = rs.getDate("Exp_time");
                experiment.setId(exp_id);
                experiment.setName(exp_name);
                experiment.setSummery(exp_summary);
                experiment.setPlace(exp_place);
                experiment.setTime(exp_time);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("操作失败o(╥﹏╥");
            e.printStackTrace();
        }finally {


        }
        return experiment;
    }

    public String selectIdByName(String name){
        conn = DBConn.conn();		//调用 DBconnection 类的 conn() 方法连接数据库
        String sql = "SELECT Exp_ID FROM t_experiment_info WHERE Exp_name = '"+name+"'";
        String exp_id = "";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();		//将查询的结果放入ResultSet结果集中

            /**
             * 从结果集ResultSet中迭代取出查询结果并输出
             */

            while(rs.next()) {
                exp_id = rs.getString("Exp_ID");
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("操作失败o(╥﹏╥");
            e.printStackTrace();
        }finally {


        }
        return exp_id;

    }

    public int insertMyExperiment(String Exp_id,String Stu_id){
        conn=DBConn.conn();
        int i = 0;
        String sql = "insert into t_chooseexperiment_relation(Chex_expid,Chex_stuid)VALUES("+Exp_id+","+Stu_id+")";
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

    public  int deleteMyExperiment(String Exp_id,String Stu_id) {
        conn=DBConn.conn();
        int i = 0;
        String sql = "delete from t_chooseexperiment_relation where Chex_expid = "+ Exp_id +" and Chex_stuid = "+Stu_id;
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public int insertGrade(String id, String grade, String expid) {
        conn= DBConn.conn();
        int i = 0;
        String sql = "UPDATE t_chooseexperiment_relation SET Chex_score = '"+grade+"' WHERE Chex_stuid = '"+id+"' and Chex_expid = '"+expid+"'";
        try {
            ps=conn.prepareStatement(sql);
            //ps.setString(6,answer);
            i=ps.executeUpdate();
            rs.close();
            ps.close();
            conn.close();//执行sql语句
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {


        }
        return i;
    }

    public List<ExperimentWithScore> selectMyExperimentWithScore(String id) {
        conn = DBConn.conn();		//调用 DBconnection 类的 conn() 方法连接数据库
        String sql = "select Exp_ID,Exp_name,Chex_score from t_experiment_info a,t_chooseexperiment_relation b where b.Chex_expid = a.Exp_ID and b.Chex_stuid='"+id+"'";
        List<ExperimentWithScore> experimentList = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();		//将查询的结果放入ResultSet结果集中

            /**
             * 从结果集ResultSet中迭代取出查询结果并输出
             */

            while(rs.next()) {
                ExperimentWithScore experiment = new ExperimentWithScore();
                int exp_id = rs.getInt("Exp_ID");
                String exp_name = rs.getString("Exp_name");
                String chex_score =rs.getString("Chex_score");
                experiment.setId(exp_id);
                experiment.setName(exp_name);
                experiment.setScore(chex_score);
                experimentList.add(experiment);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("操作失败o(╥﹏╥");
            e.printStackTrace();
        }finally {
        }
        return experimentList;

    }
}
