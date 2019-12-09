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

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author kwin
 * @create 2019-12-06 15:14
 */
public class MybatisCRUDTest {

    InputStream in = null;
    SqlSessionFactory factory = null;
    SqlSession sqlSession = null;
    UserDao userDao = null;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        sqlSession = factory.openSession();
        userDao = sqlSession.getMapper(UserDao.class);
    }

    @After
    public void destroy() throws IOException {
        sqlSession.commit();

        sqlSession.close();
        in.close();
    }

    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();
        for (User user:users){
            System.out.println(user);
        }
    }

    /**
     * 测试根据id查询用户
     */
    @Test
    public void testFindById(){
        User user = userDao.findById(48);
        System.out.println(user);
    }

    /**
     * 测试保存新用户
     */
    @Test
    public void testSaveUser(){
        User user = new User();
        user.setUserName("kwin");
        user.setUserBirthday(new Date());
        user.setUserSex("男");
        user.setUserAddress("白云区");

        System.out.println("保存前：" + user);
        userDao.saveUser(user);
        System.out.println("保存后：" + user);
    }

    /**
     * 测试更新用户
     */
    @Test
    public void testUpdateUser(){
        User user = new User();
        user.setUserId(63);
        user.setUserName("kwin666");

        userDao.updateUser(user);
    }

    /**
     * 测试根据id删除用户
     */
    @Test
    public void testDeleteUser(){
        userDao.deleteUser(63);
    }

    /**
     * 测试模糊查询
     */
    @Test
    public void testFindByName(){
        List<User> users = userDao.findByName("%王%");
        for (User user:users){
            System.out.println(user);
        }
    }

    /**
     * 测试查询总记录条数
     */
    @Test
    public void testFindByTotal(){
        Integer total = userDao.findTotal();
        System.out.println(total);
    }

    /**
     * 测试使用QueryVo作为查询条件
     */
    @Test
    public void testFindByQueryVo(){
        QueryVo queryVo = new QueryVo();
        User user = new User();
        user.setUserName("%王%");
        queryVo.setUser(user);

        List<User> users = userDao.findByQueryVo(queryVo);
        for (User u:users){
            System.out.println(u);
        }
    }
}
