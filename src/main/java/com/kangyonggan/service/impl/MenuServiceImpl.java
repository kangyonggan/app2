package com.kangyonggan.service.impl;

import com.kangyonggan.mapper.MenuMapper;
import com.kangyonggan.model.Menu;
import com.kangyonggan.service.MenuService;
import com.kangyonggan.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Menu> findMenusByCodeAndUserId(String code, Long userId) {
        return menuMapper.selectMenusByCodeAndUserId(String.format("%%%s%%", StringUtil.filter(code)), userId);
    }
}
