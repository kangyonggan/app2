package com.kangyonggan.config.directive;

import com.kangyonggan.config.shiro.SuperTag;
import com.kangyonggan.model.Article;
import com.kangyonggan.model.ShiroUser;
import com.kangyonggan.model.User;
import com.kangyonggan.service.ArticleService;
import com.kangyonggan.service.UserService;
import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kangyonggan
 * @since 16/5/18
 */
@Component
public class UserDirective extends SuperTag {

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Override
    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        ShiroUser shiroUser = userService.getShiroUser();
        User user = null;
        Long userId = 0L;
        if (shiroUser != null) {
            user = userService.getUser(shiroUser.getId());
            userId = user.getId();
        } else {
            userId = 1L;
        }
        Article article_header = articleService.findTotalArticleByUserId(userId);
        Article video_header = articleService.findTotalArticleByUserIdWithGroup(userId, "video");
        Article music_header = articleService.findTotalArticleByUserIdWithGroup(userId, "music");
        Article picture_header = articleService.findTotalArticleByUserIdWithGroup(userId, "picture");
        if (article_header.getTop() == null) {
            article_header.setTop(0);
        }
        if (article_header.getLow() == null) {
            article_header.setLow(0);
        }
        if (video_header == null) {
            video_header = new Article();
            video_header.setTotal(0);
        }
        if (music_header == null) {
            music_header = new Article();
            music_header.setTotal(0);
        }
        if (picture_header == null) {
            picture_header = new Article();
            picture_header.setTotal(0);
        }

        Map<String, Object> map = new HashMap();
        map.put("article_header", article_header);
        map.put("video_header", video_header);
        map.put("music_header", music_header);
        map.put("picture_header", picture_header);

        env.setVariable("app_user", ObjectWrapper.DEFAULT_WRAPPER.wrap(user));
        env.setVariable("app_author", ObjectWrapper.DEFAULT_WRAPPER.wrap(userService.getUser(1L)));
        env.setVariable("map_header", ObjectWrapper.DEFAULT_WRAPPER.wrap(map));
        renderBody(env, body);
    }
}
