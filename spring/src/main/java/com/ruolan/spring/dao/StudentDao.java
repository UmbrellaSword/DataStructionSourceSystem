package com.ruolan.spring.dao;

import com.ruolan.spring.datasource.DBConn;
import com.ruolan.spring.pojo.Experiment;
import com.ruolan.spring.pojo.Student;
import org.springframework.stereotype.Component;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class StudentDao {
    private static Connection conn = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
    private static final CallableStatement cs = null;

    /**
     * Insert方法封装
     * @param stu 传入参数
     */
    public static int insertStudent(Student stu) {
        conn = DBConn.conn();		//调用 DBconnection 类的 conn() 方法连接数据库
        int i = 0;
        String sql = "INSERT INTO t_student_info (Stu_ID,Stu_name,Stu_major,Stu_email,Stu_class,Stu_password,Stu_phone,Stu_teacher,Stu_type) VALUES(?,?,?,?,?,?,?,?,?)";		//插入sql语句
        try {
            ps = conn.prepareStatement(sql);

            /**
             * 调用实体StuInfo类,获取需要插入的各个字段的值
             * 注意参数占位的位置
             * 通过set方法设置参数的位置
             * 通过get方法取参数的值
             */
            ps.setInt(1, stu.getId());
            ps.setString(2, stu.getName());
            ps.setString(3, stu.getMajor());
            ps.setString(4, stu.getEmail());
            ps.setString(5, stu.getClassroom());
            ps.setString(6, stu.getPassword());
            ps.setString(7, stu.getPhone());
            ps.setInt(8,stu.getTeacher());
            ps.setInt(9,stu.getType());

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

    public int updateStudent(Student student){
        conn = DBConn.conn();
        int i = 0;
        String sql = "update t_student_info set Stu_name='" + student.getName() + "' where Stu_ID='" + student.getId() + "'";
        try {
            ps = conn.prepareStatement(sql);
            i = ps.executeUpdate();
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }finally{

        }
        return i;

    }

    public int checkStudent(String id, String password) {
        conn = DBConn.conn();		//调用 DBconnection 类的 conn() 方法连接数据库
        String sql = "SELECT count(*) FROM t_student_info where Stu_id="+id+" and Stu_password="+password;
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

    public List<Student> selectStudentByteacher(String teaid) {
        conn = DBConn.conn();		//调用 DBconnection 类的 conn() 方法连接数据库
        String sql = "Select * from t_student_info where Stu_teacher='"+teaid+"'";
        List<Student> studentsList = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();		//将查询的结果放入ResultSet结果集中

            /**
             * 从结果集ResultSet中迭代取出查询结果并输出
             */

            while(rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("Stu_id"));
                student.setType(rs.getInt("Stu_type"));
                student.setTeacher(rs.getInt("Stu_teacher"));
                student.setPhone(rs.getString("Stu_phone"));
                student.setPassword(rs.getString("Stu_password"));
                student.setName(rs.getString("Stu_name"));
                student.setMajor(rs.getString("Stu_major"));
                student.setEmail(rs.getString("Stu_email"));
                student.setClassroom(rs.getString("Stu_class"));
                studentsList.add(student);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("操作失败o(╥﹏╥");
            e.printStackTrace();
        }finally {

        }
        return studentsList;
    }
}
