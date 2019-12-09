package com.kwin.dao;

import com.kwin.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author kwin
 * @create 2019-12-06 14:31
 */
public interface UserDao {

    /**
     * 查找所有用户
     * @return
     */
    @Select("select * from user")
    List<User> findAll();
}
