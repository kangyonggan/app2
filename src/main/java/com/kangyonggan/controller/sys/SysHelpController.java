package com.kangyonggan.controller.sys;

import com.kangyonggan.constants.AppConstants;
import com.kangyonggan.service.AttachmentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kangyonggan
 * @since 16/7/12
 */
@RestController
@RequestMapping("sys/help")
public class SysHelpController {

    @Autowired
    private AttachmentService attachmentService;

    /**
     * 清除已无效的附件
     *
     * @return
     */
    @RequestMapping(value = "clean", method = RequestMethod.GET)
    @RequiresPermissions("sys")
    public String cleanInvalidAttachments() {
        attachmentService.deleteAttachmentsOfInvalid();
        return AppConstants.SUCCESS;
    }

}
