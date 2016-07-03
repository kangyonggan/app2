package com.kangyonggan.service.impl;

import com.kangyonggan.model.Page;
import com.kangyonggan.service.PageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author kangyonggan
 * @since 2016/05/24
 */
@Service
@Transactional
public class PageServiceImpl extends BaseService<Page> implements PageService {

    @Override
    public List<Page> findPagesByUserId(Long userId) {
        Example example = new Example(Page.class);
        example.createCriteria().andEqualTo("userId", userId).andEqualTo("isDeleted", 0);

        example.setOrderByClause("sort asc");
        return super.selectByExample(example);
    }

    @Override
    public Page getPage(Long id) {
        return super.selectByPrimaryKey(id);
    }

    @Override
    public void savePage(Page page) {
        page.setCreatedTime(new Date());
        page.setUpdatedTime(new Date());
        super.insertSelective(page);
    }

    @Override
    public void updatePage(Page page) {
        page.setUpdatedTime(new Date());
        super.updateByPrimaryKeySelective(page);
    }

    @Override
    public void deletePage(Long id) {
        Page page = getPage(id);
        page.setIsDeleted((byte) 1);

        updatePage(page);
    }
}
