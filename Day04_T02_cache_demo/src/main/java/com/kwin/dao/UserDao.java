package com.kwin.dao;

import com.kwin.domain.User;

/**
 * @author kwin
 * @create 2019-12-09 20:07
 */
public interface UserDao {

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User findById(int id);

    /**
     * 更新操作
     */
    void updateUser(User user);
}
