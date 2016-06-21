package com.kangyonggan.config.directive;

import com.kangyonggan.config.shiro.SuperTag;
import com.kangyonggan.model.Menu;
import com.kangyonggan.model.ShiroUser;
import com.kangyonggan.service.MenuService;
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
public class MenuDirective extends SuperTag {

    String getProperty(Map params) {
        return getParam(params, "property");
    }

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Override
    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        ShiroUser user = userService.getShiroUser();
        if (user != null) {
            Menu sys_parent_menu = menuService.findMenuByCode("sys-manage");
            if (sys_parent_menu != null) {
                List<Menu> menus = menuService.findMenusByPid(sys_parent_menu.getId());
                env.setVariable("sys_parent_menu", ObjectWrapper.DEFAULT_WRAPPER.wrap(sys_parent_menu));
                env.setVariable("sys_menus", ObjectWrapper.DEFAULT_WRAPPER.wrap(menus));
            }
        }
        renderBody(env, body);
    }
}
