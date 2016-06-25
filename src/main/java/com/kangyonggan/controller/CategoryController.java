package com.kangyonggan.controller;

import com.github.pagehelper.PageInfo;
import com.kangyonggan.constants.AppConstants;
import com.kangyonggan.model.Article;
import com.kangyonggan.model.Category;
import com.kangyonggan.service.ArticleService;
import com.kangyonggan.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author kangyonggan
 * @since 16/6/25
 */
@Controller
@RequestMapping("category")
public class CategoryController {

    private static final String PATH_ROOT = "web/category/";
    private static final String PATH_MY_LIST = PATH_ROOT + "my-list";
    private static final String PATH_ALL_LIST = PATH_ROOT + "all-list";

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    /**
     * 我的某栏目下的文章列表
     *
     * @param pageNum
     * @param title
     * @param startTime
     * @param endTime
     * @param code
     * @param userId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "{code:[\\w]+}/user/{userId:[\\d]+}", method = RequestMethod.GET)
    public String myList(@RequestParam(value = "p", required = false, defaultValue = "1") int pageNum,
                         @RequestParam(value = "title", required = false, defaultValue = "") String title,
                         @RequestParam(value = "startTime", required = false, defaultValue = "") String startTime,
                         @RequestParam(value = "endTime", required = false, defaultValue = "") String endTime,
                         @PathVariable("code") String code, @PathVariable("userId") Long userId, Model model) throws Exception {
        Category category = categoryService.findCategoryByCode(code);
        List<Article> articles = articleService.searchArticles(pageNum, AppConstants.PAGE_SIZE, code, userId, title.trim(), startTime, endTime);
        PageInfo<Article> page = new PageInfo(articles);

        model.addAttribute("category", category);
        model.addAttribute("page", page);
        return PATH_MY_LIST;
    }

    /**
     * 按栏目查看所有的文章
     *
     * @param code
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String allList(@RequestParam(value = "code", required = false, defaultValue = "") String code,
                          Model model) {
        Category category = categoryService.findCategoryByCode(code);
        if (category == null) {
            category = new Category();
            category.setName("全部栏目");
        }
        List<Article> articles = articleService.findArticesByCategoryCode(1, AppConstants.PAGE_SIZE, code);

        model.addAttribute("category", category);
        model.addAttribute("articles", articles);
        return PATH_ALL_LIST;
    }

}
