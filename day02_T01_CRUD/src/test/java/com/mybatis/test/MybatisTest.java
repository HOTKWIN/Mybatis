package com.mybatis.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.QueryVo;
import com.itheima.domain.User;
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
 * @create 2019-12-02 19:52
 */
public class MybatisTest {

    private InputStream in;
    private SqlSession session;
    private IUserDao userDao;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        userDao = session.getMapper(IUserDao.class);
    }

    @After
    public void destroy() throws IOException {
        session.commit();
        session.close();
        in.close();
    }


    /**
     * 测试查找所有用户操作
     * @throws IOException
     */
    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();
        for (User user:users)
            System.out.println(user);
    }


    /**
     * 测试保存用户操作
     */
    @Test
    public void testSaveUser(){
        User user = new User();
        user.setUsername("kwin3");
        user.setAddress("广东广州");
        user.setSex("男");
        user.setBirthday(new Date());

        System.out.println("保存操作之前" + user);
        userDao.saveUser(user);
        System.out.println("保存操作之后" + user);
    }


    /**
     * 测试更新操作
     */
    @Test
    public void testUpdateUser(){
        User user = new User();
        user.setId(49);
        user.setUsername("kwin1");
        user.setAddress("广东广州");
        user.setSex("男");
        user.setBirthday(new Date());

        userDao.updateUser(user);
    }


    /**
     * 测试删除操作
     */
    @Test
    public void testDelete(){
        userDao.deleteUser(49);
    }

    /**
     * 测试查询单个用户的方法
     */
    @Test
    public void testFindById(){
        User user = userDao.findById(48);
        System.out.println(user);
    }

    /**
     * 测试模糊查询操作
     */
    @Test
    public void testFindByName(){
//        List<User> users = userDao.findByName("%王%");
        List<User> users = userDao.findByName("王");
        for (User user:users)
            System.out.println(user);
    }

    /**
     * 测试查询总记录条数
     */
    @Test
    public void testFindTotal(){
        int total = userDao.findTotal();
        System.out.println(total);
    }

    /**
     * 测试使用QueryVo作为查询条件
     */
    @Test
    public void testFindByVo(){
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUsername("%王%");
        vo.setUser(user);

        List<User> users = userDao.findByVo(vo);
        for (User u:users)
            System.out.println(u);
    }
}








