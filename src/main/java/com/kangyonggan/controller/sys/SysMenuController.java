package com.kangyonggan.controller.sys;

import com.kangyonggan.constants.AppConstants;
import com.kangyonggan.model.Menu;
import com.kangyonggan.model.ValidationResponse;
import com.kangyonggan.service.MenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 菜单管理
 *
 * @author kangyonggan
 * @since 16/6/21
 */
@Controller
@RequestMapping("sys/menu")
public class SysMenuController {

    private static final String PATH_ROOT = "sys/menu/";
    private static final String PATH_INDEX = PATH_ROOT + "index";
    private static final String PATH_FORM_MODAL = PATH_ROOT + "form-modal";

    @Autowired
    private MenuService menuService;

    /**
     * 菜单管理界面
     *
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("sys-menu")
    public String index(Model model) {
        List<Menu> menus = menuService.findAllMenus();

        model.addAttribute("menus", menus);
        return PATH_INDEX;
    }


    /**
     * 添加菜单
     *
     * @param pid
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    @RequiresPermissions("sys-menu")
    public String create(@RequestParam(value = "pid", defaultValue = "0") Long pid,
                         Model model) {
        model.addAttribute("menu", new Menu());
        model.addAttribute("parent_menu", menuService.getMenu(pid));
        return PATH_FORM_MODAL;
    }

    /**
     * 保存菜单
     *
     * @param menu
     * @param result
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys-menu")
    public ValidationResponse save(@ModelAttribute("menu") @Valid Menu menu,
                                   BindingResult result) {
        ValidationResponse res = new ValidationResponse(AppConstants.FAIL);
        if (!result.hasErrors()) {
            menuService.saveMenu(menu);
        }
        return res;
    }

    /**
     * 编辑菜单
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/edit", method = RequestMethod.GET)
    @RequiresPermissions("sys-menu")
    public String edit(@PathVariable Long id, Model model) {
        Menu menu = menuService.getMenu(id);
        model.addAttribute("menu", menu);
        model.addAttribute("parent_menu", menuService.getMenu(menu.getPid()));
        return PATH_FORM_MODAL;
    }

    /**
     * 更新菜单
     *
     * @param menu
     * @param result
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/update", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys-menu")
    public ValidationResponse update(@ModelAttribute("menu") @Valid Menu menu,
                                     BindingResult result) {
        ValidationResponse res = new ValidationResponse(AppConstants.FAIL);

        if (!result.hasErrors()) {
            menuService.updateMenu(menu);
            res.setStatus(AppConstants.SUCCESS);
        }

        return res;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/delete", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys-menu")
    public ValidationResponse delete(@PathVariable Long id) {
        menuService.deleteMenu(id);
        return new ValidationResponse(AppConstants.SUCCESS);
    }

    /**
     * 校验菜单代码唯一性
     *
     * @param code
     * @param old_code
     * @return
     */
    @RequestMapping(value = "verify-code", method = RequestMethod.POST)
    @RequiresPermissions("sys-menu")
    @ResponseBody
    public boolean verifyCode(@RequestParam String code, @RequestParam String old_code) {
        if (old_code.equals(code)) {
            return true;
        }
        return menuService.findMenuByCode(code) == null;
    }
}
