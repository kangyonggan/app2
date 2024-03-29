package com.kangyonggan.config.directive;

import com.kangyonggan.config.shiro.SuperTag;
import com.kangyonggan.model.ShiroUser;
import com.kangyonggan.model.User;
import com.kangyonggan.service.UserService;
import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
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

    @Autowired
    private UserService userService;

    @Override
    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        ShiroUser shiroUser = userService.getShiroUser();
        User user = null;
        if (shiroUser != null) {
            user = userService.getUser(shiroUser.getId());
        }


        env.setVariable("app_user", ObjectWrapper.DEFAULT_WRAPPER.wrap(user));
        env.setVariable("app_author", ObjectWrapper.DEFAULT_WRAPPER.wrap(userService.getUser(1L)));
        renderBody(env, body);
    }
}
