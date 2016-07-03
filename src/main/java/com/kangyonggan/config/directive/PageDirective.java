package com.kangyonggan.config.directive;

import com.kangyonggan.config.shiro.SuperTag;
import com.kangyonggan.model.Menu;
import com.kangyonggan.model.Page;
import com.kangyonggan.model.ShiroUser;
import com.kangyonggan.service.MenuService;
import com.kangyonggan.service.PageService;
import com.kangyonggan.service.UserService;
import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author kangyonggan
 * @since 16/5/18
 */
@Component
public class PageDirective extends SuperTag {

    @Autowired
    private UserService userService;

    @Autowired
    private PageService pageService;

    @Override
    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {

        List<Page> publicPages = pageService.findPagesByUserId(0L);
        env.setVariable("app_public_pages", ObjectWrapper.DEFAULT_WRAPPER.wrap(publicPages));

        ShiroUser user = userService.getShiroUser();
        if (user != null) {
            List<Page> pages = pageService.findPagesByUserId(user.getId());
            if (!pages.isEmpty()) {
                env.setVariable("app_pages", ObjectWrapper.DEFAULT_WRAPPER.wrap(pages));
            }
        }
        renderBody(env, body);
    }
}
