package com.kangyonggan.service.impl;

import com.kangyonggan.mapper.ReplyMapper;
import com.kangyonggan.model.Article;
import com.kangyonggan.model.Reply;
import com.kangyonggan.service.ArticleService;
import com.kangyonggan.service.ReplyService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public Reply findReplyById(Long id) {
        return replyMapper.selectReplyById(id);
    }

    @Override
    public void saveReply(Reply reply) {
        reply.setCreatedTime(new Date());
        reply.setUpdatedTime(new Date());

        super.insertSelective(reply);

        updateArticleReplyCount(reply);
    }

    private void updateArticleReplyCount(Reply reply) {
        Article article = articleService.getArticle(reply.getArticleId());
        article.setReply(article.getReply() + 1);

        articleService.updateArticle(article);
    }
}
