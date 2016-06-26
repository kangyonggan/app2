package com.kangyonggan.service.impl;

import com.github.pagehelper.PageHelper;
import com.kangyonggan.mapper.ArticleMapper;
import com.kangyonggan.model.Article;
import com.kangyonggan.service.ArticleService;
import com.kangyonggan.service.UserService;
import com.kangyonggan.util.DateUtil;
import com.kangyonggan.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ArticleServiceImpl extends BaseService<Article> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UserService userService;

    @Override
    public List<Article> searchArticles(int pageNum, int pageSize, String code, Long userId, String title, String startTime, String endTime) throws Exception {
        Example example = new Example(Article.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("categoryCode", code);
        criteria.andEqualTo("userId", userId);
        criteria.andEqualTo("isDeleted", 0);

        if (StringUtil.isNotEmpty(title)) {
            criteria.andEqualTo("title", title);
        }
        if (StringUtil.isNotEmpty(startTime)) {
            Date start = DateUtil.parse(startTime);
            criteria.andGreaterThanOrEqualTo("createdTime", start);
        }
        if (StringUtil.isNotEmpty(endTime)) {
            Date end = DateUtil.next(endTime);
            criteria.andLessThanOrEqualTo("createdTime", end);
        }

        // 查看自己的文章不需要花样排序, 只想看最新的
        example.setOrderByClause("id desc");

        PageHelper.startPage(pageNum, pageSize);
        return super.selectByExample(example);
    }

    @Override
    public List<Article> findArticesByCategoryCode(int pageNum, int pageSize, String categoryCode) {
        PageHelper.startPage(pageNum, pageSize);
        return articleMapper.selectArticlesByCategoryCode(categoryCode);
    }

    @Override
    public Article getArticle(Long id) {
        return super.selectByPrimaryKey(id);
    }

    @Override
    public void saveArticle(Article article) {
        article.setCreatedTime(new Date());
        article.setUpdatedTime(new Date());
        article.setUserId(userService.getShiroUser().getId());

        super.insertSelective(article);
    }

    @Override
    public void deleteArticleWithLogic(Article article) {
        article.setIsDeleted((byte) 1);
        article.setUpdatedTime(new Date());

        super.updateByPrimaryKeySelective(article);
    }

    @Override
    public void updateArticle(Article article) {
        article.setUpdatedTime(new Date());
        super.updateByPrimaryKeySelective(article);
    }

    @Override
    public void deleteArticle(Long id) {
        super.deleteByPrimaryKey(id);
    }
}
