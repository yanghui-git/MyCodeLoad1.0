package com.yh.mybatis.read;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OldJDBCTest {
    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet set = null;
        try {//com.mysql.cj.jdbc.Driver
            // 1.加载驱动
            Class.forName("com.mysql.jdbc.Driver");
          //  Class.forName("com.mysql.cj.jdbc.Driver");
            // 2.创建数据库的连接对象
            con = DriverManager.getConnection("jdbc:mysql://10.20.178.137:3306/yhtest",
                    "root","123456");
            // 3.定义sql语句
            String sql = "select * from user where id = ?";
            // 4.创建statement对象
            psmt = con.prepareStatement(sql);
            // 5.设置参数
            psmt.setInt(1, 1);
            // 6.执行
            set = psmt.executeQuery();
            // 7.处理结果集
            while (set.next()) {
                System.out.println("用户Id：" + set.getInt("id") + ",用户名称：" + set.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //8.释放资源
                if (set != null) set.close();
                if (psmt != null) psmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}