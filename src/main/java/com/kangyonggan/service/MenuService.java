package com.kangyonggan.service;

import com.kangyonggan.model.Menu;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2016/05/24
 */
public interface MenuService {

    List<Menu> findAllMenusByPage(int pageNum);

    List<Menu> findMenusByUserId(Long userId);

    Menu getMenu(Long id);

    Menu findMenuByName(String name);

    int saveMenu(Menu menu);

    int updateMenu(Menu menu);

    int deleteMenu(Long id);
}