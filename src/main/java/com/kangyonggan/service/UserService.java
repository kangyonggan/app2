package com.kangyonggan.service;

import com.kangyonggan.model.ShiroUser;
import com.kangyonggan.model.User;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2016/05/24
 */
public interface UserService {

    List<User> searchUsers(int pageNum, int pageSize, String realname);

    ShiroUser getShiroUser();

    User getUser(Long id);

    User findUserByEmail(String email);

    void saveUserAndRole(User user);

    int saveUser(User user);

    void updateUser(User user);

    User findUserByMobile(String mobile);
}