package com.kwin.dao;

import com.kwin.domain.Account;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author kwin
 * @create 2019-12-09 21:56
 */
public interface AccountDao {

    @Select("select * from account")
    @Results(id = "accountMap",
        value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "uid",property = "uid"),
            @Result(column = "money",property = "money"),
            @Result(column = "uid",
                    property = "user",
                    one = @One(select = "com.kwin.dao.UserDao.findById",
                                fetchType = FetchType.LAZY)
            )
        })
    List<Account> findAll();

    /**
     * 根据用户id查询用户下的所有账户
     * @param userId
     * @return
     */
    @Select("select * from account where uid=#{uid}")
    List<Account> findByUid(Integer userId);
}
