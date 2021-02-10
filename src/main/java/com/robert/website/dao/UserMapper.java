package com.robert.website.dao;

import com.robert.website.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * add new user
     *
     * @param user
     */
    @Insert("Insert Into sys_user(username, password,user_role) Values(#{username}, #{password},#{userRole})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    void insert(User user);

    /**
     * check userdata according to username
     *
     * @param username
     * @return userdata
     */
    @Select("Select user_id,username, password,user_role From sys_user Where username=#{username}")
    User selectByUsername(String username);

    /**
     * Retrieve registered users from db
     *
     * @return List of all registered users
     */
    @Select("select * from sys_user")
    List<User> findAll();
}
