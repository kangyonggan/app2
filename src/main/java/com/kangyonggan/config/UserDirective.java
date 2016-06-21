package com.kangyonggan.config;

import com.kangyonggan.config.shiro.SuperTag;
import com.kangyonggan.model.ShiroUser;
import com.kangyonggan.model.User;
import com.kangyonggan.service.UserService;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @author kangyonggan
 * @since 16/5/18
 */
@Component
public class UserDirective extends SuperTag {

    String getProperty(Map params) {
        return getParam(params, "property");
    }

    @Autowired
    private UserService userService;

    @Override
    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        ShiroUser shiroUser = null;
        try {
            shiroUser = userService.getShiroUser();
        } catch (Exception E) {

        }
        User user = null;
        String result = null;

        if (shiroUser != null) {
            Long userId = shiroUser.getId();
            user = userService.getUser(userId);
        }

        if (user != null) {
            String property = getProperty(params);

            if (property == null) {
                result = user.toString();
            } else {
                result = getPrincipalProperty(user, property);
            }
        }

        if (result != null) {
            env.getOut().write(result);
        }
    }
}
