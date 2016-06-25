package com.kangyonggan.controller;

import com.kangyonggan.constants.AppConstants;
import com.kangyonggan.model.Article;
import com.kangyonggan.model.Category;
import com.kangyonggan.model.ShiroUser;
import com.kangyonggan.model.ValidationResponse;
import com.kangyonggan.service.ArticleService;
import com.kangyonggan.service.CategoryService;
import com.kangyonggan.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author kangyonggan
 * @since 16/6/25
 */
@Controller
@RequestMapping("article")
public class ArticleController {

    private static final String PATH_ROOT = "web/article/";
    private static final String PATH_FORM = PATH_ROOT + "form";

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    /**
     * 新建文章
     *
     * @param code
     * @param model
     * @return
     */
    @RequestMapping(value = "category/{code:[\\w]+}/create", method = RequestMethod.GET)
    public String create(@PathVariable("code") String code, Model model) {
        Category category = categoryService.findCategoryByCode(code);

        model.addAttribute("category", category);
        model.addAttribute("article", new Article());
        return PATH_FORM;
    }

    /**
     * 保存文章
     *
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String create(@ModelAttribute("article") @Valid Article article, BindingResult result) {

        if (!result.hasErrors()) {
            articleService.saveArticle(article);
        }

        ShiroUser user = userService.getShiroUser();

        return String.format("redirect:/category/%s/user/%d", article.getCategoryCode(), user.getId());
    }

    /**
     * 删除文章(逻辑删除)
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/delete", method = RequestMethod.GET)
    @ResponseBody
    public ValidationResponse logicDelete(@PathVariable("id") Long id) {
        ValidationResponse res = new ValidationResponse(AppConstants.SUCCESS);

        ShiroUser user = userService.getShiroUser();
        Article article = articleService.getArticle(id);

        if (!article.getUserId().equals(user.getId())) {
            res.setStatus(AppConstants.FAIL);
        } else {
            articleService.deleteArticleWithLogic(article);
        }

        return res;
    }

}
