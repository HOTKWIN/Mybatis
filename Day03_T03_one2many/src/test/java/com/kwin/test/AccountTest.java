package com.kwin.test;

import com.kwin.dao.AccountDao;
import com.kwin.dao.UserDao;
import com.kwin.domain.Account;
import com.kwin.domain.AccountUser;
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
 * @create 2019-12-09 15:01
 */
public class AccountTest {

    InputStream in = null;
    SqlSessionFactory factory = null;
    SqlSession sqlSession = null;
    AccountDao accountDao = null;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        //设置事务自动提交
        sqlSession = factory.openSession(true);
        accountDao = sqlSession.getMapper(AccountDao.class);
    }

    @After
    public void destroy() throws IOException {
        sqlSession.close();
        in.close();
    }

    /**
     * 测试查询所有账户，同时包含对应的用户信息
     *
     */
    @Test
    public void testFindAll(){
        List<AccountUser> accountUsers = accountDao.findAll();
        for (AccountUser accountUser:accountUsers){
            System.out.println("----------每个账户的信息--------");
            System.out.println(accountUser);
        }
    }

    @Test
    public void testFindAll2(){
        List<Account> accounts = accountDao.findAll2();
        for (Account account:accounts){
            System.out.println("----------每个账户的信息--------");
            System.out.println(account);
        }
    }
}
