package com.kwin.dao;

import com.kwin.domain.User;

import java.util.List;

/**
 * @author kwin
 * @create 2019-12-06 13:57
 */
public interface UserDao {

    List<User> findAll();
}
