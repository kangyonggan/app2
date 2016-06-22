package com.kangyonggan.controller.sys;

import com.github.pagehelper.PageInfo;
import com.kangyonggan.constants.AppConstants;
import com.kangyonggan.model.Role;
import com.kangyonggan.model.ValidationResponse;
import com.kangyonggan.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 角色管理
 *
 * @author kangyonggan
 * @since 16/6/21
 */
@Controller
@RequestMapping("sys/role")
public class SysRoleController {

    private static final String PATH_ROOT = "sys/role/";
    private static final String PATH_LIST = PATH_ROOT + "list";
    private static final String PATH_FORM_MODAL = PATH_ROOT + "form-modal";
    private static final String PATH_DETAIL_MODAL = PATH_ROOT + "detail-modal";

    @Autowired
    private RoleService roleService;

    /**
     * 角色列表
     *
     * @param pageNum
     * @param name
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("sys-role")
    public String list(@RequestParam(value = "p", required = false, defaultValue = "1") int pageNum,
                       @RequestParam(value = "name", required = false, defaultValue = "") String name,
                       Model model) {
        List<Role> roles = roleService.searchRoles(pageNum, AppConstants.PAGE_SIZE, name);
        PageInfo<Role> page = new PageInfo(roles);

        model.addAttribute("page", page);
        return PATH_LIST;
    }

    /**
     * 添加角色
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    @RequiresPermissions("sys-role")
    public String create(Model model) {
        model.addAttribute("item", new Role());
        return PATH_FORM_MODAL;
    }

    /**
     * 保存角色
     *
     * @param role
     * @param result
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys-role")
    public ValidationResponse save(@ModelAttribute("role") @Valid Role role, BindingResult result) {
        ValidationResponse res = new ValidationResponse(AppConstants.SUCCESS);

        if (!result.hasErrors()) {
            roleService.saveRole(role);
        } else {
            res.setStatus(AppConstants.FAIL);
        }

        return res;
    }

    /**
     * 编辑角色
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/edit", method = RequestMethod.GET)
    @RequiresPermissions("sys-role")
    public String create(@PathVariable("id") Long id, Model model) {
        model.addAttribute("item", roleService.getRole(id));
        return PATH_FORM_MODAL;
    }

    /**
     * 更新角色
     *
     * @param role
     * @param result
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/update", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys-role")
    public ValidationResponse update(@ModelAttribute("role") @Valid Role role, BindingResult result) {
        ValidationResponse res = new ValidationResponse(AppConstants.SUCCESS);

        if (!result.hasErrors()) {
            roleService.updateRole(role);
        } else {
            res.setStatus(AppConstants.FAIL);
        }

        return res;
    }

    /**
     * 查看角色详情
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}", method = RequestMethod.GET)
    @RequiresPermissions("sys-role")
    public String detail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("item", roleService.getRole(id));
        return PATH_DETAIL_MODAL;
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/delete", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions("sys-role")
    public ValidationResponse delete(@PathVariable("id") Long id) {
        roleService.deleteRole(id);
        return new ValidationResponse(AppConstants.SUCCESS);
    }

    /**
     * 校验角色代码唯一性
     *
     * @param code
     * @param oldCode
     * @return
     */
    @RequestMapping(value = "/verify-code", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys-role")
    public boolean verifyCode(@RequestParam String code, @RequestParam String oldCode) {
        if (oldCode.equals(code)) {
            return true;
        }
        return roleService.findRoleByCode(code) == null;
    }

}
