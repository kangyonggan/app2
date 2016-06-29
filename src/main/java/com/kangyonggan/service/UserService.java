package com.kangyonggan.service;

import com.kangyonggan.model.ShiroUser;
import com.kangyonggan.model.Token;
import com.kangyonggan.model.User;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2016/05/24
 */
public interface UserService {

    List<User> searchUsers(int pageNum, int pageSize, String email, String realname);

    ShiroUser getShiroUser();

    User getUser(Long id);

    User findUserByEmail(String email);

    User findUserByMobile(String mobile);

    void saveUserAndRole(User user);

    void saveUser(User user);

    void updateUser(User user);

    void updateUserRoles(Long id, String roleIds);

    void deleteUser(Long id);

    void updateUserEmailVerified(Token token);
}