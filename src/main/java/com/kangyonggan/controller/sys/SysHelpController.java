package com.kangyonggan.controller.sys;

import com.kangyonggan.constants.AppConstants;
import com.kangyonggan.service.AttachmentService;
import com.kangyonggan.util.Shell;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 执行shell命令
     *
     * @return
     */
    @RequestMapping(value = "shell", method = RequestMethod.GET)
    @RequiresPermissions("sys")
    @ResponseBody
    public List shell(@RequestParam("shell") String shell) {
        List list = new ArrayList();
        try {
            list = Shell.run(shell);
        } catch (Exception e) {
            e.printStackTrace();
            list.add("命令执行异常: " + e.getMessage());
        }
        return list;
    }

}
