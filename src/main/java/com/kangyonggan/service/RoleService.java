package com.kangyonggan.service;

import com.kangyonggan.model.Role;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2016/05/24
 */
public interface RoleService {

    List<Role> searchRoles(int pageNum, int pageSize, String name);

    List<Role> findRolesByUserId(Long userId);

    Role getRole(Long id);

    Role findRoleByCode(String code);

    void saveRole(Role role);

    void updateRole(Role role);

    void deleteRole(Long id);
}