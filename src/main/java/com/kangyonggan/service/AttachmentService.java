package com.kangyonggan.service;

import com.kangyonggan.model.Attachment;

import java.util.List;

/**
 * @author kangyonggan
 * @since 16/6/25
 */
public interface AttachmentService {

    void saveAttachments(Long articleId, List<Attachment> files);

    List<Attachment> findAttachmentsByArticleId(Long articleId);

}
