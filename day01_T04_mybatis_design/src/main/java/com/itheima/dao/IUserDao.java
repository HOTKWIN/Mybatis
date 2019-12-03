package com.itheima.dao;

import com.itheima.domain.User;
import com.itheima.mybatis.annotations.Select;

import java.util.List;

/**
 * @author kwin
 * @create 2019-12-02 14:01
 */
public interface IUserDao {
    @Select("select * from user")
    List<User> findAll();
}
