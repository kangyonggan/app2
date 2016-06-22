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
     * 电子邮箱唯一性校验
     *
     * @param email
     * @param isEdit
     * @return
     */
    @RequestMapping(value = "/verify-email", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys-user")
    public boolean verifyEmail(@RequestParam String email, @RequestParam String isEdit) {
        if ("yes".equals(isEdit)) {
            return true;
        }
        return userService.findUserByEmail(email) == null;
    }

    /**
     * 手机号唯一性校验
     *
     * @param mobile
     * @param isEdit
     * @return
     */
    @RequestMapping(value = "/verify-mobile", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys-user")
    public boolean verifyMobile(@RequestParam String mobile, @RequestParam String isEdit) {
        if ("yes".equals(isEdit)) {
            return true;
        }
        if (StringUtil.isEmpty(mobile)) {
            return true;
        }
        return userService.findUserByMobile(mobile) == null;
    }


}
