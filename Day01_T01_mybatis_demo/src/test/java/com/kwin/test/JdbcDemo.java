package com.kwin.test;

import com.kwin.domain.User;

import java.sql.*;

/**
 * 回顾Jdbc
 *
 * 模糊查询
 *
 * @author kwin
 * @create 2019-12-06 13:40
 */
public class JdbcDemo {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");

            //2.获取数据库连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eesy", "root", "123456");

            //3.定义sql语句
            String sql = "select * from user where username like ?";

            //4.获取prepareStatement
            pstmt = conn.prepareStatement(sql);

            //5.传入参数
            pstmt.setString(1,"%王%");

            //6.执行sql语句
            rs = pstmt.executeQuery();

            //7.打印输出
            User user = null;
            while (rs.next()){
                int id = rs.getInt("id");
                String username = rs.getString("username");
                Date birthday = rs.getDate("birthday");
                String sex = rs.getString("sex");
                String address = rs.getString("address");

                user = new User();
                user.setId(id);
                user.setUsername(username);
                user.setBirthday(birthday);
                user.setSex(sex);
                user.setAddress(address);

                System.out.println(user);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (pstmt != null){
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
