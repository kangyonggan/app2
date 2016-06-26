package com.kangyonggan.config.directive;

import com.kangyonggan.config.shiro.SuperTag;
import com.kangyonggan.model.Category;
import com.kangyonggan.service.CategoryService;
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
public class CategoryDirective extends SuperTag {

    @Autowired
    private CategoryService categoryService;

    @Override
    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        Category category = categoryService.findTreeCategory();
        env.setVariable("app_category", ObjectWrapper.DEFAULT_WRAPPER.wrap(category));
        renderBody(env, body);
    }
}
