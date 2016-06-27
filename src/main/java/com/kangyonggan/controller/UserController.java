package com.kangyonggan.controller;

import com.github.pagehelper.PageInfo;
import com.kangyonggan.constants.AppConstants;
import com.kangyonggan.model.Article;
import com.kangyonggan.model.Category;
import com.kangyonggan.model.User;
import com.kangyonggan.model.ValidationResponse;
import com.kangyonggan.service.ArticleService;
import com.kangyonggan.service.UserService;
import com.kangyonggan.util.FileUpload;
import com.kangyonggan.util.Images;
import com.kangyonggan.util.StringUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author kangyonggan
 * @since 16/6/20
 */
@Controller
@RequestMapping("user")
@Log4j2
public class UserController {

    private static final String PATH_ROOT = "web/user/";
    private static final String PATH_INDEX = PATH_ROOT + "index";
    private static final String PATH_STAR = PATH_ROOT + "star";

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    /**
     * Ta的主页
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index(@RequestParam("id") Long id, Model model) {
        User user = userService.getUser(id);
        if (user == null) {
            return "redirect:/404";
        }
        Category category = new Category();
        category.setName("全部栏目");
        List<Article> articles = articleService.findArticesByCategoryCode(1, AppConstants.PAGE_SIZE, null);
        PageInfo<Article> page = new PageInfo(articles);

        model.addAttribute("user", user);
        model.addAttribute("category", category);
        model.addAttribute("page", page);
        return PATH_INDEX;
    }

    /**
     * 我的收藏
     *
     * @param pageNum
     * @param model
     * @return
     */
    @RequestMapping(value = "article/star", method = RequestMethod.GET)
    public String star(@RequestParam(value = "p", required = false, defaultValue = "1") int pageNum, Model model) {
        List<Article> articles = articleService.findStarArticles(pageNum, AppConstants.PAGE_SIZE);
        PageInfo<Article> page = new PageInfo(articles);
        Category category = new Category();
        category.setName("我的收藏");
        category.setIcon("ace-icon fa fa-star-o");

        model.addAttribute("category", category);
        model.addAttribute("page", page);
        return PATH_STAR;
    }

    /**
     * 校验电子邮箱唯一性
     *
     * @param email
     * @param oldEmail
     * @return
     */
    @RequestMapping(value = "verify-email", method = RequestMethod.POST)
    @ResponseBody
    public boolean verifyEmail(@RequestParam("email") String email, @RequestParam("oldEmail") String oldEmail) {
        if (oldEmail.equals(email)) {
            return true;
        }
        return userService.findUserByEmail(email) == null;
    }

    /**
     * 校验手机号唯一性
     *
     * @param mobile
     * @param oldMobile
     * @return
     */
    @RequestMapping(value = "verify-mobile", method = RequestMethod.POST)
    @ResponseBody
    public boolean verifyMobile(@RequestParam("mobile") String mobile, @RequestParam("oldMobile") String oldMobile) {
        if (oldMobile.equals(mobile)) {
            return true;
        }
        if (StringUtil.isEmpty(mobile)) {
            return true;
        }
        return userService.findUserByMobile(mobile) == null;
    }

    /**
     * 保存头像修改
     *
     * @param avatar
     * @return
     */
    @RequestMapping(value = "avatar", method = RequestMethod.POST)
    @ResponseBody
    public ValidationResponse avatar(@RequestParam(value = "avatar") MultipartFile avatar) throws Exception {
        ValidationResponse res = new ValidationResponse(AppConstants.SUCCESS);
        try {
            String fileName = FileUpload.upload(avatar);
            User user = userService.getUser(userService.getShiroUser().getId());

            String large = Images.large(fileName);
            user.setLargeAvatar(large);
            res.setMessage(large);
            String middle = Images.middle(fileName);
            user.setMediumAvatar(middle);
            String small = Images.small(fileName);
            user.setSmallAvatar(small);

            userService.updateUser(user);
        } catch (Exception e) {
            log.error(e.getMessage());
            res.setStatus(AppConstants.FAIL);
            res.setMessage(e.getMessage());
        }
        return res;
    }

}
