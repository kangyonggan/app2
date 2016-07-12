package com.kangyonggan.mapper;

import com.kangyonggan.model.Attachment;
import com.kangyonggan.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AttachmentMapper extends MyMapper<Attachment> {

    void insertAttachments(@Param("articleId") Long articleId, @Param("userId") Long userId, @Param("now") Date now, @Param("files") List<Attachment> files);

    void updateAttachments(@Param("ids") String[] ids, @Param("articleId") Long articleId);

    void deleteAttachmentsOfInvalid();

}