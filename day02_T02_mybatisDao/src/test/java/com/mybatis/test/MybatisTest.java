package com.mybatis.test;

import com.itheima.dao.IUserDao;
import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author kwin
 * @create 2019-12-02 19:52
 */
public class MybatisTest {

    private InputStream in;
    private SqlSessionFactory factory;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
    }

    @After
    public void destroy() throws IOException {
        in.close();
    }

    /**
     * 测试查找所有用户操作
     * @throws IOException
     */
    @Test
    public void testFindAll(){
        IUserDao userDao = new UserDaoImpl(factory);
        List<User> users = userDao.findAll();
        for (User user:users)
            System.out.println(user);
    }

    /**
     * 测试保存用户操作
     */
    @Test
    public void testSaveUser(){
        UserDaoImpl userDao = new UserDaoImpl(factory);

        User user = new User();
        user.setId(49);
        user.setUsername("kwin");
        user.setAddress("广东广州");
        user.setSex("男");
        user.setBirthday(new Date());

        userDao.saveUser(user);
    }

    /**
     * 测试更新操作
     */
    @Test
    public void testUpdateUser(){
        IUserDao userDao = new UserDaoImpl(factory);

        User user = new User();
        user.setId(49);
        user.setUsername("kwin1");
        user.setAddress("广东广州");
        user.setSex("男");
        user.setBirthday(new Date());

        userDao.updateUser(user);
    }

    /**
     * 测试删除操作
     */
    @Test
    public void testDeleteUser(){
        IUserDao userDao = new UserDaoImpl(factory);

        userDao.deleteUser(49);
    }

    /**
     * 测试查询单个用户的方法
     */
    @Test
    public void testFindById(){
        IUserDao userDao = new UserDaoImpl(factory);

        User user = userDao.findById(48);
        System.out.println(user);
    }

    /**
     * 测试模糊查询操作
     */
    @Test
    public void testFindByName(){
        IUserDao userDao = new UserDaoImpl(factory);

        List<User> users = userDao.findByName("%王%");
        for (User user:users)
            System.out.println(user);
    }

    /**
     * 测试查询总记录条数
     */
    @Test
    public void testFindTotal(){
        IUserDao userDao = new UserDaoImpl(factory);

        userDao.findTotal();
    }

}
