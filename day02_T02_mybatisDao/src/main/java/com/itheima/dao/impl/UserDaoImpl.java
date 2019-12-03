package com.itheima.dao.impl;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author kwin
 * @create 2019-12-02 22:06
 */
public class UserDaoImpl implements IUserDao {

    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory){
        this.factory = factory;
    }

    public List<User> findAll() {

        SqlSession sqlSession = factory.openSession();
        List<User> users = sqlSession.selectList("com.itheima.dao.IUserDao.findAll");//参数就是获取配置信息的key
        sqlSession.close();
        return users;
    }

    public void saveUser(User user) {
        SqlSession sqlSession = factory.openSession();
        sqlSession.insert("com.itheima.dao.IUserDao.saveUser",user);
        sqlSession.commit();
        sqlSession.close();
    }

    public void updateUser(User user) {
        SqlSession sqlSession = factory.openSession();
        sqlSession.update("com.itheima.dao.IUserDao.updateUser",user);
        sqlSession.commit();
        sqlSession.close();
    }

    public void deleteUser(Integer userId) {
        SqlSession sqlSession = factory.openSession();
        sqlSession.delete("com.itheima.dao.IUserDao.deleteUser",userId);
        sqlSession.commit();
        sqlSession.close();
    }

    public User findById(Integer userId) {
        SqlSession sqlSession = factory.openSession();
        User user = sqlSession.selectOne("com.itheima.dao.IUserDao.findById", userId);
        sqlSession.close();
        return user;
    }

    public List<User> findByName(String username) {
        SqlSession sqlSession = factory.openSession();
        List<User> users = sqlSession.selectList("com.itheima.dao.IUserDao.findByName", username);
        sqlSession.close();
        return users;
    }

    public int findTotal() {
        SqlSession sqlSession = factory.openSession();
        int total = sqlSession.selectOne("com.itheima.dao.IUserDao.findTotal");
        sqlSession.close();
        return total;
    }
}
