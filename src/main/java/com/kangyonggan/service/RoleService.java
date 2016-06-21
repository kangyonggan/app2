package com.kangyonggan.service;

import com.kangyonggan.model.Role;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2016/05/24
 */
public interface RoleService {

    List<Role> findRolesByUserId(Long userId);

}