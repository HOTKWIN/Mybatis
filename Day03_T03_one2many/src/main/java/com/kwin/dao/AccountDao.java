package com.kwin.dao;

import com.kwin.domain.Account;
import com.kwin.domain.AccountUser;
import com.kwin.domain.User;

import java.util.List;

/**
 * @author kwin
 * @create 2019-12-09 14:57
 */
public interface AccountDao {

    /**
     * 查询所有用户,同时获取所属用户的用户名和地址
     * @return
     */
    List<AccountUser> findAll();

    /**
     * 查询所有用户，同时获取所属用户的所有信息
     * @return
     */
    List<Account> findAll2();
}

