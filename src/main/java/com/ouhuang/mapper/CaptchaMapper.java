package com.ouhuang.mapper;

import com.ouhuang.entity.Captcha;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CaptchaMapper {

    @Select("select * from captcha order by rand() limit 1")
    Captcha select();

    @Insert("insert into captcha(`key`, url) values (#{key}, #{url})")
    void insert(Captcha captcha);

    @Update("update captcha set url = #{url} where captcha_id = #{id}")
    void update(Integer id, String url);

    @Select("select * from captcha where `key` = #{key} and md5 = #{code}")
    Captcha verify(String code, String key);
}
