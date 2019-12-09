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
 * @create 2019-12-06 14:04
 */
public class MysqlDemoTest {

    public static void main(String[] args) throws IOException {

        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.使用工厂生产sqlSession对象
        SqlSession sqlSession = factory.openSession();
        //4.使用sqlSession对象创建Dao接口的代理对象
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        //5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        //6.输出结果
        for (User user:users)
            System.out.println(user);
        //7.释放资源
        sqlSession.close();
        in.close();
    }
}
