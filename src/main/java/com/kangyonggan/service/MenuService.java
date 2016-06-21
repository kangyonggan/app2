package com.kangyonggan.service;

import com.kangyonggan.model.Menu;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2016/05/24
 */
public interface MenuService {

    List<Menu> findMenusByCodeAndUserId(String code, Long userId);
}