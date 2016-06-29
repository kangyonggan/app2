package com.kangyonggan.controller;

import com.kangyonggan.model.Token;
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

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "email/{code}", method = RequestMethod.GET)
    public String activeEmail(@PathVariable String code, Model model){
        Token token = tokenService.findTokenByCode(code);

        if(token == null){
            model.addAttribute("message", "验证码不合法");
        }else if(token.getExpireTime().before(new Date())) {
            model.addAttribute("message", "验证地址已过期");
        }

        userService.updateUserEmailVerified(token);
        model.addAttribute("message", "邮箱验证成功");

        return "web/email-result";
    }

}
