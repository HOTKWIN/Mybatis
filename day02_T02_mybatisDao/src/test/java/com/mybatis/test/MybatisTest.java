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
    private IUserDao userDao;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        userDao = new UserDaoImpl(factory);

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
    public void testFindAll() throws IOException {

        List<User> users = userDao.findAll();
        for (User user:users)
            System.out.println(user);
    }

    /**
     * 测试保存用户操作
     */
    @Test
    public void testSaveUser() throws IOException {

        User user = new User();
        user.setUsername("kwin1");
        user.setAddress("广东省广州市");
        user.setSex("男");
        user.setBirthday(new Date());

        System.out.println("保存操作之前：" + user);
        userDao.saveUser(user);
        System.out.println("保存操作之后：" + user);

    }

    /**
     * 测试更新操作
     */
    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(52);
        user.setUsername("lsx");
        user.setAddress("白云区");
        user.setSex("女");
        user.setBirthday(new Date());

        userDao.updateUser(user);
    }

    /**
     * 测试删除操作
     */
    @Test
    public void testDelete(){
        userDao.deleteUser(52);
    }

    /**
     * 测试查询单个用户的方法
     */
    @Test
    public void testFindOne(){
        User user = userDao.findById(48);
        System.out.println(user);
    }

    /**
     * 测试模糊查询操作
     */
    @Test
    public void testFindByName(){
        List<User> users = userDao.findByName("%王%");
//        List<User> users = userDao.findByName("王");
        for(User user:users)
            System.out.println(user);
    }

    /**
     * 测试查询总记录条数
     */
    @Test
    public void testFindTotal(){
        int total = userDao.findTotal();
        System.out.println(total);
    }

}
