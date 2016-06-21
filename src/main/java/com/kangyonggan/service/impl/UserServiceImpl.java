package com.kangyonggan.service.impl;

import com.github.pagehelper.PageHelper;
import com.kangyonggan.model.ShiroUser;
import com.kangyonggan.model.User;
import com.kangyonggan.service.UserService;
import com.kangyonggan.util.StringUtil;
import org.apache.shiro.SecurityUtils;
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
public class UserServiceImpl extends BaseService<User> implements UserService {

    @Override
    public List<User> searchUsers(int pageNum, int pageSize, String realname) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();

        if (StringUtil.isNotEmpty(realname)) {
            criteria.andLike("realname", "%" + realname + "%");
        }
        example.setOrderByClause("login_time desc");

        PageHelper.startPage(pageNum, pageSize);
        return super.selectByExample(example);
    }

    @Override
    public ShiroUser getShiroUser() {
        ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        return shiroUser;
    }

    @Override
    public User getUser(Long id) {
        return super.selectByPrimaryKey(id);
    }

    @Override
    public User findUserByEmail(String email) {
        User user = new User();
        user.setEmail(email);
        return super.selectOne(user);
    }

    @Override
    public void saveUserAndRole(User user) {
        // TODO
    }

    @Override
    public void updateUser(User user) {
        user.setUpdatedTime(new Date());

        super.updateByPrimaryKey(user);
    }
}
