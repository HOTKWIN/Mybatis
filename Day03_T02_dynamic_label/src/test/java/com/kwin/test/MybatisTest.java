package com.kwin.test;

import com.kwin.dao.UserDao;
import com.kwin.domain.QueryVo;
import com.kwin.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
     * 测试根据条件查询
     * 使用for 和 where标签
     */
    @Test
    public void testFindByCondition(){
        User user = new User();
        user.setUsername("老王");
//        user.setSex("男");

        List<User> users = userDao.findUserByCondition(user);
        for (User u:users)
            System.out.println(u);
    }

    /**
     * 根据ids进行范围查询
     * 使用foreach标签
     */
    @Test
    public void testFindUserInIds(){
        QueryVo queryVo = new QueryVo();
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(41);
        list.add(42);
        list.add(43);
        queryVo.setIds(list);

        List<User> users = userDao.findUserInIds(queryVo);
        for (User u:users)
            System.out.println(u);
    }
}
