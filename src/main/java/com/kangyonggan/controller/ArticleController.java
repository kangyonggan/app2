package com.kangyonggan.controller;

import com.kangyonggan.constants.AppConstants;
import com.kangyonggan.model.Article;
import com.kangyonggan.model.Reply;
import com.kangyonggan.model.ShiroUser;
import com.kangyonggan.model.ValidationResponse;
import com.kangyonggan.service.ArticleService;
import com.kangyonggan.service.ReplyService;
import com.kangyonggan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author kangyonggan
 * @since 16/6/25
 */
@Controller
@RequestMapping("article")
public class ArticleController {

    private static final String PATH_ROOT = "web/article/";
    private static final String PATH_DETAIL = PATH_ROOT + "detail";
    private static final String PATH_REPLY_MODAL = PATH_ROOT + "reply-modal";

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ReplyService replyService;

    /**
     * 文章详情
     *
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String detail(@RequestParam("id") Long id, Model model) {
        Article article = articleService.findArticleById(id);
        List<Reply> replies = replyService.findRepliesByArticleId(id);

        model.addAttribute("article", article);
        model.addAttribute("replies", replies);
        return PATH_DETAIL;
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

        if (!user.getId().equals(reply.getUserId())) {
            res.setStatus(AppConstants.FAIL);
            return res;
        }

        replyService.deleteArticleReplyById(id);
        return res;
    }

}
