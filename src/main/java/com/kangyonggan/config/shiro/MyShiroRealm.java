package com.kangyonggan.config.shiro;

import com.kangyonggan.constants.ShiroConstants;
import com.kangyonggan.exception.EmailNotVerifiedException;
import com.kangyonggan.model.Menu;
import com.kangyonggan.model.Role;
import com.kangyonggan.model.ShiroUser;
import com.kangyonggan.model.User;
import com.kangyonggan.service.MenuService;
import com.kangyonggan.service.RoleService;
import com.kangyonggan.service.UserService;
import com.kangyonggan.util.Encodes;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

/**
 * @author kangyonggan
 * @since 16/5/15
 */
@Log4j2
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    /**
     * 权限认证，为当前登录的Subject授予角色和权限
     * 经测试：本例中该方法的调用时机为需授权资源被访问时
     * 经测试：并且每次访问需授权资源时都会执行该方法中的逻辑，这表明本例中默认并未启用AuthorizationCache
     * 经测试：如果连续访问同一个URL（比如刷新），该方法不会被重复调用，Shiro有一个时间间隔（也就是cache时间，在ehcache-shiro.xml中配置），超过这个时间间隔再刷新页面，该方法会被执行
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        ShiroUser shiroUser = (ShiroUser) principalCollection.getPrimaryPrincipal();
        log.info("Shiro权限认证, email={}", shiroUser.getEmail());
        List<Role> roles = roleService.findRolesByUserId(shiroUser.getId());
        List<Menu> menus = menuService.findMenusByUserId(shiroUser.getId());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 基于Role的权限信息
        for (Role role : roles) {
            info.addRole(role.getCode());
        }
        // 基于Permission的权限信息
        for (Menu menu : menus) {
            info.addStringPermission(menu.getCode());
        }

        return info;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken) throws AuthenticationException {
        //UsernamePasswordToken对象用来存放提交的登录信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        log.info("Shiro登录认证, email={}", token.getUsername());

        String email = token.getUsername();
        User user = userService.findUserByEmail(email);

        if (null == user) {
            throw new UnknownAccountException();
        }

        if (user.getIsLocked() == 1) {
            // 锁定超过30分钟后, 再次登录将解锁
            if (new Date().getTime() - user.getErrorPasswordTime().getTime() < 30 * 60 * 1000) {
                throw new LockedAccountException();
            }
        }

        if (user.getIsVerified() == 0) {
            throw new EmailNotVerifiedException();
        }

        byte[] salt = Encodes.decodeHex(user.getSalt());
        ShiroUser shiroUser = new ShiroUser();
        shiroUser.setId(user.getId());
        shiroUser.setEmail(user.getEmail());
        shiroUser.setRealname(user.getRealname());
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(shiroUser,
                user.getPassword(), ByteSource.Util.bytes(salt), getName());

        return simpleAuthenticationInfo;
    }

    /**
     * 设定Password校验的Hash算法与迭代次数.
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(ShiroConstants.HASH_ALGORITHM);
        matcher.setHashIterations(ShiroConstants.HASH_INTERATIONS);

        setCredentialsMatcher(matcher);
    }
}
