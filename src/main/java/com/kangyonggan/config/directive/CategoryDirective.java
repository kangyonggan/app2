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
import java.util.List;
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
        List<Category> categories = categoryService.findTreeCategories();
        env.setVariable("app_category", ObjectWrapper.DEFAULT_WRAPPER.wrap(categories.get(0)));
        renderBody(env, body);
    }
}
