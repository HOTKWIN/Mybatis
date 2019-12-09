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
 * @create 2019-12-09 13:24
 */
public class MybatisTest {

    InputStream in = null;
    SqlSessionFactory factory = null;
    SqlSession sqlSession = null;
    UserDao userDao = null;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        //设置事务自动提交
        sqlSession = factory.openSession(true);
        userDao = sqlSession.getMapper(UserDao.class);
    }

    @After
    public void destroy() throws IOException {
//        sqlSession.commit();

        sqlSession.close();
        in.close();
    }

    /**
     * 测试查询所有
     * @throws IOException
     */
    @Test
    public void testFindAll() throws IOException {
        List<User> users = userDao.findAll();
        for (User user:users){
            System.out.println(user);
        }
    }

    /**
     * 测试添加用户，使用自动提交
     */
    @Test
    public void testSaveUser(){
        User user = new User();
        user.setUsername("kwin");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("白云区");

        userDao.saveUser(user);
    }
}
