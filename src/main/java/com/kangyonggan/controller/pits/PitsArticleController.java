package com.kangyonggan.controller.pits;

import com.github.pagehelper.PageInfo;
import com.kangyonggan.constants.AppConstants;
import com.kangyonggan.model.Article;
import com.kangyonggan.model.Category;
import com.kangyonggan.model.ValidationResponse;
import com.kangyonggan.service.ArticleService;
import com.kangyonggan.service.CategoryService;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 文章管理
 *
 * @author kangyonggan
 * @since 16/6/21
 */
@Controller
@RequestMapping("pits/article")
@Log4j2
public class PitsArticleController {

    private static final String PATH_ROOT = "pits/article/";
    private static final String PATH_LIST = PATH_ROOT + "list";

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    /**
     * 文章管理界面
     *
     * @param pageNum
     * @param categoryCode
     * @param startTime
     * @param endTime
     * @param title
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @RequiresPermissions("pits-article")
    public String index(@RequestParam(value = "p", required = false, defaultValue = "1") int pageNum,
                        @RequestParam(value = "categoryCode", required = false, defaultValue = "root") String categoryCode,
                        @RequestParam(value = "startTime", required = false, defaultValue = "") String startTime,
                        @RequestParam(value = "endTime", required = false, defaultValue = "") String endTime,
                        @RequestParam(value = "title", required = false, defaultValue = "") String title,
                        Model model) throws Exception {
        List<Article> articles = articleService.searchAllArticles(pageNum, AppConstants.PAGE_SIZE, categoryCode, title.trim(), startTime, endTime);
        PageInfo<Article> page = new PageInfo(articles);
        List<Category> categories = categoryService.findAllCategories();

        model.addAttribute("page", page);
        model.addAttribute("categories", categories);
        return PATH_LIST;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("pits-article")
    public ValidationResponse delete(@RequestParam("id") Long id) {
        log.info("删除文章id:" + id);
        articleService.deleteArticle(id);
        log.info("文章删除成功");
        return new ValidationResponse(AppConstants.SUCCESS);
    }
}
