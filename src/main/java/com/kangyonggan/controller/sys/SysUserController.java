package com.kangyonggan.controller.sys;

import com.github.pagehelper.PageInfo;
import com.kangyonggan.constants.AppConstants;
import com.kangyonggan.model.Role;
import com.kangyonggan.model.User;
import com.kangyonggan.model.ValidationResponse;
import com.kangyonggan.service.RoleService;
import com.kangyonggan.service.UserService;
import com.kangyonggan.util.Collections3;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    private static final String PATH_FORM_MODAL = PATH_ROOT + "form-modal";
    private static final String PATH_ROLES_MODAL = PATH_ROOT + "roles-modal";
    private static final String PATH_DETAIL_MODAL = PATH_ROOT + "detail-modal";
    private static final String PATH_TABLE_TR = PATH_ROOT + "table-tr";

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 用户列表
     *
     * @param pageNum
     * @param email
     * @param realname
     * @param model
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @RequiresPermissions("sys-user")
    public String list(@RequestParam(value = "p", required = false, defaultValue = "1") int pageNum,
                       @RequestParam(value = "email", required = false, defaultValue = "") String email,
                       @RequestParam(value = "realname", required = false, defaultValue = "") String realname,
                       Model model) {
        List<User> users = userService.searchUsers(pageNum, AppConstants.PAGE_SIZE, email, realname);
        PageInfo<User> page = new PageInfo(users);

        model.addAttribute("page", page);
        return PATH_LIST;
    }

    /**
     * 锁定/解锁
     *
     * @param id
     * @param isLocked
     * @param model
     * @return
     */
    @RequestMapping(value = "{isLocked:\\bunlock\\b|\\block\\b}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    @RequiresPermissions("sys-user")
    public String lock(@RequestParam("id") Long id, @PathVariable("isLocked") String isLocked, Model model) {
        User user = userService.getUser(id);
        user.setIsLocked((byte) (isLocked.equals("unlock") ? 0 : 1));
        userService.updateUser(user);

        model.addAttribute("item", user);
        return PATH_TABLE_TR;
    }

    /**
     * 添加用户
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    @RequiresPermissions("sys-user")
    public String create(Model model) {
        model.addAttribute("item", new User());
        return PATH_FORM_MODAL;
    }

    /**
     * 保存用户
     *
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys-user")
    public ValidationResponse save(@ModelAttribute("user") @Valid User user, BindingResult result) {
        ValidationResponse res = new ValidationResponse(AppConstants.FAIL);

        if (!result.hasErrors()) {
            user.setIsVerified((byte) 1);
            userService.saveUserAndRole(user);
            res.setStatus(AppConstants.SUCCESS);
        }

        return res;
    }

    /**
     * 编辑用户
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    @RequiresPermissions("sys-user")
    public String create(@RequestParam("id") Long id, Model model) {
        model.addAttribute("item", userService.getUser(id));
        return PATH_FORM_MODAL;
    }

    /**
     * 更新用户
     *
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys-user")
    public ValidationResponse update(@ModelAttribute("user") @Valid User user, BindingResult result) {
        ValidationResponse res = new ValidationResponse(AppConstants.FAIL);

        if (!result.hasErrors()) {
            userService.updateUser(user);
            res.setStatus(AppConstants.SUCCESS);
        }

        return res;
    }

    /**
     * 查看用户详情
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("sys-user")
    public String detail(@RequestParam("id") Long id, Model model) {
        model.addAttribute("item", userService.getUser(id));
        return PATH_DETAIL_MODAL;
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys-user")
    public ValidationResponse delete(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return new ValidationResponse(AppConstants.SUCCESS);
    }

    /**
     * 设置角色
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "roles", method = RequestMethod.GET)
    @RequiresPermissions("sys-user")
    public String roles(@RequestParam("id") Long id, Model model) {
        List<Role> user_roles = roleService.findRolesByUserId(id);
        user_roles = Collections3.extractToList(user_roles, "code");
        List<Role> all_roles = roleService.findAllRoles();

        model.addAttribute("id", id);
        model.addAttribute("user_roles", user_roles);
        model.addAttribute("all_roles", all_roles);
        return PATH_ROLES_MODAL;
    }

    /**
     * 保存角色
     *
     * @param id
     * @param roles
     * @return
     */
    @RequestMapping(value = "roles", method = RequestMethod.POST)
    @RequiresPermissions("sys-user")
    @ResponseBody
    public ValidationResponse updateUserRoles(@RequestParam(value = "id", required = true) Long id,
                                              @RequestParam(value = "roles", defaultValue = "") String roles) {
        userService.updateUserRoles(id, roles);
        return new ValidationResponse(AppConstants.SUCCESS);
    }

}
