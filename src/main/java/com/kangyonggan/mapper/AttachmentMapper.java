package com.kangyonggan.mapper;

import com.kangyonggan.model.Attachment;
import com.kangyonggan.util.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface AttachmentMapper extends MyMapper<Attachment> {

    void insertAttachments(Map<String, Object> param);

}