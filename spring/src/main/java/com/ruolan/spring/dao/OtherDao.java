package com.ruolan.spring.dao;

import com.ruolan.spring.datasource.DBConn;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Component
public class OtherDao {
    private static Connection conn = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
    private static final CallableStatement cs = null;
    public static List<String> selectTypeOfCode() {
        conn = DBConn.conn();		//调用 DBconnection 类的 conn() 方法连接数据库
        String sql = "select content from t_type_code";
        List<String> list= new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();		//将查询的结果放入ResultSet结果集中

            /**
             * 从结果集ResultSet中迭代取出查询结果并输出
             */

            while(rs.next()) {
                String code = rs.getString("content");
                list.add(code);
            }
            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("操作失败o(╥﹏╥");
            e.printStackTrace();
        }finally {


        }
        return list;
    }
}
