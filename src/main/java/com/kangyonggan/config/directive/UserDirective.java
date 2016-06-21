package com.kangyonggan.config.directive;

import com.kangyonggan.config.shiro.SuperTag;
import com.kangyonggan.constants.AppConstants;
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
        Long id = AppConstants.AUTHOR_ID;
        if (shiroUser != null) {
            id = shiroUser.getId();
        }
        User user = userService.getUser(id);
        env.setVariable("app_user", ObjectWrapper.DEFAULT_WRAPPER.wrap(user));
        renderBody(env, body);
    }
}
