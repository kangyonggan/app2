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
    private static final String PATH_LIST = PATH_ROOT + "list";

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    /**
     * 按栏目查看所有的文章
     *
     * @param code
     * @param model
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(@RequestParam(value = "p", required = false, defaultValue = "1") int pageNum,
                          @RequestParam(value = "code", required = false, defaultValue = "") String code,
                          Model model) {
        Category category = categoryService.findCategoryByCode(code);
        if (category == null) {
            category = new Category();
            category.setName("全部栏目");
            category.setCode("");
        }
        List<Article> articles = articleService.findArticesByCategoryCode(pageNum, AppConstants.PAGE_SIZE, code);
        PageInfo<Article> page = new PageInfo(articles);

        model.addAttribute("category", category);
        model.addAttribute("page", page);
        return PATH_LIST;
    }

}
