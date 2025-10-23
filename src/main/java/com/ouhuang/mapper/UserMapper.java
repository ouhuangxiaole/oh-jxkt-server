package com.ouhuang.mapper;

import com.ouhuang.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("select * from user where user_id = #{user_id}")
    User select(Long user_id);

    @Select("select * from user where mobile = #{mobile}")
    User selectByMobile(String mobile);

    @Delete("delete from user where user_id = #{user_id}")
    void delete(Long user_id);

    @Insert("insert into user(username, password, mobile, email) " +
            "values (#{username}, #{password}, #{mobile}, #{email})")
    void insert(User user);

    @Update("update user set " +
            "username = #{username}, " +
            "email = #{email}, " +
            "update_time = now() " +
            "where user_id = #{user_id}")
    void update(User user);

    @Update("update user set avatar = #{avatar}, update_time = now() where user_id = #{user_id}")
    void updateAvatar(User user);
}
