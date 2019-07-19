package com.ruolan.spring.dao;

import com.ruolan.spring.datasource.DBConn;
import com.ruolan.spring.pojo.Experiment;
import com.ruolan.spring.pojo.Source;
import com.ruolan.spring.pojo.Student;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ResourceDao {
    private static Connection conn = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
    private static final CallableStatement cs = null;
    public static int insertResource(String res_name,String teacher_id) {
        conn = DBConn.conn();		//调用 DBconnection 类的 conn() 方法连接数据库
        int i = 0;
        String sql = "insert into t_resource_info(Res_name,Res_update_teacher,Res_update_time)VALUES('"+res_name+"',"+teacher_id+",NOW())";		//插入sql语句
        try {
            ps = conn.prepareStatement(sql);
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

    public static List<Source> selectSource() {
        conn = DBConn.conn();		//调用 DBconnection 类的 conn() 方法连接数据库
        String sql = "SELECT * FROM t_resource_info";
        List<Source> resourceList = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();		//将查询的结果放入ResultSet结果集中

            /**
             * 从结果集ResultSet中迭代取出查询结果并输出
             */

            while(rs.next()) {
                Source source = new Source();
                int res_id = rs.getInt("Res_ID");
                String res_name = rs.getString("Res_name");
                String res_update_teacher = rs.getString("Res_update_teacher");
                Date res_update_time = rs.getDate("Res_update_time");
                source.setId(res_id);
                source.setName(res_name);
                source.setUpdate_teacher(res_update_teacher);
                source.setUpdate_time(res_update_time);
                resourceList.add(source);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("操作失败o(╥﹏╥");
            e.printStackTrace();
        }finally {

        }
        return resourceList;
    }

    public int insertMyResource(String stuid,String resid){
        conn=DBConn.conn();
        int i = 0;
        String sql = "insert into t_opresource_relation (Opre_stuid,Opre_time,Opre_resid) VALUES("+stuid+",now(),"+resid+")";
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

    public String selectIdByNameOfResource(String name){
        conn = DBConn.conn();		//调用 DBconnection 类的 conn() 方法连接数据库
        String sql = "select Res_id from t_resource_info where Res_name = '"+name+"'";

        String Res_id = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();		//将查询的结果放入ResultSet结果集中

            /**
             * 从结果集ResultSet中迭代取出查询结果并输出
             */

            while(rs.next()) {
                Res_id = rs.getString("Res_ID");
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("操作失败o(╥﹏╥");
            e.printStackTrace();
        }finally {

        }
        return Res_id;
    }
    public static List<Source> selectMySource(String id) {
        conn = DBConn.conn();		//调用 DBconnection 类的 conn() 方法连接数据库
        String sql = "select * from t_resource_info where Res_ID in (select Opre_id from t_opresource_relation where Opre_stuid = "+id+")";
        List<Source> resourceList = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();		//将查询的结果放入ResultSet结果集中

            /**
             * 从结果集ResultSet中迭代取出查询结果并输出
             */

            while(rs.next()) {
                Source source = new Source();
                int res_id = rs.getInt("Res_ID");
                String res_name = rs.getString("Res_name");
                String res_update_teacher = rs.getString("Res_update_teacher");
                Date res_update_time = rs.getDate("Res_update_time");
                source.setId(res_id);
                source.setName(res_name);
                source.setUpdate_teacher(res_update_teacher);
                source.setUpdate_time(res_update_time);
                resourceList.add(source);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("操作失败o(╥﹏╥");
            e.printStackTrace();
        }finally {

        }
        return resourceList;
    }

    public List<Source> selectUptodateSource() {
        conn = DBConn.conn();		//调用 DBconnection 类的 conn() 方法连接数据库
        String sql = "select * from t_resource_info s  \n" +
                "  ORDER BY ABS(NOW() - s.Res_update_time) ASC\n" +
                "limit 1";
        List<Source> resourceList = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();		//将查询的结果放入ResultSet结果集中

            /**
             * 从结果集ResultSet中迭代取出查询结果并输出
             */

            while(rs.next()) {
                Source source = new Source();
                int res_id = rs.getInt("Res_ID");
                String res_name = rs.getString("Res_name");
                String res_update_teacher = rs.getString("Res_update_teacher");
                Date res_update_time = rs.getDate("Res_update_time");
                source.setId(res_id);
                source.setName(res_name);
                source.setUpdate_teacher(res_update_teacher);
                source.setUpdate_time(res_update_time);
                resourceList.add(source);
            }
            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("操作失败o(╥﹏╥");
            e.printStackTrace();
        }finally {

        }
        return resourceList;
    }
}
