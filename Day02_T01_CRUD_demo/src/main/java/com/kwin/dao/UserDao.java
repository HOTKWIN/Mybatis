package com.kwin.dao;

import com.kwin.domain.QueryVo;
import com.kwin.domain.User;

import java.util.List;

/**
 * @author kwin
 * @create 2019-12-06 13:57
 */
public interface UserDao {

    /**
     * 查找所有用户
     * @return
     */
    List<User> findAll();

    /**
     * 根据id查询用户
     * @return
     */
    User findById(Integer id);

    /**
     * 保存新用户
     */
    void saveUser(User user);

    /**
     * 更新操作
     */
    void updateUser(User user);

    /**
     * 根据id删除用户
     */
    void deleteUser(Integer id);

    /**
     * 模糊查询
     */
    List<User> findByName(String username);

    /**
     * 查询总记录条数
     */
    Integer findTotal();

    /**
     * 根据用户类的包装类QueryVo来查询
     * @param queryVo
     */
    List<User> findByQueryVo(QueryVo queryVo);
}
