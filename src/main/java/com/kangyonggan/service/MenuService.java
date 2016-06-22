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

    List<Menu> findMenusByRoleId(Long roleId);

    List<Menu> findAllMenus();

    Menu getMenu(Long id);

    Menu findMenuByCode(String code);

    void saveMenu(Menu menu);

    void updateMenu(Menu menu);

    void deleteMenu(Long id);
}