package com.kwin.test;

import com.kwin.dao.AccountDao;
import com.kwin.dao.UserDao;
import com.kwin.domain.Account;
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
 * @create 2019-12-09 22:01
 */
public class testOne2one {

    InputStream in = null;
    SqlSessionFactory factory = null;
    SqlSession sqlSession = null;
    AccountDao accountDao = null;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        sqlSession = factory.openSession(true);
        accountDao = sqlSession.getMapper(AccountDao.class);
    }

    @After
    public void destroy() throws IOException {
        sqlSession.close();
        in.close();
    }

//    @Test
//    public void testOne2one(){
//        List<Account> accounts = accountDao.findAll();
//        for (Account account:accounts){
//            System.out.println("=============");
//            System.out.println(account);
//            System.out.println(account.getUser());
//        }
//    }
}
