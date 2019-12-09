package com.kwin.dao;

import com.kwin.domain.QueryVo;
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

    /**
     * 根据传入的条件查询
     * @param user
     * @return
     */
    List<User> findUserByCondition(User user);

    /**
     * 根据QueryVo中的id集合查询用户
     * @param queryVo
     * @return
     */
    List<User> findUserInIds(QueryVo queryVo);
}
