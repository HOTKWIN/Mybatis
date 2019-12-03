package com.mybatis.test;

import java.sql.*;

/**
 * 回顾JDBC
 *
 * @author kwin
 * @create 2019-12-03 19:46
 */
public class JdbcDemo {

    public static void main(String[] args) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            //1.加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.通过驱动管理员获取数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eesy","root","123456");
            //3.定义sql语句?表示占位符
            String sql = "select * from user where username=?";
            //4.获取预处理statement
            preparedStatement = connection.prepareStatement(sql);
            //5.设置参数,第一个参数为sql语句中参数的序号(从1开始)，第二个参数为设置的参数值
            preparedStatement.setString(1,"老王");
            //6.向数据库发出sql执行查询，查询出结果集
            resultSet = preparedStatement.executeQuery();
            //7.遍例查询结果集
            while (resultSet.next()){
                System.out.println(resultSet.getInt("id") + "," + resultSet.getString("username"));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
