package com.kwin.test;

import com.kwin.dao.RoleDao;
import com.kwin.dao.UserDao;
import com.kwin.domain.Role;
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
 * @create 2019-12-09 16:22
 */
public class RoleTest {

    InputStream in = null;
    SqlSessionFactory factory = null;
    SqlSession sqlSession = null;
    RoleDao roleDao = null;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        //设置事务自动提交
        sqlSession = factory.openSession(true);
        roleDao = sqlSession.getMapper(RoleDao.class);
    }

    @After
    public void destroy() throws IOException {
//        sqlSession.commit();

        sqlSession.close();
        in.close();
    }

    @Test
    public void testFindAll(){
        List<Role> roles = roleDao.findAll();
        for (Role role:roles)
            System.out.println(role);
    }
}
