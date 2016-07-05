package com.kangyonggan.controller.pits;

import com.kangyonggan.model.User;
import com.kangyonggan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * @author kangyonggan
 * @since 16/7/5
 */
@Controller
@RequestMapping("pits/user")
public class PitsUserController {

    private static final String PATH_ROOT = "pits/user/";
    private static final String PATH_EDIT_FORM = PATH_ROOT + "edit-form";
    private static final String PATH_PASSWORD_FORM = PATH_ROOT + "password-form";

    @Autowired
    private UserService userService;

    /**
     * 修改资料
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model) {
        model.addAttribute("item", userService.getUser(userService.getShiroUser().getId()));
        return PATH_EDIT_FORM;
    }

    /**
     * 更新资料
     *
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@ModelAttribute("user") @Valid User user, BindingResult result) throws Exception {

        if (!result.hasErrors()) {
            userService.updateUser(user);
        }

        return String.format("redirect:/user?id=%d", user.getId());
    }

    /**
     * 修改密码
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "password", method = RequestMethod.GET)
    public String password(Model model) {
        model.addAttribute("item", userService.getUser(userService.getShiroUser().getId()));
        return PATH_PASSWORD_FORM;
    }

    /**
     * 更新密码
     *
     * @return
     */
    @RequestMapping(value = "password/update", method = RequestMethod.POST)
    public String updatePassword(@ModelAttribute("user") @Valid User user, BindingResult result) throws Exception {

        if (!result.hasErrors()) {
            userService.updateUserPassword(user);
        }

        return String.format("redirect:/user?id=%d", user.getId());
    }

}
