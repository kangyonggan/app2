package com.kangyonggan.controller;

import com.kangyonggan.model.User;
import com.kangyonggan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

}
