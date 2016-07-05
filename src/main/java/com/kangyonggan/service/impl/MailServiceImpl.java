package com.kangyonggan.service.impl;

import com.kangyonggan.model.User;
import com.kangyonggan.service.MailService;
import com.kangyonggan.service.TokenService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kangyonggan
 * @since 16/6/29
 */
@Service
@Transactional
@Log4j2
@ConfigurationProperties(prefix = "app")
public class MailServiceImpl implements MailService {

    @Getter
    private List<String> prop = new ArrayList();

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Autowired
    private TokenService tokenService;

    @Override
    public void sendMail(User user, String type, String callbackUrl) {
        String code = tokenService.saveToken(type, user.getId());
        Map<String, Object> map = new HashMap();
        if ("user-locked".equals(type)) {
            callbackUrl += "/validator/locked/";
            map.put("title", "请点击下面的链接解除账号锁定");
        } else if ("password-reset".equals(type)) {
            callbackUrl += "/validator/reset/";
            map.put("title", "请点击下面的链接重置密码");
        } else if ("email-verify".equals(type)) {
            callbackUrl += "/validator/email/";
            map.put("title", "请点击下面的链接完成邮箱激活");
        }
        map.put("url", callbackUrl + code);
        map.put("appName", prop.get(0));
        map.put("author", prop.get(2));

        String text;
        try {
            text = getString(map, "email.ftl");
        } catch (Exception e) {
            log.error("邮件模板出错！", e);
            return;
        }

        send(user.getEmail(), text, true);
    }

    private void send(String to, String text, boolean isHtml) {
        MimeMessage msg = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setFrom(prop.get(7), prop.get(0));
            helper.setTo(to);
            helper.setSubject(prop.get(0));

            helper.setText(text, isHtml);
        } catch (Exception e) {
            log.error("邮件发送失败！" + to, e);
            return;
        }

        new Thread(() -> {
            log.info("正在给{}发邮件...", to);
            try {
                javaMailSender.send(msg);
                log.info("邮件发送成功...");
            } catch (Exception e) {
                log.error("邮件发送失败：{}", e.getMessage());
            }
        }).start();
    }

    private String getString(Map body, String templatePath) throws IOException, TemplateException {
        Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templatePath);
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, body);
        return text;
    }
}
