package com.kangyonggan.controller;

import com.kangyonggan.constants.AppConstants;
import com.kangyonggan.model.User;
import com.kangyonggan.model.ValidationResponse;
import com.kangyonggan.service.UserService;
import com.kangyonggan.util.FileUpload;
import com.kangyonggan.util.Images;
import com.kangyonggan.util.StringUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author kangyonggan
 * @since 16/6/20
 */
@Controller
@RequestMapping("user")
@Log4j2
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

    /**
     * 保存头像修改
     *
     * @param avatar
     * @return
     */
    @RequestMapping(value = "avatar", method = RequestMethod.POST)
    @ResponseBody
    public ValidationResponse avatar(@RequestParam(value = "avatar") MultipartFile avatar) throws Exception {
        ValidationResponse res = new ValidationResponse(AppConstants.SUCCESS);
        try {
            String fileName = FileUpload.upload(avatar);
            User user = userService.getUser(userService.getShiroUser().getId());

            String large = Images.large(fileName);
            user.setLargeAvatar(large);
            res.setMessage(large);
            String middle = Images.middle(fileName);
            user.setMediumAvatar(middle);
            String small = Images.small(fileName);
            user.setSmallAvatar(small);

            userService.updateUser(user);
        } catch (Exception e) {
            log.error(e.getMessage());
            res.setStatus(AppConstants.FAIL);
            res.setMessage(e.getMessage());
        }
        return res;
    }

}
