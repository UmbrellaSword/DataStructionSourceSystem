package com.ruolan.spring.dao;

import com.ruolan.spring.datasource.DBConn;
import com.ruolan.spring.pojo.Experiment;
import com.ruolan.spring.pojo.Notice;
import com.ruolan.spring.pojo.Student;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class NoticeDao {
    private static Connection conn = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
    private static final CallableStatement cs = null;

    public int ifnew(String id) throws SQLException {
        conn = DBConn.conn();		//调用 DBconnection 类的 conn() 方法连接数据库
        String sql = "select * from t_notice_info where Notice_jieshouren = '"+id+"' and Notice_ifnew = '是'";
        int ifnew = 0;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();		//将查询的结果放入ResultSet结果集中

            /**
             * 从结果集ResultSet中迭代取出查询结果并输出
             */
            rs.last();
            ifnew = rs.getRow();
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        }
        return ifnew;

    }

    public List<Notice> selectNotice(String userid, String type) {
        Connection conn=DBConn.conn();
        String sql = "";
        if(type.equals("全部信息"))
            sql="select * from t_notice_info where Notice_jieshouren='"+userid+"'";
        else if(type.equals("通知"))
            sql="select * from t_notice_info where Notice_jieshouren='"+userid+"' and Notice_type = '通知'";
        else
            sql="select * from t_notice_info where Notice_jieshouren='"+userid+"' and Notice_type = '私信'";
        List<Notice> noticeList = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();		//将查询的结果放入ResultSet结果集中
            while(rs.next()) {
                Notice notice = new Notice();
                int noticeid=rs.getInt("Notice_id");
                String type1=rs.getString("Notice_type");
                Date time = rs.getDate("Notice_time");
                String faqiren = rs.getString("Notice_faqiren");
                String jieshouren = rs.getString("Notice_jieshouren");
                String noticetext = rs.getString("Notice_text");
                String title=rs.getString("Notice_title");
                String ifnew=rs.getString("Notice_ifnew");
                notice.setNoticeid(noticeid);
                notice.setType(type1);
                notice.setTime(time);
                notice.setFaqiren(faqiren);
                notice.setJieshouren(jieshouren);
                notice.setNoticetext(noticetext);
                notice.setTitle(title);
                notice.setIfnew(ifnew);
                noticeList.add(notice);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("操作失败o(╥﹏╥");
            e.printStackTrace();
        }finally {

        }
        return noticeList;
    }

    public int UpdatenoticereadyAll(String userid, String type) {
        conn= DBConn.conn();
        int i = 0;
        String sql="";
        if(type.equals("全部信息"))
            sql="UPDATE t_notice_info set Notice_ifnew='否' where Notice_jieshouren='"+userid+"'";
        else if(type.equals("通知"))
            sql="UPDATE t_notice_info set Notice_ifnew='否' where Notice_jieshouren='"+userid+"' and Notice_type = '通知'";
        else
            sql="UPDATE t_notice_info set Notice_ifnew='否' where Notice_jieshouren='"+userid+"' and Notice_type = '私信'";
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

        }return i;

    }

    public int Updatenotice(String noticeid) {
        conn= DBConn.conn();
        int i = 0;
        String sql = "UPDATE t_notice_info set Notice_ifnew='否' where Notice_id='"+noticeid+"'";
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
            DBConn.close();

        }return i;

    }

    public int DelNotice(int noticeid) {
        conn = DBConn.conn();
        int i = 0;
        String sql = "Delete from t_notice_info where Notice_id = '"+noticeid+"'";
        try {
            ps=conn.prepareStatement(sql);
            i = ps.executeUpdate();
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

        }
        return i;

    }

    public int insertNotice(List<Student> studentList, String id,String topic, String type, String stuid, String content) {
        conn = DBConn.conn();
        int i = 0;
        String sql = "";
        if(type.equals("私信")){
            sql = "insert into t_notice_info (Notice_type,Notice_time,Notice_faqiren,Notice_jieshouren,Notice_title,Notice_text,Notice_ifnew)values('私信',NOW(),'"+id+"','"+stuid+"','"+topic+"','"+content+"','是')";
        }else{
            sql = "insert into t_notice_info (Notice_type,Notice_time,Notice_faqiren,Notice_jieshouren,Notice_title,Notice_text,Notice_ifnew)values";
            for(int j = 0;j<studentList.size();j++){
                sql +="('通知',NOW(),'"+id+"','"+studentList.get(j).getId()+"','"+topic+"','"+content+"','是'),";
            }
            /*for(Student student:studentList){
                sql +="('通知',NOW(),'"+id+"','"+student.getId()+"','"+topic+"','"+content+"','是'),";
            }*/
            sql=sql.substring(0,sql.length()-1);
        }
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
}
