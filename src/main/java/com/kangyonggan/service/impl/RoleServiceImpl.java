package com.kangyonggan.service.impl;

import com.github.pagehelper.PageHelper;
import com.kangyonggan.mapper.RoleMapper;
import com.kangyonggan.model.Role;
import com.kangyonggan.service.RoleService;
import com.kangyonggan.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author kangyonggan
 * @since 2016/05/24
 */
@Service
@Transactional
public class RoleServiceImpl extends BaseService<Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> searchRoles(int pageNum, int pageSize, String name) {
        Example example = new Example(Role.class);
        if (StringUtil.isNotEmpty(name)) {
            example.createCriteria().andLike("name", StringUtil.bothPercent(name));
        }
        example.setOrderByClause("id desc");

        PageHelper.startPage(pageNum, pageSize);
        return super.selectByExample(example);
    }

    @Override
    public List<Role> findRolesByUserId(Long userId) {
        return roleMapper.selectRolesByUserId(userId);
    }

    @Override
    public Role getRole(Long id) {
        return super.selectByPrimaryKey(id);
    }

    @Override
    public Role findRoleByCode(String code) {
        Role role = new Role();
        role.setCode(code);

        return super.selectOne(role);
    }

    @Override
    public void saveRole(Role role) {
        role.setCreatedTime(new Date());
        role.setUpdatedTime(new Date());

        super.insertSelective(role);
    }

    @Override
    public void updateRole(Role role) {
        role.setUpdatedTime(new Date());

        super.updateByPrimaryKeySelective(role);
    }

    @Override
    public void deleteRole(Long id) {
        super.deleteByPrimaryKey(id);
    }
}
