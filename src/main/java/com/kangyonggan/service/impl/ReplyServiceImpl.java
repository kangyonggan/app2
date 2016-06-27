package com.kangyonggan.service.impl;

import com.kangyonggan.model.Reply;
import com.kangyonggan.service.ReplyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author kangyonggan
 * @since 2016/05/24
 */
@Service
@Transactional
public class ReplyServiceImpl extends BaseService<Reply> implements ReplyService {

    @Override
    public void saveReply(Reply reply) {
        reply.setCreatedTime(new Date());
        reply.setUpdatedTime(new Date());

        super.insertSelective(reply);
    }
}
