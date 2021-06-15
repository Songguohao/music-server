package com.sou.music.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminDao {

    /**
     * 验证密码是否正确
     */
    int verifyPassword(@Param("username") String username, @Param("password") String password);
}
