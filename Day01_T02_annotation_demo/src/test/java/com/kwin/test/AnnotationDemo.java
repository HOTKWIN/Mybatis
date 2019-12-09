package com.kwin.test;

import com.kwin.dao.UserDao;
import com.kwin.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author kwin
 * @create 2019-12-06 14:34
 */
public class AnnotationDemo {

    public static void main(String[] args) throws IOException {

        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> users = userDao.findAll();
        for (User user:users)
            System.out.println(user);
        sqlSession.close();
        in.close();
    }
}
