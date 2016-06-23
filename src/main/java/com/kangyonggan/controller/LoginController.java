package com.kangyonggan.controller;

import com.kangyonggan.constants.AppConstants;
import com.kangyonggan.exception.EmailNotVerifiedException;
import com.kangyonggan.model.User;
import com.kangyonggan.model.ValidationResponse;
import com.kangyonggan.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 登录
 *
 * @author kangyonggan
 * @since 16/5/15
 */
@Controller
@RequestMapping
@Log4j2
public class LoginController {

    private static final String PASSWORD_ERROR_KEY = "password-error-key";
    private static final int PASSWORD_ERROR_COUNT = 3;

    private static final String PATH_ROOT = "web/login";
    private static final String PATH_INDEX = PATH_ROOT + "/index";
    private static final String PATH_FORGET = PATH_ROOT + "/forget";

    @Autowired
    private UserService userService;

    /**
     * 登录界面
     *
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String index() {
        return PATH_INDEX;
    }

    /**
     * 登录
     *
     * @param user
     * @param captcha
     * @param request
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ValidationResponse login(@RequestParam(value = "captcha", required = true) String captcha,
                                    User user, HttpServletRequest request) {
        ValidationResponse res = new ValidationResponse(AppConstants.FAIL);

        HttpSession session = request.getSession();
        String realCaptcha = (String) session.getAttribute(CaptchaController.KEY_CAPTCHA);

//        if (!captcha.equalsIgnoreCase(realCaptcha)) {
//            res.setMessage("验证码错误，请重新输入!");
//            return res;
//        }

        UsernamePasswordToken token = new UsernamePasswordToken(user.getEmail(), user.getPassword());
        final Subject subject = SecurityUtils.getSubject();

        try {
            //会调用 ShiroDbRealm 的认证方法 doGetAuthenticationInfo(AuthenticationToken)
            subject.login(token);
        } catch (UnknownAccountException uae) {
            res.setMessage("该电子邮箱不存在！");
            return res;
        } catch (IncorrectCredentialsException ice) {
            int count = doErrorPassword(session, user.getEmail());
            res.setMessage(String.format("密码错误%d次，错误%d次将锁定账户！", count, PASSWORD_ERROR_COUNT));
            if (count >= PASSWORD_ERROR_COUNT) {
                res.setMessage(String.format("密码错误%d次，账户已锁定, 请前往邮箱激活！", count));
            }
            return res;
        } catch (LockedAccountException lae) {
            res.setMessage("账号已锁定，请联系管理员！");
            return res;
        } catch (EmailNotVerifiedException enve) {
            res.setMessage("账号未激活，请前往邮箱激活！");
            return res;
        } catch (Exception e) {
            res.setMessage("未知错误，请联系管理员！");
            log.error("未知错误", e);
            return res;
        }

        user = userService.findUserByEmail(user.getEmail());
        // 更新最近登录时间
        user.setLoginTime(new Date());
        userService.updateUser(user);

        SavedRequest savedRequest = WebUtils.getSavedRequest(request);
        res.setStatus(AppConstants.SUCCESS);
        // 获取之前访问的URL
        if (savedRequest == null || savedRequest.getRequestUrl() == null) {
            res.setMessage(String.format("user/%d", user.getId()));
            return res;
        }
        res.setMessage(String.format("%s", savedRequest.getRequestUrl()));
        return res;
    }

    /**
     * 注销
     *
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout() {
        final Subject subject = SecurityUtils.getSubject();
        log.info("logout {}", subject.getPrincipal());
        subject.logout();
        return "redirect:/";
    }

    /**
     * 忘记密码界面
     *
     * @return
     */
    @RequestMapping(value = "forget", method = RequestMethod.GET)
    public String forget() {
        return PATH_FORGET;
    }

    /**
     * 处理密码错误, 防止暴力破解
     *
     * @param session
     * @param email
     * @return 已错误次数
     */
    private int doErrorPassword(HttpSession session, String email) {
        int count;
        try {
            count = (int) session.getAttribute(PASSWORD_ERROR_KEY);
        } catch (Exception e) {
            count = 0;
        }
        if (++count >= PASSWORD_ERROR_COUNT) {
            User user = userService.findUserByEmail(email);
            user.setIsLocked((byte) 1);
            userService.updateUser(user);
            // TODO 因为可能是其他人恶意破坏, 所以需要发邮箱通知用户
        }
        session.setAttribute(PASSWORD_ERROR_KEY, count % PASSWORD_ERROR_COUNT);
        return count;
    }

}
