package com.kangyonggan.controller.sys;

import com.github.pagehelper.PageInfo;
import com.kangyonggan.constants.AppConstants;
import com.kangyonggan.model.User;
import com.kangyonggan.model.ValidationResponse;
import com.kangyonggan.service.UserService;
import com.kangyonggan.util.StringUtil;
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
    private static final String PATH_TABLE_TR = PATH_ROOT + "table-tr";

    @Autowired
    private UserService userService;

    /**
     * 用户列表
     *
     * @param pageNum
     * @param realname
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("sys-user")
    public String list(@RequestParam(value = "p", required = false, defaultValue = "1") int pageNum,
                       @RequestParam(value = "realname", required = false, defaultValue = "") String realname,
                       Model model) {
        List<User> users = userService.searchUsers(pageNum, AppConstants.PAGE_SIZE, realname);
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
    @RequestMapping(value = "{id:[\\d]+}/locked/{isLocked:[01]}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    @RequiresPermissions("sys-user")
    public String lock(@PathVariable("id") Long id, @PathVariable("isLocked") Byte isLocked, Model model) {
        User user = userService.getUser(id);
        user.setIsLocked(isLocked);
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
        ValidationResponse res = new ValidationResponse(AppConstants.SUCCESS);

        if (!result.hasErrors()) {
            userService.saveUserAndRole(user);
        } else {
            res.setStatus(AppConstants.FAIL);
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
    @RequestMapping(value = "{id:[\\d]+}/edit", method = RequestMethod.GET)
    @RequiresPermissions("sys-user")
    public String create(@PathVariable("id") Long id, Model model) {
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
    @RequestMapping(value = "{id:[\\d]+}/update", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys-user")
    public ValidationResponse update(@ModelAttribute("user") @Valid User user, BindingResult result) {
        ValidationResponse res = new ValidationResponse(AppConstants.SUCCESS);

        if (!result.hasErrors()) {
            userService.updateUser(user);
        } else {
            res.setStatus(AppConstants.FAIL);
        }

        return res;
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/delete", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys-user")
    public ValidationResponse delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ValidationResponse(AppConstants.SUCCESS);
    }

    /**
     * 校验电子邮箱唯一性
     *
     * @param email
     * @param oldEmail
     * @return
     */
    @RequestMapping(value = "/verify-email", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys-user")
    public boolean verifyEmail(@RequestParam String email, @RequestParam String oldEmail) {
        if (oldEmail.equals(email)) {
            return true;
        }
        return userService.findUserByEmail(email) == null;
    }

    /**
     * 校验手机号唯一性
     *
     * @param mobile
     * @param oldMobile
     * @return
     */
    @RequestMapping(value = "/verify-mobile", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys-user")
    public boolean verifyMobile(@RequestParam String mobile, @RequestParam String oldMobile) {
        if (oldMobile.equals(mobile)) {
            return true;
        }
        if (StringUtil.isEmpty(mobile)) {
            return true;
        }
        return userService.findUserByMobile(mobile) == null;
    }

}
