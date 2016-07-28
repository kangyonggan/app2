package com.kangyonggan.controller.dashboard;

import com.kangyonggan.constants.AppConstants;
import com.kangyonggan.model.*;
import com.kangyonggan.service.ArticleService;
import com.kangyonggan.service.CategoryService;
import com.kangyonggan.service.UserService;
import com.kangyonggan.util.FileUpload;
import com.kangyonggan.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kangyonggan
 * @since 16/6/26
 */
@Controller
@RequestMapping("dashboard/article")
public class DashboardArticleController {

    private static final String PATH_ROOT = "dashboard/article/";
    private static final String PATH_FORM = PATH_ROOT + "form";

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    /**
     * 新建文章
     *
     * @param code
     * @param model
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(@RequestParam(name = "code", required = true) String code, Model model) {
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
    public String save(@RequestParam(value = "attachment[]", required = false) List<MultipartFile> attachments,
                         @ModelAttribute("article") @Valid Article article, BindingResult result) throws Exception {

        if (!result.hasErrors()) {
            List<Attachment> files = new ArrayList();
            if (attachments != null && !attachments.isEmpty()) {
                for (MultipartFile file : attachments) {
                    if (StringUtil.isNotEmpty(file.getOriginalFilename())) {
                        String path = FileUpload.upload(file);

                        Attachment attachment = new Attachment();
                        attachment.setName(file.getOriginalFilename());
                        attachment.setType(article.getCategoryCode());
                        attachment.setPath(path);

                        files.add(attachment);
                    }
                }
            }
            articleService.saveArticle(article, files);
        }

        return String.format("redirect:/dashboard/category/list?code=%s", article.getCategoryCode());
    }

    /**
     * 保存相册
     *
     * @return
     */
    @RequestMapping(value = "save/pics", method = RequestMethod.POST)
    public String savePics(@RequestParam(value = "ids", required = false, defaultValue = "") String ids,
                         @ModelAttribute("article") @Valid Article article, BindingResult result) throws Exception {

        if (!result.hasErrors()) {
            articleService.saveArticle(article, ids);
        }

        return String.format("redirect:/dashboard/category/list?code=%s", article.getCategoryCode());
    }

    /**
     * 编辑文章
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") Long id, Model model) {
        Article article = articleService.getArticle(id);
        Category category = categoryService.findCategoryByCode(article.getCategoryCode());

        model.addAttribute("category", category);
        model.addAttribute("article", article);
        return PATH_FORM;
    }

    /**
     * 更新文章
     *
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@ModelAttribute("article") @Valid Article article, BindingResult result) {

        if (!result.hasErrors()) {
            articleService.updateArticle(article);
        }

        return String.format("redirect:/dashboard/category/list?code=%s", article.getCategoryCode());
    }

    /**
     * 删除文章
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public ValidationResponse delete(@RequestParam("id") Long id) {
        ValidationResponse res = new ValidationResponse(AppConstants.SUCCESS);

        ShiroUser user = userService.getShiroUser();
        Article article = articleService.getArticle(id);

        if (!article.getUserId().equals(user.getId())) {
            res.setStatus(AppConstants.FAIL);
        } else {
            articleService.deleteArticle(id);
        }

        return res;
    }

}
