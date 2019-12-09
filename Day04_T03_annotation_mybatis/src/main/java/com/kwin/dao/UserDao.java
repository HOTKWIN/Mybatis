package com.kwin.dao;

import com.kwin.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author kwin
 * @create 2019-12-09 20:52
 */
@CacheNamespace(blocking = true)
public interface UserDao {

    /**
     * 查找所有用户
     * @return
     */
    @Select("select * from user")
    @Results(id="userMap",
        value = {
            @Result(id=true,column = "id",property = "userId"),
            @Result(column = "username",property = "userName"),
            @Result(column = "birthday",property = "userBirthday"),
            @Result(column = "sex",property = "userSex"),
            @Result(column = "address",property = "userAddress"),
            @Result(column = "id",property = "accounts",
                many = @Many(
                        select = "com.kwin.dao.AccountDao.findByUid",
                        fetchType = FetchType.LAZY
                ))
        })
    List<User> findAll();

    /**
     * 保存用户
     * @param user
     * @return
     */
    @Insert("insert into user(id,username,birthday,sex,address) values(#{userId},#{userName},#{userBirthday},#{userSex},#{userAddress})")
    @SelectKey(keyColumn = "id",keyProperty = "userId",before = false,resultType = Integer.class,statement = {"select last_insert_id()"})
    int saveUser(User user);

    /**
     * 更新用户
     * @param user
     */
    @Update("update user set username=#{userName},birthday=#{userBirthday},sex=#{userSex},address=#{userAddress} where id=#{userId}")
    void updateUser(User user);

    /**
     * 删除用户
     * @param id
     */
    @Delete("delete from user where id=#{value}")
    void deleteUser(Integer id);

    /**
     * 根据id查询用户
     * @param userId
     * @return
     */
    @Select("select * from user where id=#{value}")
    @ResultMap("userMap")
    User findById(Integer userId);

    /**
     * 根据用户名称模糊查询
     * @param username
     * @return
     */
//    @Select("select * from user where username like '%${value}%'")
    @Select("select * from user where username like #{value}")
    @ResultMap("userMap")
    List<User> findByUserName(String username);

    /**
     * 查询总用户数量
     * @return
     */
    @Select("select count(id) from user")
    Integer findTotalUser();

}
