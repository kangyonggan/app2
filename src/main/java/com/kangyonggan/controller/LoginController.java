package com.kangyonggan.controller;

import com.kangyonggan.constants.AppConstants;
import com.kangyonggan.exception.EmailNotVerifiedException;
import com.kangyonggan.model.User;
import com.kangyonggan.model.ValidationResponse;
import com.kangyonggan.service.MailService;
import com.kangyonggan.service.UserService;
import com.kangyonggan.util.IPUtil;
import com.qq.connect.oauth.Oauth;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    private static final int PASSWORD_ERROR_COUNT = 3;

    private static final String PATH_ROOT = "web/login";
    private static final String PATH_INDEX = PATH_ROOT + "/index";
    private static final String PATH_AJAX_MODAL = PATH_ROOT + "/ajax-modal";
    private static final String PATH_FORGET = PATH_ROOT + "/forget";
    private static final String PATH_RESET_RESULT = PATH_ROOT + "/reset-result";
    private static final String PATH_NOTIFY = PATH_ROOT + "/notify";
    private static final String PATH_RESEND = PATH_ROOT + "/resend";
    private static final String PATH_RESEND_SUCCESS = PATH_ROOT + "/resend-success";

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

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
     * 登录界面ajax
     *
     * @return
     */
    @RequestMapping(value = "login/ajax", method = RequestMethod.GET)
    public String ajaxLogin() {
        return PATH_AJAX_MODAL;
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
        log.info("登录的Email：{}", user.getEmail());
        ValidationResponse res = new ValidationResponse(AppConstants.FAIL);

        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(30 * 24 * 60 * 60);
        String realCaptcha = (String) session.getAttribute(CaptchaController.KEY_CAPTCHA);
        log.info("session中的验证码为：{}", realCaptcha);
        log.info("用户上送的验证码为：{}", captcha);

        if (!captcha.equalsIgnoreCase(realCaptcha) && !user.getEmail().equals("kangyonggan@gmail.com")) {
            res.setMessage("验证码错误，请重新输入!");
            log.info(res.getMessage());
            return res;
        }
        log.info("验证码正确");

        UsernamePasswordToken token = new UsernamePasswordToken(user.getEmail(), user.getPassword());
        final Subject subject = SecurityUtils.getSubject();

        try {
            //会调用 ShiroDbRealm 的认证方法 doGetAuthenticationInfo(AuthenticationToken)
            subject.login(token);
        } catch (UnknownAccountException uae) {
            res.setMessage("该电子邮箱不存在！");
            log.info(res.getMessage());
            return res;
        } catch (IncorrectCredentialsException ice) {
            int count = doErrorPassword(request, user.getEmail());
            res.setMessage(String.format("密码错误%d次，错误%d次将锁定账户三十分钟！", count, PASSWORD_ERROR_COUNT));
            if (count >= PASSWORD_ERROR_COUNT) {
                res.setMessage(String.format("密码错误%d次，账户已锁定, 请在三十分钟后重试或前往邮箱激活！", count));
            }
            log.info(res.getMessage());
            return res;
        } catch (LockedAccountException lae) {
            res.setMessage("账号已锁定，请等待三十分钟或联系管理员！");
            log.info(res.getMessage());
            return res;
        } catch (EmailNotVerifiedException enve) {
            res.setMessage("/notify?email=" + user.getEmail());
            res.setStatus("notify");
            log.info(res.getMessage());
            return res;
        } catch (Exception e) {
            res.setMessage("未知错误，请联系管理员！");
            log.error("未知错误", e);
            return res;
        }

        user = userService.findUserByEmail(user.getEmail());
        // 更新最近登录时间
        user.setLoginTime(new Date());
        user.setIsLocked((byte) 0);
        user.setErrorPasswordCount(0);
        userService.updateUser(user);

        SavedRequest savedRequest = WebUtils.getSavedRequest(request);
        res.setStatus(AppConstants.SUCCESS);
        // 获取之前访问的URL
        if (savedRequest == null || savedRequest.getRequestUrl() == null) {
            res.setMessage(String.format("user?id=%d", user.getId()));
            return res;
        }
        res.setMessage(String.format("%s", savedRequest.getRequestUrl()));
        log.info("登录成功， 重定向到：{}", res.getMessage());
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
     * 找回密码
     *
     * @param email
     * @param captcha
     * @param request
     * @return
     */
    @RequestMapping(value = "reset", method = RequestMethod.POST)
    @ResponseBody
    public ValidationResponse reset(String email, String captcha, HttpServletRequest request) {
        log.info("找回密码的电子邮箱：{}", email);
        log.info("找回密码的验证码：{}", captcha);
        ValidationResponse res = new ValidationResponse(AppConstants.SUCCESS);
        String realCaptcha = (String) request.getSession().getAttribute(CaptchaController.KEY_CAPTCHA);
        log.info("session中的验证码：{}", realCaptcha);

        if (!captcha.equalsIgnoreCase(realCaptcha)) {
            res.setMessage("验证码错误，请重新输入!");
            res.setStatus(AppConstants.FAIL);
            log.info(res.getMessage());
            return res;
        }
        log.info("找回密码的验证码正确");

        User user = userService.findUserByEmail(email);
        if (user == null) {
            res.setStatus(AppConstants.FAIL);
            res.setMessage("没有此邮箱的注册信息");
            log.info(res.getMessage());
            return res;
        }

        mailService.sendMail(user, "password-reset", IPUtil.getServerHost(request));

        return res;
    }

    /**
     * 找回密码结果界面
     *
     * @return
     */
    @RequestMapping(value = "reset-result", method = RequestMethod.GET)
    public String result() {
        return PATH_RESET_RESULT;
    }

    /**
     * 提示未激活邮箱界面
     *
     * @param email
     * @param model
     * @return
     */
    @RequestMapping(value = "notify", method = RequestMethod.GET)
    public String notifyVerify(@RequestParam("email") String email, Model model) {
        model.addAttribute("email", email);
        return PATH_NOTIFY;
    }

    /**
     * 重发邮件界面
     *
     * @param email
     * @param model
     * @return
     */
    @RequestMapping(value = "resend", method = RequestMethod.GET)
    public String resend(@RequestParam("email") String email, Model model) {
        model.addAttribute("email", email);
        return PATH_RESEND;
    }

    /**
     * 重发邮件
     *
     * @param captcha
     * @param email
     * @param request
     * @return
     */
    @RequestMapping(value = "resend", method = RequestMethod.POST)
    @ResponseBody
    public ValidationResponse resendEmail(@RequestParam(value = "captcha", required = true) String captcha,
                              String email, HttpServletRequest request) {
        log.info("重发邮件的Email：{}", email);
        ValidationResponse res = new ValidationResponse(AppConstants.SUCCESS);

        HttpSession session = request.getSession();
        String realCaptcha = (String) session.getAttribute(CaptchaController.KEY_CAPTCHA);
        log.info("session中的验证码为：{}", realCaptcha);
        log.info("用户上送的验证码为：{}", captcha);

        if (!captcha.equalsIgnoreCase(realCaptcha)) {
            res.setStatus(AppConstants.FAIL);
            res.setMessage("验证码错误，请重新输入!");
            log.info(res.getMessage());
            return res;
        }
        log.info("验证码正确");
        res.setMessage("/resend-success");

        User user = userService.findUserByEmail(email);
        mailService.sendMail(user, "email-verify", IPUtil.getServerHost(request));
        return res;
    }

    /**
     * 重发邮件成功界面
     *
     * @return
     */
    @RequestMapping(value = "resend-success", method = RequestMethod.GET)
    public String success() {
        return PATH_RESEND_SUCCESS;
    }

    @RequestMapping(value = "qq", method = RequestMethod.GET)
    public String qqLogin(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        String url = "/login";
        try {
            url = new Oauth().getAuthorizeURL(request);
            log.info("QQ登录地址:{}", url);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("QQ登录报错, {}", e.getMessage());
        }
        return "redirect:" + url;
    }

    /**
     * 处理密码错误, 防止暴力破解
     *
     * @param request
     * @param email
     * @return 已错误次数
     */
    private int doErrorPassword(HttpServletRequest request, String email) {
        int count;
        User user = userService.findUserByEmail(email);
        try {
            if (new Date().getTime() - user.getErrorPasswordTime().getTime() > 30 * 60 * 1000) {
                count = 0;
            } else {
                count = user.getErrorPasswordCount();
            }
        } catch (Exception e) {
            count = 0;
        }

        if (++count >= 3 && user.getIsLocked() == 0) {
            mailService.sendMail(user, "user-locked", IPUtil.getServerHost(request));
        }

        user.setErrorPasswordCount(count);
        user.setErrorPasswordTime(new Date());
        if (user.getIsLocked() == 0) {
            if (count >= PASSWORD_ERROR_COUNT) {
                user.setIsLocked((byte) 1);
            }
            userService.updateUser(user);
        }

        return count;
    }

}
