package com.kangyonggan.service.impl;

import com.kangyonggan.mapper.ReplyMapper;
import com.kangyonggan.model.Article;
import com.kangyonggan.model.Reply;
import com.kangyonggan.model.ShiroUser;
import com.kangyonggan.service.ArticleService;
import com.kangyonggan.service.ReplyService;
import com.kangyonggan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author kangyonggan
 * @since 2016/05/24
 */
@Service
@Transactional
public class ReplyServiceImpl extends BaseService<Reply> implements ReplyService {

    @Autowired
    private ReplyMapper replyMapper;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Override
    public List<Reply> findRepliesByArticleId(Long articleId) {
        return replyMapper.selectReplyByArticleId(articleId);
    }

    @Override
    public List<Reply> findUserReplies() {
        ShiroUser user = userService.getShiroUser();
        Example example = new Example(Reply.class);
        example.createCriteria().andEqualTo("userId", user.getId()).andEqualTo("isDeleted", 0);

        example.setOrderByClause("id desc");
        example.setDistinct(true);
        return super.selectByExample(example);
    }

    @Override
    public Reply getReply(Long id) {
        return super.selectByPrimaryKey(id);
    }

    @Override
    public void saveReply(Reply reply) {
        reply.setCreatedTime(new Date());
        reply.setUpdatedTime(new Date());

        super.insertSelective(reply);

        updateArticleReplyCount(reply);
    }

    @Override
    public void deleteArticleReplyById(Long id) {
        Reply reply = getReply(id);
        reply.setIsDeleted((byte) 1);
        updateReply(reply);

        Article article = articleService.getArticle(reply.getArticleId());
        article.setReply(article.getReply() - 1);
        articleService.updateArticle(article);
    }

    @Override
    public void updateReply(Reply reply) {
        reply.setUpdatedTime(new Date());
        super.updateByPrimaryKeySelective(reply);
    }

    private void updateArticleReplyCount(Reply reply) {
        Article article = articleService.getArticle(reply.getArticleId());
        article.setReply(article.getReply() + 1);

        articleService.updateArticle(article);
    }
}
