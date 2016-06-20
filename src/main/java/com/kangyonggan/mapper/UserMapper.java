package com.kangyonggan.mapper;

import com.kangyonggan.model.User;
import com.kangyonggan.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserMapper extends MyMapper<User> {

    void insertUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

    void deleteUserAllRoles(@Param("userId") Long userId);

    void insertUserRoles(Map<String, Object> params);
}