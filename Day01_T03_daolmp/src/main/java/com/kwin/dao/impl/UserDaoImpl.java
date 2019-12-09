package com.kwin.dao.impl;

import com.kwin.dao.UserDao;
import com.kwin.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author kwin
 * @create 2019-12-06 14:56
 */
public class UserDaoImpl implements UserDao {

    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory){
        this.factory = factory;
    }

    public List<User> findAll() {

        SqlSession sqlSession = factory.openSession();
        List<User> users = sqlSession.selectList("com.kwin.dao.UserDao.findAll");
        sqlSession.close();
        return users;
    }
}
