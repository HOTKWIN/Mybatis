package com.kwin.dao;

import com.kwin.domain.Account;

import java.util.List;

/**
 * @author kwin
 * @create 2019-12-09 14:57
 */
public interface AccountDao {

    /**
     * 查找所有账户，延迟查找所属用户
     * @return
     */
    List<Account> findAll();

    /**
     * 根据用户id查询账户
     * @return
     */
    List<Account> findByUid(Integer uid);
}

