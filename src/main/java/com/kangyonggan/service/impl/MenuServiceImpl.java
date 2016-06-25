package com.kangyonggan.service.impl;

import com.kangyonggan.mapper.MenuMapper;
import com.kangyonggan.model.Menu;
import com.kangyonggan.service.MenuService;
import com.kangyonggan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author kangyonggan
 * @since 2016/05/24
 */
@Service
@Transactional
public class MenuServiceImpl extends BaseService<Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private UserService userService;

    @Override
    public List<Menu> findMenusByUserId(Long userId) {
        return menuMapper.selectMenusByUserId(userId);
    }

    @Override
    public List<Menu> findMenusByPid(Long pid) {
        return menuMapper.selectMenusByPid(pid, userService.getShiroUser().getId());
    }

    @Override
    public List<Menu> findMenusByRoleId(Long roleId) {
        return menuMapper.selectMenusByRoleId(roleId);
    }

    @Override
    public List<Menu> findAllMenus() {
        Menu menu = new Menu();
        menu.setIsDeleted((byte) 0);

        return super.select(menu);
    }

    @Override
    public Menu getMenu(Long id) {
        return super.selectByPrimaryKey(id);
    }

    @Override
    public Menu findMenuByCode(String code) {
        Menu menu = new Menu();
        menu.setCode(code);
        menu.setIsDeleted((byte) 0);

        return super.selectOne(menu);
    }

    @Override
    public void saveMenu(Menu menu) {
        menu.setCreatedTime(new Date());
        menu.setUpdatedTime(new Date());

        super.insertSelective(menu);
    }

    @Override
    public void updateMenu(Menu menu) {
        menu.setUpdatedTime(new Date());

        super.updateByPrimaryKeySelective(menu);
    }

    @Override
    public void deleteMenu(Long id) {
        super.deleteByPrimaryKey(id);
    }
}
