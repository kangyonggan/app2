package com.kangyonggan.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
