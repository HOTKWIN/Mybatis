package com.kwin.test;

import com.kwin.dao.UserDao;
import com.kwin.domain.User;
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
 * @create 2019-12-09 21:16
 */
public class AnnotationCRUDTest {

    InputStream in = null;
    SqlSessionFactory factory = null;
    SqlSession sqlSession = null;
    UserDao userDao = null;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        sqlSession = factory.openSession(true);
        userDao = sqlSession.getMapper(UserDao.class);
    }

    @After
    public void destroy() throws IOException {
        sqlSession.close();
        in.close();
    }

    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();
        for (User user:users)
            System.out.println(user);
    }

    @Test
    public void testSave(){
        User user = new User();
        user.setUserName("kwin1");
        user.setUserAddress("白云");
        user.setUserSex("男");
        user.setUserBirthday(new Date());
        System.out.println("保存前：" + user);
        int count = userDao.saveUser(user);
        System.out.println("保存后：" + user);
        System.out.println("影响行数：" + count);
    }

    @Test
    public void testUpdateUser(){
        User user = new User();
        user.setUserId(66);
        user.setUserName("kwin666");

        userDao.updateUser(user);
    }

    @Test
    public void testDeleteUser(){
        userDao.deleteUser(66);
    }

    @Test
    public void testFindById(){
        User user = userDao.findById(64);
        System.out.println(user);
    }

    @Test
    public void testFindByName(){
        List<User> users = userDao.findByUserName("%王%");
        for (User user:users)
            System.out.println(user);
    }

    @Test
    public void testFindTotal(){
        int totalUser = userDao.findTotalUser();
        System.out.println(totalUser);
    }
}
