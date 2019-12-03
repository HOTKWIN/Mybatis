package com.itheima.mybatis.sqlsession;

/**
 * @author kwin
 * @create 2019-12-02 15:17
 */
public interface SqlSession {

    <T> T getMapper(Class<T> daoInterfaceClass);

    void close();
}
