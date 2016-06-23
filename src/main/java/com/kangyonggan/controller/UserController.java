package com.kangyonggan.controller;

import com.kangyonggan.model.User;
import com.kangyonggan.service.UserService;
import com.kangyonggan.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author kangyonggan
 * @since 16/6/20
 */
@Controller
@RequestMapping("user")
public class UserController {

    private static final String PATH_ROOT = "web/user/";
    private static final String PATH_INDEX = PATH_ROOT + "index";

    @Autowired
    private UserService userService;

    /**
     * Ta的主页
     *
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}", method = RequestMethod.GET)
    public String index(@PathVariable("id") Long id, Model model) {
        User user = userService.getUser(id);
        if (user == null) {
            return "redirect:/404";
        }

        model.addAttribute("user", user);
        return PATH_INDEX;
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
