package com.kangyonggan.service;

import com.kangyonggan.model.Reply;

import java.util.List;

/**
 * @author kangyonggan
 * @since 16/6/25
 */
public interface ReplyService {

    List<Reply> findRepliesByArticleId(Long articleId);

    Reply getReply(Long id);

    void saveReply(Reply reply);

    void deleteArticleReplyById(Long id);

    void updateReply(Reply reply);

}
