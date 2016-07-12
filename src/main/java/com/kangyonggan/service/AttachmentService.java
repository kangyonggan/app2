package com.kangyonggan.service;

import com.kangyonggan.model.Attachment;

import java.util.List;

/**
 * @author kangyonggan
 * @since 16/6/25
 */
public interface AttachmentService {

    List<Attachment> findAttachmentsByArticleId(Long articleId);

    List<Attachment> findAttachmentsByUserIdAndType(int pageNum, int pageSize, Long userId, String type);

    Attachment getAttachment(Long id);

    void saveAttachments(Long articleId, List<Attachment> files);

    void saveAttachment(Attachment attachment);

    void updateAttachments(String ids, Long articleId);

    void updateAttachment(Attachment attachment);

    void deleteAttachmentsByArticleId(Long articleId);

    void deleteAttachmentsOfInvalid();
}
