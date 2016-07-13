package com.kangyonggan.controller;

import com.github.pagehelper.PageInfo;
import com.kangyonggan.constants.AppConstants;
import com.kangyonggan.model.*;
import com.kangyonggan.service.ArticleService;
import com.kangyonggan.service.AttachmentService;
import com.kangyonggan.service.CategoryService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private static final String PATH_MY = PATH_ROOT + "my";

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private AttachmentService attachmentService;

    /**
     * Ta的主页
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index(@RequestParam(value = "p", required = false, defaultValue = "1") int pageNum,
                        @RequestParam("id") Long id, Model model) {
        User user = userService.getUser(id);
        if (user == null) {
            return "redirect:/404";
        }
        List<Attachment> attachments = attachmentService.findAttachmentsByUserIdAndType(pageNum, 12, user.getId(), "picture");
        PageInfo<Attachment> page = new PageInfo(attachments);

        model.addAttribute("user", user);
        model.addAttribute("page", page);
        return PATH_INDEX;
    }

    /**
     * 我收藏的/我订阅的/我参与的
     *
     * @param pageNum
     * @param model
     * @return
     */
    @RequestMapping(value = "article/{code:\\bstar\\b|\\brss\\b|\\bbell\\b}", method = RequestMethod.GET)
    public String star(@RequestParam(value = "p", required = false, defaultValue = "1") int pageNum,
                       @PathVariable("code") String code,
                       Model model) {
        List<Article> articles = null;
        if (code.equals("star")) {
            articles = articleService.findStarArticles(pageNum, AppConstants.PAGE_SIZE);
        } else if (code.equals("bell")) {
            articles = articleService.findBellArticles(pageNum, AppConstants.PAGE_SIZE);
        }
        PageInfo<Article> page = new PageInfo(articles);
        Category category = categoryService.findCategoryByCode(code);

        model.addAttribute("category", category);
        model.addAttribute("page", page);
        return PATH_MY;
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

    @RequestMapping(value = "password/update", method = RequestMethod.POST)
    public String password(@RequestParam("id") Long id, @RequestParam("password") String password) {
        User user = userService.getUser(id);
        user.setPassword(password);
        userService.updateUserPassword(user);

        return "redirect:/login";
    }

    /**
     * 获取header信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "header", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getHeaderArticleMap(@RequestParam("id") Long id) {
        Article article_header = articleService.findTotalArticleByUserId(id);
        Article video_header = articleService.findTotalArticleByUserIdWithGroup(id, "video");
        Article music_header = articleService.findTotalArticleByUserIdWithGroup(id, "music");
        Article picture_header = articleService.findTotalArticleByUserIdWithGroup(id, "picture");
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
        return map;
    }

}
