package com.kangyonggan.controller;

import com.kangyonggan.model.Attachment;
import com.kangyonggan.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 网站首页
 *
 * @author kangyonggan
 * @since 16/5/15
 */
@Controller
@RequestMapping
public class IndexController {

    private static final String PATH_ROOT = "web/index/";
    private static final String PATH_INDEX = PATH_ROOT + "index";

    /**
     * 网站首页
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return PATH_INDEX;
    }

    @RequestMapping(value = "404", method = RequestMethod.GET)
    public String error404() {
        return "error/404";
    }

    @RequestMapping(value = "405", method = RequestMethod.GET)
    public String error405() {
        return "error/405";
    }

    @RequestMapping(value = "400", method = RequestMethod.GET)
    public String error400() {
        return "error/400";
    }
}
