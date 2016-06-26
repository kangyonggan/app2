package com.kangyonggan.controller.pits;

import com.kangyonggan.constants.AppConstants;
import com.kangyonggan.model.Category;
import com.kangyonggan.model.ValidationResponse;
import com.kangyonggan.service.CategoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 栏目管理
 *
 * @author kangyonggan
 * @since 16/6/21
 */
@Controller
@RequestMapping("pits/category")
public class PitsCategoryController {

    private static final String PATH_ROOT = "pits/category/";
    private static final String PATH_INDEX = PATH_ROOT + "index";
    private static final String PATH_FORM_MODAL = PATH_ROOT + "form-modal";

    @Autowired
    private CategoryService categoryService;

    /**
     * 栏目管理界面
     *
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("pits-category")
    public String index(Model model) {
        List<Category> categories = categoryService.findAllCategories();

        model.addAttribute("categories", categories);
        return PATH_INDEX;
    }


    /**
     * 添加栏目
     *
     * @param pid
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    @RequiresPermissions("pits-category")
    public String create(@RequestParam(value = "pid", defaultValue = "0") Long pid,
                         Model model) {
        model.addAttribute("item", new Category());
        model.addAttribute("parent_item", categoryService.getCategory(pid));
        return PATH_FORM_MODAL;
    }

    /**
     * 保存栏目
     *
     * @param category
     * @param result
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("pits-category")
    public ValidationResponse save(@ModelAttribute("category") @Valid Category category,
                                   BindingResult result) {
        ValidationResponse res = new ValidationResponse(AppConstants.FAIL);
        if (!result.hasErrors()) {
            categoryService.saveCategory(category);
            res.setStatus(AppConstants.SUCCESS);
        }
        return res;
    }

    /**
     * 编辑栏目
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/edit", method = RequestMethod.GET)
    @RequiresPermissions("pits-category")
    public String edit(@PathVariable Long id, Model model) {
        Category category = categoryService.getCategory(id);
        model.addAttribute("item", category);
        model.addAttribute("parent_item", categoryService.getCategory(category.getPid()));
        return PATH_FORM_MODAL;
    }

    /**
     * 更新栏目
     *
     * @param category
     * @param result
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/update", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("pits-category")
    public ValidationResponse update(@ModelAttribute("category") @Valid Category category,
                                     BindingResult result) {
        ValidationResponse res = new ValidationResponse(AppConstants.FAIL);

        if (!result.hasErrors()) {
            categoryService.updateCategory(category);
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
    @RequiresPermissions("pits-category")
    public ValidationResponse delete(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new ValidationResponse(AppConstants.SUCCESS);
    }

    /**
     * 校验栏目代码唯一性
     *
     * @param code
     * @param oldCode
     * @return
     */
    @RequestMapping(value = "verify-code", method = RequestMethod.POST)
    @RequiresPermissions("pits-category")
    @ResponseBody
    public boolean verifyCode(@RequestParam String code, @RequestParam String oldCode) {
        if (oldCode.equals(code)) {
            return true;
        }
        return categoryService.findCategoryByCode(code) == null;
    }
}
