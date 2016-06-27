package com.kangyonggan.controller;

import com.github.pagehelper.PageInfo;
import com.kangyonggan.constants.AppConstants;
import com.kangyonggan.model.Article;
import com.kangyonggan.model.Category;
import com.kangyonggan.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 网站首页
 *
 * @author kangyonggan
 * @since 16/5/15
 */
@Controller
@RequestMapping
public class IndexController {

    private static final String PATH_ROOT = "web/index/";
    private static final String PATH_INDEX = PATH_ROOT + "index";

    @Autowired
    private ArticleService articleService;

    /**
     * 网站首页
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index(@RequestParam(value = "p", required = false, defaultValue = "1") int pageNum,
                        Model model) {
        Category category = new Category();
        category.setName("全部栏目");
        category.setCode("");
        List<Article> articles = articleService.findArticesByCategoryCode(pageNum, AppConstants.PAGE_SIZE, null);
        PageInfo<Article> page = new PageInfo(articles);

        model.addAttribute("category", category);
        model.addAttribute("page", page);
        return PATH_INDEX;
    }

    @RequestMapping(value = "404", method = RequestMethod.GET)
    public String error404() {
        return "error/404";
    }

    @RequestMapping(value = "405", method = RequestMethod.GET)
    public String error405() {
        return "error/405";
    }
}
