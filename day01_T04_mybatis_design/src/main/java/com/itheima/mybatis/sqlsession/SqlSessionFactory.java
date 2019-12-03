package com.itheima.mybatis.sqlsession;

/**
 * @author kwin
 * @create 2019-12-02 15:16
 */
public interface SqlSessionFactory {
    SqlSession openSession();
}
