package com.kangyonggan.controller.sys;

import com.github.pagehelper.PageInfo;
import com.kangyonggan.constants.AppConstants;
import com.kangyonggan.model.User;
import com.kangyonggan.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 用户管理
 *
 * @author kangyonggan
 * @since 16/6/21
 */
@Controller
@RequestMapping("sys/user")
public class SysUserController {

    private static final String PATH_ROOT = "sys/user/";
    private static final String PATH_LIST = PATH_ROOT + "list";

    @Autowired
    private UserService userService;

    /**
     * 用户列表
     *
     * @param pageNum
     * @param realname
     * @param email
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("sys-user")
    public String list(@RequestParam(value = "p", required = false, defaultValue = "1") int pageNum,
                       @RequestParam(value = "realname", required = false, defaultValue = "") String realname,
                       @RequestParam(value = "email", required = false, defaultValue = "") String email,
                       Model model) {
        List<User> users = userService.searchUsers(pageNum, AppConstants.PAGE_SIZE, realname, email);
        PageInfo<User> page = new PageInfo(users);

        model.addAttribute("page", page);
        return PATH_LIST;
    }

}
