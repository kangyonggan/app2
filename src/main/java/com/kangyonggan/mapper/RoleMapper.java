package com.kangyonggan.mapper;

import com.kangyonggan.model.Role;
import com.kangyonggan.util.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper extends MyMapper<Role> {

    List<Role> selectRolesByUserId(Long userId);

    void deleteAllRolesByUserId(Long userId);
}