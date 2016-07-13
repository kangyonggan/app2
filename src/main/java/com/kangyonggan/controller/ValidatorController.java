package com.kangyonggan.controller;

import com.kangyonggan.model.Token;
import com.kangyonggan.model.User;
import com.kangyonggan.service.TokenService;
import com.kangyonggan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * @author kangyonggan
 * @since 16/6/29
 */
@Controller
@RequestMapping("validator")
public class ValidatorController {

    private static final String PATH_RESULT = "web/email-result";

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "email/{code}", method = RequestMethod.GET)
    public String email(@PathVariable String code, Model model) {
        Token token = tokenService.findTokenByCode(code);

        if (token == null) {
            model.addAttribute("message", "验证码不合法");
        } else if (token.getExpireTime().before(new Date())) {
            model.addAttribute("message", "验证地址已过期");
        } else if (token.getIsDeleted() == 1) {
            model.addAttribute("message", "验证码已失效");
        } else {
            userService.updateUserEmailVerified(token);
            model.addAttribute("message", "邮箱验证成功");
        }

        token.setIsDeleted((byte) 1);
        tokenService.updateToken(token);

        model.addAttribute("code", token.getCode());
        model.addAttribute("header", "邮箱验证结果");
        return PATH_RESULT;
    }

    @RequestMapping(value = "reset/{code}", method = RequestMethod.GET)
    public String reset(@PathVariable String code, Model model) {
        Token token = tokenService.findTokenByCode(code);

        if (token == null) {
            model.addAttribute("message", "验证码不合法");
        } else if (token.getExpireTime().before(new Date())) {
            model.addAttribute("message", "验证码已过期");
        } else if (token.getIsDeleted() == 1) {
            model.addAttribute("message", "验证码已失效");
        } else {
            model.addAttribute("userId", token.getUserId());
            model.addAttribute("message", "重置密码");
        }

        token.setIsDeleted((byte) 1);
        tokenService.updateToken(token);

        model.addAttribute("code", token.getCode());
        model.addAttribute("header", "重置密码");
        return PATH_RESULT;
    }

    @RequestMapping(value = "locked/{code}", method = RequestMethod.GET)
    public String locked(@PathVariable String code, Model model) {
        Token token = tokenService.findTokenByCode(code);

        if (token == null) {
            model.addAttribute("message", "验证码不合法");
        } else if (token.getExpireTime().before(new Date())) {
            model.addAttribute("message", "验证码已过期");
        } else if (token.getIsDeleted() == 1) {
            model.addAttribute("message", "验证码已失效");
        } else {
            User user = userService.getUser(token.getUserId());
            user.setIsLocked((byte) 0);
            user.setErrorPasswordCount(0);
            userService.updateUser(user);

            model.addAttribute("message", "已解除锁定， 请重新登录， 如果记不得密码， 可以点击“忘记密码”。");
        }

        token.setIsDeleted((byte) 1);
        tokenService.updateToken(token);

        model.addAttribute("code", token.getCode());
        model.addAttribute("header", "解除用户锁定");
        return PATH_RESULT;
    }

}
