package com.kangyonggan.service.impl;

import com.github.pagehelper.PageHelper;
import com.kangyonggan.mapper.ArticleMapper;
import com.kangyonggan.model.Article;
import com.kangyonggan.model.Attachment;
import com.kangyonggan.model.Reply;
import com.kangyonggan.model.ShiroUser;
import com.kangyonggan.service.ArticleService;
import com.kangyonggan.service.AttachmentService;
import com.kangyonggan.service.ReplyService;
import com.kangyonggan.service.UserService;
import com.kangyonggan.util.Collections3;
import com.kangyonggan.util.DateUtil;
import com.kangyonggan.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

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

    @Autowired
    private ReplyService replyService;

    @Autowired
    private AttachmentService attachmentService;

    @Override
    public List<Article> searchArticles(int pageNum, int pageSize, String code, String title, String startTime, String endTime) throws Exception {
        Example example = new Example(Article.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("categoryCode", code);
        criteria.andEqualTo("userId", userService.getShiroUser().getId());
        criteria.andEqualTo("isDeleted", 0);

        if (StringUtil.isNotEmpty(title)) {
            criteria.andLike("title", StringUtil.bothPercent(title));
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
    public List<Article> findStarArticles(int pageNum, int pageSize) {
        ShiroUser user = userService.getShiroUser();

        PageHelper.startPage(pageNum, pageSize);
        return articleMapper.selectStarArticles(user.getId());
    }

    @Override
    public List<Article> findBellArticles(int pageNum, int pageSize) {
        Map<String, Object> param = new HashMap();
        List<Reply> replies = replyService.findUserReplies();
        if (replies.isEmpty()) {
            return new ArrayList();
        }

        param.put("articleIds", Collections3.extractToList(replies, "articleId"));

        PageHelper.startPage(pageNum, pageSize);
        return articleMapper.selectArticlesInIds(param);
    }

    @Override
    public Article getArticle(Long id) {
        return super.selectByPrimaryKey(id);
    }

    @Override
    public Article findArticleById(Long id) {
        return articleMapper.selectArticleById(id);
    }

    @Override
    public Article findTotalArticleByUserId(Long userId) {
        return articleMapper.selectTotalArticleByUserId(userId);
    }

    @Override
    public Article findTotalArticleByUserIdWithGroup(Long userId, String group) {
        return articleMapper.selectTotalArticleByUserIdWithGroup(userId, group);
    }

    @Override
    public void saveArticle(Article article) {
        article.setCreatedTime(new Date());
        article.setUpdatedTime(new Date());
        article.setUserId(userService.getShiroUser().getId());

        super.insertSelective(article);
    }

    @Override
    public void saveArticle(Article article, List<Attachment> files) {
        article.setCreatedTime(new Date());
        article.setUpdatedTime(new Date());
        article.setUserId(userService.getShiroUser().getId());

        super.insertSelective(article);
        if (!files.isEmpty()) {
            attachmentService.saveAttachments(article.getId(), files);
        }
    }

    @Override
    public void saveArticle(Article article, String ids) {
        this.saveArticle(article);

        attachmentService.updateAttachments(ids, article.getId());
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
    public boolean updateArticleActions(Long id, String type) {
        ShiroUser user = userService.getShiroUser();

        int count = articleMapper.selectArticleUser(id, user.getId(), type);

        Article article = this.getArticle(id);
        if ("top".equals(type)) {
            article.setTop(article.getTop() + (count > 0 ? -1 : 1));
        } else if ("low".equals(type)) {
            article.setLow(article.getLow() + (count > 0 ? -1 : 1));
        } else if ("star".equals(type)) {
            article.setStar(article.getStar() + (count > 0 ? -1 : 1));
        }
        this.updateArticle(article);

        if (count > 0) {
            this.deleteArticleUser(id, user.getId(), type);
        } else {
            this.saveArticleUser(id, user.getId(), type);
        }
        return count == 0;
    }

    /**
     * 删除顶踩记录
     *
     * @param articleId
     * @param userId
     * @param type
     */
    private void deleteArticleUser(Long articleId, Long userId, String type) {
        articleMapper.deleteArticleUser(articleId, userId, type);
    }

    /**
     * 保存顶踩记录
     *
     * @param articleId
     * @param userId
     * @param type
     */
    private void saveArticleUser(Long articleId, Long userId, String type) {
        articleMapper.insertArticleUser(articleId, userId, type);
    }
}
