package com.zx.shiro2.dao;

import com.zx.shiro2.beans.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    User getUserByUsername(String username);
}
