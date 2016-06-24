package com.kangyonggan.service.impl;

import com.github.pagehelper.PageHelper;
import com.kangyonggan.constants.RoleEnum;
import com.kangyonggan.constants.ShiroConstants;
import com.kangyonggan.mapper.RoleMapper;
import com.kangyonggan.mapper.UserMapper;
import com.kangyonggan.model.ShiroUser;
import com.kangyonggan.model.User;
import com.kangyonggan.service.UserService;
import com.kangyonggan.util.Digests;
import com.kangyonggan.util.Encodes;
import com.kangyonggan.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author kangyonggan
 * @since 2016/05/24
 */
@Service
@Transactional
public class UserServiceImpl extends BaseService<User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<User> searchUsers(int pageNum, int pageSize, String email, String realname) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();

        if (StringUtil.isNotEmpty(email)) {
            criteria.andLike("email", StringUtil.bothPercent(email));
        }
        if (StringUtil.isNotEmpty(realname)) {
            criteria.andLike("realname", StringUtil.bothPercent(realname));
        }
        example.setOrderByClause("login_time desc");

        PageHelper.startPage(pageNum, pageSize);
        return super.selectByExample(example);
    }

    @Override
    public ShiroUser getShiroUser() {
        try {
            return (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        } catch (Exception e) {
            return null;
        }
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
        entryptPassword(user);
        saveUser(user);
        saveUserRole(user);
    }

    @Override
    public void saveUser(User user) {
        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());
        user.setLoginTime(new Date());
        super.insertSelective(user);
    }

    @Override
    public void updateUser(User user) {
        user.setUpdatedTime(new Date());

        super.updateByPrimaryKeySelective(user);
    }

    @Override
    public void updateUserRoles(Long id, String roleIds) {
        roleMapper.deleteAllRolesByUserId(id);

        if (StringUtil.isNotEmpty(roleIds)) {
            saveUserRoles(id, roleIds);
        }
    }

    @Override
    public void deleteUser(Long id) {
        super.deleteByPrimaryKey(id);
    }

    @Override
    public User findUserByMobile(String mobile) {
        User user = new User();
        user.setMobile(mobile);

        return super.selectOne(user);
    }

    /**
     * 设定安全的密码，生成随机的salt并经过N次 sha-1 hash
     *
     * @param user
     */
    public void entryptPassword(User user) {
        byte[] salt = Digests.generateSalt(ShiroConstants.SALT_SIZE);
        user.setSalt(Encodes.encodeHex(salt));

        byte[] hashPassword = Digests.sha1(user.getPassword().getBytes(), salt, ShiroConstants.HASH_INTERATIONS);
        user.setPassword(Encodes.encodeHex(hashPassword));
    }

    /**
     * 保存“普通用户”角色
     *
     * @param user
     */
    private void saveUserRole(User user) {
        userMapper.insertUserRole(user.getId(), RoleEnum.ROLE_USER.getId());
    }

    /**
     * 批量保存用户角色
     *
     * @param userId
     * @param roleIds
     */
    private void saveUserRoles(Long userId, String roleIds) {
        userMapper.insertUserRoles(userId, Arrays.asList(roleIds.split(",")));
    }
}
