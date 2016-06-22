package com.kangyonggan.mapper;

import com.kangyonggan.model.User;
import com.kangyonggan.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends MyMapper<User> {

    void insertUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

    void insertUserRoles(@Param("userId") Long userId, @Param("roleIds") List<String> roleIds);
}