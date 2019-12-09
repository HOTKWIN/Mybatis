package com.kwin.dao;

import com.kwin.domain.User;

import java.util.List;

/**
 * @author kwin
 * @create 2019-12-09 13:15
 */
public interface UserDao {

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User findById(Integer id);

    /**
     * 查找所有用户
     * @return
     */
    List<User> findAll();

}
