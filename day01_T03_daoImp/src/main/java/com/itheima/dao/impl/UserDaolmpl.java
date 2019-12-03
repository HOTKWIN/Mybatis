package com.itheima.dao.impl;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.util.List;

/**
 * @author kwin
 * @create 2019-12-02 10:03
 */
public class UserDaolmpl implements IUserDao {

    private SqlSessionFactory factory;

    public UserDaolmpl(SqlSessionFactory factory){
        this.factory = factory;
    }

    public List<User> findAll() {

        //1.使用工厂创建SqlSession对象
        SqlSession session = factory.openSession();
        //2.使用session方法执行查询所有方法
        List<User> users = session.selectList("com.itheima.dao.IUserDao.findAll");
        session.close();
        //3.返回查询结果
        return users;
    }
}
