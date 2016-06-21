package com.kangyonggan.controller.sys;

import com.github.pagehelper.PageInfo;
import com.kangyonggan.constants.AppConstants;
import com.kangyonggan.model.Role;
import com.kangyonggan.model.User;
import com.kangyonggan.service.RoleService;
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
 * 角色管理
 *
 * @author kangyonggan
 * @since 16/6/21
 */
@Controller
@RequestMapping("sys/role")
public class SysRoleController {

    private static final String PATH_ROOT = "sys/role/";
    private static final String PATH_LIST = PATH_ROOT + "list";

    @Autowired
    private RoleService roleService;

    /**
     * 角色列表
     *
     * @param pageNum
     * @param name
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("sys-user")
    public String list(@RequestParam(value = "p", required = false, defaultValue = "1") int pageNum,
                       @RequestParam(value = "name", required = false, defaultValue = "") String name,
                       Model model) {
        List<Role> roles = roleService.searchRoles(pageNum, AppConstants.PAGE_SIZE, name);
        PageInfo<Role> page = new PageInfo(roles);

        model.addAttribute("page", page);
        return PATH_LIST;
    }

}
