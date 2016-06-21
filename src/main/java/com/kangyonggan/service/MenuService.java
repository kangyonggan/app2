package com.kangyonggan.service;

import com.kangyonggan.model.Menu;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2016/05/24
 */
public interface MenuService {

    List<Menu> findMenusByUserId(Long userId);

    List<Menu> findMenusByPid(Long pid);

    Menu findMenuByCode(String code);
}