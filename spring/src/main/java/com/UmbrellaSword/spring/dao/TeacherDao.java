package com.ruolan.spring.dao;

import com.ruolan.spring.datasource.DBConn;
import com.ruolan.spring.pojo.Teacher;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class TeacherDao {
    private static Connection conn = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
    private static final CallableStatement cs = null;
    /**
     * 添加一位教师用户
     * @param teacher
     * @return
     */
    public static int insertTeacher(Teacher teacher) {
        conn = DBConn.conn();		//调用 DBconnection 类的 conn() 方法连接数据库
        int i = 0;
        String sql = "INSERT INTO t_teacher_info (Tea_ID,Tea_name,Tea_order,Tea_email,Tea_pas,Tea_phone,Tea_type) VALUES(?,?,?,?,?,?,?)";		//插入sql语句
        try {
            ps = conn.prepareStatement(sql);

            /**
             * 调用实体StuInfo类,获取需要插入的各个字段的值
             * 注意参数占位的位置
             * 通过set方法设置参数的位置
             * 通过get方法取参数的值
             */
            ps.setInt(1, teacher.getId());
            ps.setString(2, teacher.getName());
            ps.setString(3, teacher.getOrder());
            ps.setString(4, teacher.getEmail());
            ps.setString(5, teacher.getPassword());
            ps.setString(6, teacher.getPhone());
            ps.setInt(7,teacher.getType());

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
     * 查询所有老师
     * @return
     */
    public static List<Teacher> selectTeacher() {
        conn = DBConn.conn();		//调用 DBconnection 类的 conn() 方法连接数据库
        String sql = "SELECT * FROM t_teacher_info";
        List<Teacher> teacherList = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();		//将查询的结果放入ResultSet结果集中

            /**
             * 从结果集ResultSet中迭代取出查询结果并输出
             */

            while(rs.next()) {
                Teacher teacher = new Teacher();
                int tea_ID = rs.getInt("Tea_ID");
                String tea_name = rs.getString("Tea_name");
                String tea_order = rs.getString("Tea_order");
                String tea_email = rs.getString("Tea_email");
                String tea_pas = rs.getString("Tea_pas");
                String tea_phone = rs.getString("Tea_phone");
                int tea_type = rs.getInt("Tea_type");
                teacher.setId(tea_ID);
                teacher.setName(tea_name);
                teacher.setOrder(tea_order);
                teacher.setEmail(tea_email);
                teacher.setPassword(tea_pas);
                teacher.setPhone(tea_phone);
                teacher.setType(tea_type);
                teacherList.add(teacher);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("操作失败o(╥﹏╥");
            e.printStackTrace();
        }finally {

        }
        return teacherList;
    }

    public int checkTeacher(String id, String password) {
        conn = DBConn.conn();		//调用 DBconnection 类的 conn() 方法连接数据库
        String sql = "SELECT count(*) FROM t_teacher_info where Tea_id="+id+" and Tea_pas="+password;
        int count = 0;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();		//将查询的结果放入ResultSet结果集中

            /**
             * 从结果集ResultSet中迭代取出查询结果并输出
             */
            rs=ps.executeQuery();
            while(rs.next()){
                count=rs.getInt("count(*)");
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("操作失败o(╥﹏╥");
            e.printStackTrace();
        }finally {

        }
        return count;
    }

    public Teacher selectMyTeacher(String id) {
        conn = DBConn.conn();		//调用 DBconnection 类的 conn() 方法连接数据库
        String sql = "select * from t_teacher_info where Tea_ID = (select Stu_teacher from t_student_info where Stu_ID = "+id+")";
        Teacher teacher = new Teacher();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();		//将查询的结果放入ResultSet结果集中

            /**
             * 从结果集ResultSet中迭代取出查询结果并输出
             */

            while(rs.next()) {
                int tea_ID = rs.getInt("Tea_ID");
                String tea_name = rs.getString("Tea_name");
                String tea_order = rs.getString("Tea_order");
                String tea_email = rs.getString("Tea_email");
                String tea_pas = rs.getString("Tea_pas");
                String tea_phone = rs.getString("Tea_phone");
                int tea_type = rs.getInt("Tea_type");
                teacher.setId(tea_ID);
                teacher.setName(tea_name);
                teacher.setOrder(tea_order);
                teacher.setEmail(tea_email);
                teacher.setPassword(tea_pas);
                teacher.setPhone(tea_phone);
                teacher.setType(tea_type);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("操作失败o(╥﹏╥");
            e.printStackTrace();
        }finally {

        }
        return teacher;

    }

    public String selectTeacherName(String faqiren) {
        Connection conn = DBConn.conn();		//调用 DBconnection 类的 conn() 方法连接数据库
        //System.out.println(faqiren);
        String sql = "select Tea_name from t_teacher_info where Tea_ID = '"+faqiren+"'";
        String Tea_name="";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Tea_name=rs.getString("Tea_name");
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

        }
        return Tea_name;

    }
}
