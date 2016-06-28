package com.kangyonggan.service.impl;

import com.kangyonggan.mapper.ReplyMapper;
import com.kangyonggan.model.Article;
import com.kangyonggan.model.Reply;
import com.kangyonggan.service.ArticleService;
import com.kangyonggan.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public List<Reply> findRepliesByArticleId(Long articleId) {
        return replyMapper.selectReplyByArticleId(articleId);
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
