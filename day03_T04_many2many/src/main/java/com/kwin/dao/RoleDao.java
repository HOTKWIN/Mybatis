package com.kwin.dao;

import com.kwin.domain.Role;

import java.util.List;

/**
 * @author kwin
 * @create 2019-12-09 16:02
 */
public interface RoleDao {

    /**
     * 查询所有角色
     * @return
     */
    List<Role> findAll();
}
