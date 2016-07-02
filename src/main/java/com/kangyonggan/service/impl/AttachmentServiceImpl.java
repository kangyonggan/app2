package com.kangyonggan.service.impl;

import com.github.pagehelper.PageHelper;
import com.kangyonggan.mapper.AttachmentMapper;
import com.kangyonggan.model.Attachment;
import com.kangyonggan.model.ShiroUser;
import com.kangyonggan.service.AttachmentService;
import com.kangyonggan.service.UserService;
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
public class AttachmentServiceImpl extends BaseService<Attachment> implements AttachmentService {

    @Autowired
    private AttachmentMapper attachmentMapper;

    @Autowired
    private UserService userService;

    @Override
    public void saveAttachments(Long articleId, List<Attachment> files) {
        ShiroUser user = userService.getShiroUser();

        attachmentMapper.insertAttachments(articleId, user.getId(), new Date(), files);
    }

    @Override
    public void saveAttachment(Attachment attachment) {
        ShiroUser user = userService.getShiroUser();

        attachment.setUserId(user.getId());
        attachment.setCreatedTime(new Date());
        attachment.setUpdatedTime(new Date());
        super.insertSelective(attachment);
    }

    @Override
    public void updateAttachments(String ids, Long articleId) {
        attachmentMapper.updateAttachments(ids.split(","), articleId);
    }

    @Override
    public void updateAttachment(Attachment attachment) {
        attachment.setUpdatedTime(new Date());
        super.updateByPrimaryKeySelective(attachment);
    }

    @Override
    public List<Attachment> findAttachmentsByArticleId(Long articleId) {
        Attachment attachment = new Attachment();
        attachment.setIsDeleted((byte) 0);
        attachment.setArticleId(articleId);

        return super.select(attachment);
    }

    @Override
    public List<Attachment> findAttachmentsByUserIdAndType(int pageNum, int pageSize, Long userId, String type) {
        Attachment attachment = new Attachment();
        attachment.setIsDeleted((byte) 0);
        attachment.setUserId(userId);
        attachment.setType(type);

        PageHelper.startPage(pageNum, pageSize);
        return super.select(attachment);
    }

    @Override
    public Attachment getAttachment(Long id) {
        return super.selectByPrimaryKey(id);
    }
}
