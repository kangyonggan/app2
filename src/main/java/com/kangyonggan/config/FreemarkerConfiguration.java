package com.kangyonggan.config;

import com.kangyonggan.config.directive.CategoryDirective;
import com.kangyonggan.config.directive.MenuDirective;
import com.kangyonggan.config.directive.PageDirective;
import com.kangyonggan.config.directive.UserDirective;
import com.kangyonggan.config.freemarker.BlockDirective;
import com.kangyonggan.config.freemarker.ExtendsDirective;
import com.kangyonggan.config.freemarker.OverrideDirective;
import com.kangyonggan.config.freemarker.SuperDirective;
import com.kangyonggan.config.shiro.ShiroTags;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.*;

/**
 * @author kangyonggan
 * @since 16/5/13
 */
@Configuration
@ConfigurationProperties(prefix = "app")
public class FreemarkerConfiguration extends FreeMarkerAutoConfiguration.FreeMarkerWebConfiguration {

    @Getter
    private List<String> prop = new ArrayList();

    @Value("${server.context-path}")
    private String ctx;

    @Autowired
    private ShiroTags shiroTags;

    @Autowired
    private UserDirective userDirective;

    @Autowired
    private MenuDirective menuDirective;

    @Autowired
    private CategoryDirective categoryDirective;

    @Autowired
    private PageDirective pageDirective;

    @Override
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = super.freeMarkerConfigurer();
        Map<String, Object> sharedVariables = new HashMap();
        sharedVariables.put("appName", prop.get(0));
        sharedVariables.put("version", prop.get(1));
        sharedVariables.put("authorName", prop.get(2));
        sharedVariables.put("submit", prop.get(3));
        sharedVariables.put("cancel", prop.get(4));
        sharedVariables.put("ctx", ctx);
        sharedVariables.put("current_user", userDirective);
        sharedVariables.put("app_menu", menuDirective);
        sharedVariables.put("app_category", categoryDirective);
        sharedVariables.put("app_page", pageDirective);
        sharedVariables.put("block", new BlockDirective());
        sharedVariables.put("extends", new ExtendsDirective());
        sharedVariables.put("override", new OverrideDirective());
        sharedVariables.put("super", new SuperDirective());
        sharedVariables.put("shiro", shiroTags);

        configurer.setFreemarkerVariables(sharedVariables);

        Properties properties = new Properties();
        properties.setProperty("template_update_delay", "10");
        properties.setProperty("defaultEncoding", "UTF-8");
        properties.setProperty("url_escaping_charset", "UTF-8");
        properties.setProperty("locale", "zh_CN");
        properties.setProperty("number_format", "#.##");
        properties.setProperty("time_format", "HH:mm:ss");
        properties.setProperty("date_format", "yyyy-MM-dd");
        properties.setProperty("datetime_format", "yyyy-MM-dd HH:mm:ss");
        properties.setProperty("whitespace_stripping", "true");
        properties.setProperty("auto_import", "common.ftl as c");
        configurer.setFreemarkerSettings(properties);
        configurer.setDefaultEncoding("UTF-8");

        configurer.setTemplateLoaderPath("/WEB-INF/templates/");

        return configurer;
    }

}
