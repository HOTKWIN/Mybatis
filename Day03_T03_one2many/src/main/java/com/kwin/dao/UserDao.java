package com.kwin.dao;

import com.kwin.domain.User;

import java.util.List;

/**
 * @author kwin
 * @create 2019-12-09 13:15
 */
public interface UserDao {

    /**
     * 查询所有
     * @return
     */
    List<User> findAll();

}
