package com.kangyonggan.controller.dashboard;

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
 * 文章管理
 *
 * @author kangyonggan
 * @since 16/6/26
 */
@Controller
@RequestMapping("dashboard/category")
public class DashboardCategoryController {

    private static final String PATH_ROOT = "dashboard/category/";
    private static final String PATH_DASHBOARD_LIST = PATH_ROOT + "list";

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
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "{code:[\\w]+}", method = RequestMethod.GET)
    public String myList(@RequestParam(value = "p", required = false, defaultValue = "1") int pageNum,
                         @RequestParam(value = "title", required = false, defaultValue = "") String title,
                         @RequestParam(value = "startTime", required = false, defaultValue = "") String startTime,
                         @RequestParam(value = "endTime", required = false, defaultValue = "") String endTime,
                         @PathVariable("code") String code, Model model) throws Exception {
        Category category = categoryService.findCategoryByCode(code);
        List<Article> articles = articleService.searchArticles(pageNum, AppConstants.PAGE_SIZE, code, title.trim(), startTime, endTime);
        PageInfo<Article> page = new PageInfo(articles);

        model.addAttribute("category", category);
        model.addAttribute("page", page);
        return PATH_DASHBOARD_LIST;
    }
}
