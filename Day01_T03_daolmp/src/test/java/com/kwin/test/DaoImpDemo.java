package com.kwin.test;

import com.kwin.dao.impl.UserDaoImpl;
import com.kwin.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author kwin
 * @create 2019-12-06 15:03
 */
public class DaoImpDemo {

    public static void main(String[] args) throws IOException {

        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        UserDaoImpl userDao = new UserDaoImpl(factory);
        List<User> users = userDao.findAll();
        for (User user:users){
            System.out.println(user);
        }
        in.close();
    }
}
