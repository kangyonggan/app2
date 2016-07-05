package com.kangyonggan.controller;

import com.kangyonggan.constants.AppConstants;
import com.kangyonggan.model.User;
import com.kangyonggan.model.ValidationResponse;
import com.kangyonggan.service.MailService;
import com.kangyonggan.service.UserService;
import com.kangyonggan.util.IPUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 注册
 *
 * @author kangyonggan
 * @since 16/5/16
 */
@Controller
@RequestMapping("register")
@Log4j2
public class RegisterController {

    private static final String PATH_ROOT = "web/register";
    private static final String PATH_INDEX = PATH_ROOT + "/index";
    private static final String PATH_REGISTER_RESULT = PATH_ROOT + "/register-result";

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    /**
     * 注册界面
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String register() {
        return PATH_INDEX;
    }

    /**
     * 注册
     *
     * @param user
     * @param captcha
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ValidationResponse register(User user, String captcha,
                                       HttpServletRequest request) {
        ValidationResponse res = new ValidationResponse(AppConstants.FAIL);
        String realCaptcha = (String) request.getSession().getAttribute(CaptchaController.KEY_CAPTCHA);

        if (!captcha.equalsIgnoreCase(realCaptcha)) {
            res.setMessage("验证码错误，请重新输入!");
            return res;
        }

        res.setMessage("注册失败, 请稍后重试!");

        try {
            userService.saveUserAndRole(user);
            res.setStatus(AppConstants.SUCCESS);

            mailService.sendMail(user, "email-verify", IPUtil.getServerHost(request));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return res;
    }

    /**
     * 注册成功
     *
     * @return
     */
    @RequestMapping(value = "success", method = RequestMethod.GET)
    public String success() {
        return PATH_REGISTER_RESULT;
    }


}
