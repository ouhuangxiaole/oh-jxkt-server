package com.ouhuang.mapper;

import com.ouhuang.entity.LoginReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

    @Select("select * from user where mobile = #{mobile} and password = #{password}")
    LoginReq login(LoginReq loginReq);

    @Select("select exists(select 1 from user where mobile = #{mobile})")
    Boolean exist(String mobile);

    @Select("select * from user where mobile = #{mobile}")
    LoginReq select(LoginReq loginReq);
}
