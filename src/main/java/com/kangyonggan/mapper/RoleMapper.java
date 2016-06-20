package com.kangyonggan.mapper;

import com.kangyonggan.model.Role;
import com.kangyonggan.util.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RoleMapper extends MyMapper<Role> {
    /**
     * 查找用户的所有角色
     *
     * @param userId
     * @return
     */
    List<Role> selectRolesByUserId(Long userId);

    void insertRoleMenus(Map<String, Object> params);

    void deleteRoleMenus(Map<String, Object> params);
}