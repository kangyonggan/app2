package com.kangyonggan.controller;

import com.kangyonggan.constants.AppConstants;
import com.kangyonggan.model.Article;
import com.kangyonggan.model.ValidationResponse;
import com.kangyonggan.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author kangyonggan
 * @since 16/6/25
 */
@Controller
@RequestMapping("article")
public class ArticleController {

    private static final String PATH_ROOT = "web/article/";
    private static final String PATH_DETAIL = PATH_ROOT + "detail";

    @Autowired
    private ArticleService articleService;

    /**
     * 文章详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}", method = RequestMethod.GET)
    public String detail(@PathVariable("id") Long id, Model model) {
        Article article = articleService.findArticleById(id);

        model.addAttribute("article", article);
        return PATH_DETAIL;
    }

    /**
     * 顶/踩
     *
     * @param id
     * @param action
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/{action:\\btop\\b|\\blow\\b}", method = RequestMethod.GET)
    @ResponseBody
    public ValidationResponse actions(@PathVariable("id") Long id, @PathVariable("action") String action) {
        ValidationResponse res = new ValidationResponse(AppConstants.SUCCESS);

        boolean success = articleService.updateArticleActions(id, action);

        if (!success) {
            res.setStatus(AppConstants.FAIL);
        }

        return res;
    }

}
