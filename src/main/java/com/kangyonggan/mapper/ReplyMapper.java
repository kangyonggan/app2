package com.kangyonggan.mapper;

import com.kangyonggan.model.Reply;
import com.kangyonggan.util.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyMapper extends MyMapper<Reply> {

    Reply selectReplyById(Long id);

    List<Reply> selectReplyByArticleId(Long articleId);
}