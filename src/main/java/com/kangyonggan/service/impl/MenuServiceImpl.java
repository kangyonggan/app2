package com.kangyonggan.service.impl;

import com.kangyonggan.mapper.MenuMapper;
import com.kangyonggan.model.Menu;
import com.kangyonggan.model.ShiroUser;
import com.kangyonggan.service.MenuService;
import com.kangyonggan.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Menu findTreeMenu() {
        ShiroUser user = userService.getShiroUser();
        List<Menu> menus = new ArrayList();
        recursionTreeList(findMenusByUserId(user.getId()), menus, 0L);

        return menus.isEmpty() ? null : menus.get(0);
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

    private List<Menu> recursionTreeList(List<Menu> from, List<Menu> toList, Long parentId) {
        if (CollectionUtils.isEmpty(from)) {
            return null;
        }

        for (int i = 0; i < from.size(); i++) {
            Menu menu = from.get(i);
            if (parentId.equals(menu.getPid())) {
                List<Menu> childrens = new ArrayList();
                menu.setChildrens(childrens);
                toList.add(menu);
                recursionTreeList(from, childrens, menu.getId());
            }
        }
        return toList;
    }
}
