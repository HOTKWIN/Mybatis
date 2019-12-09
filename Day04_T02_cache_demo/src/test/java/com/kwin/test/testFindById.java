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

/**
 * @author kwin
 * @create 2019-12-09 20:10
 */
public class testFindById {

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
        sqlSession.close();
        in.close();
    }


    /**
     * 测试一级缓存的存在
     */
    @Test
    public void testFindById(){
        User user1 = userDao.findById(41);
        System.out.println("第一次查询：" + user1);

        User user2 = userDao.findById(41);
        System.out.println("第二次查询：" + user2);

        System.out.println(user1 == user2);
    }

    @Test
    public void testClearCache(){
        User user1 = userDao.findById(41);
        System.out.println("第一次查询：" + user1);

        //清除一级缓存
//        sqlSession.close();
//        sqlSession = factory.openSession();

        //清除一级缓存的另一种方式
        sqlSession.clearCache();

        userDao = sqlSession.getMapper(UserDao.class);

        User user2 = userDao.findById(41);
        System.out.println("第二次查询：" + user2);

        System.out.println(user1 == user2);
    }

    @Test
    public void testRUDCache(){
        User user1 = userDao.findById(41);
        System.out.println("第一次查询：" + user1);

        user1.setUsername("新名字");
        userDao.updateUser(user1);

        User user2 = userDao.findById(41);
        System.out.println("第二次查询：" + user2);

        System.out.println(user1 == user2);
    }

    @Test
    public void testSecondLevelCache(){
        SqlSession sqlSession1 = factory.openSession();
        UserDao userDao1 = sqlSession1.getMapper(UserDao.class);
        User user1 = userDao1.findById(41);
        System.out.println("第一次查询：" + user1);
        //一级缓存消失
        sqlSession1.close();

        SqlSession sqlSession2 = factory.openSession();
        UserDao userDao2 = sqlSession2.getMapper(UserDao.class);
        User user2 = userDao2.findById(41);
        System.out.println("第一次查询：" + user1);
        sqlSession2.close();

        System.out.println(sqlSession1 == sqlSession2);

    }
}
