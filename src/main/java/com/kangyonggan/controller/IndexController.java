package com.kangyonggan.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * 网站首页
 *
 * @author kangyonggan
 * @since 16/5/15
 */
@Controller
@RequestMapping
@Log4j2
public class IndexController {

    private static final String PATH_ROOT = "web/index/";
    private static final String PATH_INDEX = PATH_ROOT + "index";

    @RequestMapping("test")
    public void test(HttpServletRequest request) {
        log.info("交行一键支付-进入回调servlet");
        try {
            request.setCharacterEncoding("GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            log.error("交行一键支付-签约回调异常：", e);
            return;
        }

        String  notifyMsg = request.getParameter("notifyMsg"); //获取银行通知结果
        log.info(notifyMsg);
    }

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
