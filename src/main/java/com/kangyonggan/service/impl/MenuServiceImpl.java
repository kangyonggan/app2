package com.kangyonggan.service.impl;

import com.kangyonggan.mapper.MenuMapper;
import com.kangyonggan.model.Menu;
import com.kangyonggan.service.MenuService;
import com.kangyonggan.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

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

    @Override
    public List<Menu> findMenusByUserId(Long userId) {
        return menuMapper.findMenusByUserId(userId);
    }

    @Override
    public List<Menu> findMenusByPid(Long pid) {
        Example example = new Example(Menu.class);
        example.createCriteria().andEqualTo("pid", pid).andEqualTo("isDeleted", 0);
        example.setOrderByClause("sort asc");

        return super.selectByExample(example);
    }

    @Override
    public Menu findMenuByCode(String code) {
        Menu menu = new Menu();
        menu.setCode(code);
        menu.setIsDeleted((byte) 0);

        return super.selectOne(menu);
    }
}
