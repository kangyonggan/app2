package com.kangyonggan.controller.pits;

import com.kangyonggan.constants.AppConstants;
import com.kangyonggan.model.Page;
import com.kangyonggan.model.ShiroUser;
import com.kangyonggan.model.ValidationResponse;
import com.kangyonggan.service.PageService;
import com.kangyonggan.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 页面管理
 *
 * @author kangyonggan
 * @since 16/6/21
 */
@Controller
@RequestMapping("pits/page")
public class PitsPageController {

    private static final String PATH_ROOT = "pits/page/";
    private static final String PATH_LIST = PATH_ROOT + "list";
    private static final String PATH_FORM_MODAL = PATH_ROOT + "form-modal";
    private static final String PATH_DETAIL_MODAL = PATH_ROOT + "detail-modal";

    @Autowired
    private UserService userService;

    @Autowired
    private PageService pageService;

    /**
     * 页面管理界面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @RequiresPermissions("pits-page")
    public String index(Model model) {
        ShiroUser user = userService.getShiroUser();

        List<Page> pages = pageService.findPagesByUserId(user.getId());
        if (user.getId().equals(1L)) {
            List<Page> publicPages = pageService.findPagesByUserId(0L);
            model.addAttribute("publicPages", publicPages);
        }

        model.addAttribute("pages", pages);
        return PATH_LIST;
    }

    /**
     * 添加页面
     *
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    @RequiresPermissions("pits-page")
    public String create(Model model) {
        model.addAttribute("item", new Page());
        return PATH_FORM_MODAL;
    }

    /**
     * 保存页面
     *
     * @param page
     * @param result
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("pits-page")
    public ValidationResponse save(@ModelAttribute("page") @Valid Page page,
                                   BindingResult result) {
        ValidationResponse res = new ValidationResponse(AppConstants.FAIL);
        if (!result.hasErrors()) {
            ShiroUser user = userService.getShiroUser();
            if (!"0".equals(page.getUserId())) {
                page.setUserId(user.getId());
            } else if (!"1".equals(page.getUserId())) {
                throw new RuntimeException("权限不足");
            }
            pageService.savePage(page);
            res.setStatus(AppConstants.SUCCESS);
        }
        return res;
    }

    /**
     * 编辑页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    @RequiresPermissions("pits-page")
    public String edit(@RequestParam("id") Long id, Model model) {
        Page page = pageService.getPage(id);
        model.addAttribute("item", page);
        return PATH_FORM_MODAL;
    }

    /**
     * 更新页面
     *
     * @param page
     * @param result
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("pits-page")
    public ValidationResponse update(@ModelAttribute("page") @Valid Page page,
                                     BindingResult result) {
        ValidationResponse res = new ValidationResponse(AppConstants.FAIL);

        if (!result.hasErrors()) {
            pageService.updatePage(page);
            res.setStatus(AppConstants.SUCCESS);
        }

        return res;
    }

    /**
     * 查看页面详情
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("pits-page")
    public String detail(@RequestParam("id") Long id, Model model) {
        model.addAttribute("item", pageService.getPage(id));
        return PATH_DETAIL_MODAL;
    }


    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("pits-page")
    public ValidationResponse delete(@RequestParam("id") Long id) {
        pageService.deletePage(id);
        return new ValidationResponse(AppConstants.SUCCESS);
    }
}
