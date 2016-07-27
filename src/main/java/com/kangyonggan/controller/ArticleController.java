package com.kangyonggan.controller;

import com.github.pagehelper.PageInfo;
import com.kangyonggan.constants.AppConstants;
import com.kangyonggan.model.*;
import com.kangyonggan.service.ArticleService;
import com.kangyonggan.service.AttachmentService;
import com.kangyonggan.service.ReplyService;
import com.kangyonggan.service.UserService;
import com.kangyonggan.util.FenCi;
import com.kangyonggan.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author kangyonggan
 * @since 16/6/25
 */
@Controller
@RequestMapping("article")
public class ArticleController {

    private static final String PATH_ROOT = "web/article/";
    private static final String PATH_DETAIL = PATH_ROOT + "detail";
    private static final String PATH_LIST = PATH_ROOT + "list";
    private static final String PATH_REPLY_MODAL = PATH_ROOT + "reply-modal";

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private AttachmentService attachmentService;

    /**
     * 文章详情
     *
     * @param id
     * @param password
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String detail(@RequestParam("id") Long id, @RequestParam(value = "password", required = false, defaultValue = "") String password,
                         Model model) {
        Article article = articleService.findArticleById(id);
        List<Attachment> attachments = attachmentService.findAttachmentsByArticleId(article.getId());
        List<Reply> replies = replyService.findRepliesByArticleId(id);

        if (StringUtil.isEmpty(article.getPassword()) || password.equals(article.getPassword())) {
            model.addAttribute("attachments", attachments);
        } else {
            article.setBody("密码不正确!!!");

            // 自己(已登录)可以查看到文章密码
            ShiroUser user = userService.getShiroUser();
            if (user == null || user.getId() - article.getUserId() != 0) {
                model.addAttribute("article_password", "error");
            } else {
                model.addAttribute("attachments", attachments);
            }
        }
        model.addAttribute("article", article);
        model.addAttribute("replies", replies);
        return PATH_DETAIL;
    }

    /**
     * 文章内容
     *
     * @param id
     * @param password
     * @return
     */
    @RequestMapping(value = "body", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String body(@RequestParam("id") Long id, @RequestParam(value = "password", required = false, defaultValue = "") String password) {
        Article article = articleService.findArticleById(id);

        if (StringUtil.isEmpty(article.getPassword()) || password.equals(article.getPassword())) {
            return article.getBody();
        }

        // 自己(已登录)可以查看
        ShiroUser user = userService.getShiroUser();
        if (user != null && user.getId() - article.getUserId() == 0) {
            return article.getBody();
        }

        return "密码不正确!!!";
    }

    /**
     * 顶/踩
     *
     * @param id
     * @param action
     * @return
     */
    @RequestMapping(value = "{action:\\btop\\b|\\blow\\b|\\bstar\\b}", method = RequestMethod.GET)
    @ResponseBody
    public ValidationResponse actions(@RequestParam("id") Long id, @PathVariable("action") String action) {
        ValidationResponse res = new ValidationResponse(AppConstants.SUCCESS);

        boolean success = articleService.updateArticleActions(id, action);

        if (!success) {
            res.setStatus(AppConstants.FAIL);
        }

        return res;
    }

    /**
     * 评论
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "reply", method = RequestMethod.GET)
    public String reply(@RequestParam("id") Long id, Model model) {
        model.addAttribute("article", articleService.findArticleById(id));
        return PATH_REPLY_MODAL;
    }

    /**
     * 提交评论common
     *
     * @param reply
     * @return
     */
    @RequestMapping(value = "reply2", method = RequestMethod.POST)
    public String reply(@ModelAttribute("reply") @Valid Reply reply, BindingResult result) {

        if (!result.hasErrors()) {
            ShiroUser user = userService.getShiroUser();
            reply.setUserId(user.getId());

            replyService.saveReply(reply);
        }

        return String.format("redirect:/article?id=%d", reply.getArticleId());
    }

    /**
     * 提交评论ajax
     *
     * @param reply
     * @return
     */
    @RequestMapping(value = "reply", method = RequestMethod.POST)
    @ResponseBody
    public ValidationResponse replyAjax(@ModelAttribute("reply") @Valid Reply reply, BindingResult result) {
        ValidationResponse res = new ValidationResponse(AppConstants.FAIL);

        if (!result.hasErrors()) {
            ShiroUser user = userService.getShiroUser();
            reply.setUserId(user.getId());

            replyService.saveReply(reply);
            res.setStatus(AppConstants.SUCCESS);
        }

        return res;
    }

    /**
     * 删除评论
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "reply/delete", method = RequestMethod.GET)
    @ResponseBody
    public ValidationResponse replyDelete(@RequestParam("id") Long id) {
        ValidationResponse res = new ValidationResponse(AppConstants.SUCCESS);
        ShiroUser user = userService.getShiroUser();
        Reply reply = replyService.getReply(id);

        if (user == null || !user.getId().equals(reply.getUserId())) {
            res.setStatus(AppConstants.FAIL);
            return res;
        }

        replyService.deleteArticleReplyById(id);
        return res;
    }

    /**
     * 删除附件
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "attachment/delete", method = RequestMethod.GET)
    @ResponseBody
    public ValidationResponse attachmentDelete(@RequestParam("id") Long id) {
        ValidationResponse res = new ValidationResponse(AppConstants.SUCCESS);
        ShiroUser user = userService.getShiroUser();
        Attachment attachment = attachmentService.getAttachment(id);

        if (user == null || !user.getId().equals(attachment.getUserId())) {
            res.setStatus(AppConstants.FAIL);
            return res;
        }

        attachment.setIsDeleted((byte) 1);
        attachmentService.updateAttachment(attachment);
        return res;
    }

    /**
     * 全文检索文章
     *
     * @param pageNum
     * @param key
     * @param model
     * @return
     */
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String search(@RequestParam(value = "p", required = false, defaultValue = "1") int pageNum,
                         @RequestParam(value = "key", required = false, defaultValue = "") String key,
                         Model model) {
        key = FenCi.process(key.trim());
        List<Article> articles = articleService.findArticesByKey(pageNum, AppConstants.PAGE_SIZE, key);
        PageInfo<Article> page = new PageInfo(articles);

        model.addAttribute("page", page);
        return PATH_LIST;
    }

}
