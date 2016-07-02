package com.kangyonggan.service;

import com.kangyonggan.model.Attachment;

import java.util.List;

/**
 * @author kangyonggan
 * @since 16/6/25
 */
public interface AttachmentService {

    List<Attachment> findAttachmentsByArticleId(Long articleId);

    void saveAttachments(Long articleId, List<Attachment> files);

    void saveAttachment(Attachment attachment);

    void updateAttachments(String ids, Long articleId);
}
