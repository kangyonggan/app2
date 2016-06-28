package com.kangyonggan.service.impl;

import com.github.pagehelper.PageHelper;
import com.kangyonggan.mapper.ArticleMapper;
import com.kangyonggan.mapper.AttachmentMapper;
import com.kangyonggan.model.Article;
import com.kangyonggan.model.Attachment;
import com.kangyonggan.model.Reply;
import com.kangyonggan.model.ShiroUser;
import com.kangyonggan.service.ArticleService;
import com.kangyonggan.service.AttachmentService;
import com.kangyonggan.service.ReplyService;
import com.kangyonggan.service.UserService;
import com.kangyonggan.util.Collections3;
import com.kangyonggan.util.DateUtil;
import com.kangyonggan.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * @author kangyonggan
 * @since 2016/05/24
 */
@Service
@Transactional
public class AttachmentServiceImpl extends BaseService<Attachment> implements AttachmentService {

    @Autowired
    private AttachmentMapper attachmentMapper;

    @Autowired
    private UserService userService;

    @Override
    public void saveAttachments(Long articleId, List<String> filenames) {
        ShiroUser user = userService.getShiroUser();
        Map<String, Object> param = new HashMap();
        param.put("articleId", articleId);
        param.put("userId", user.getId());
        param.put("filenames", filenames);
        param.put("now", new Date());

        attachmentMapper.insertAttachments(param);
    }

    @Override
    public List<Attachment> findAttachmentsByArticleId(Long articleId) {
        Attachment attachment = new Attachment();
        attachment.setIsDeleted((byte) 0);
        attachment.setArticleId(articleId);

        return super.select(attachment);
    }
}
