package com.kangyonggan.controller.sys;

import com.kangyonggan.util.Shell;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kangyonggan
 * @since 16/7/12
 */
@RestController
@RequestMapping("sys")
public class SysController {

    /**
     * 执行shell命令
     *
     * @return
     */
    @RequestMapping(value = "shell", method = RequestMethod.GET)
    @RequiresPermissions("sys")
    @ResponseBody
    public List shell(@RequestParam("cmd") String cmd) {
        List list = new ArrayList();
        try {
            list = Shell.run(cmd);
        } catch (Exception e) {
            e.printStackTrace();
            list.add("命令执行异常: " + e.getMessage());
        }
        return list;
    }

}
