package com.kwin.test;

import com.kwin.dao.AccountDao;
import com.kwin.dao.UserDao;
import com.kwin.domain.Account;
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
import java.util.List;

/**
 * @author kwin
 * @create 2019-12-09 19:15
 */
public class UserTest {
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

    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();
        for (User user:users){
            System.out.println("==================");
            System.out.println(user);
            List<Account> accounts = user.getAccounts();
            for (Account account:accounts)
                System.out.println(account);
        }
    }
}
