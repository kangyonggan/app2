package com.kangyonggan.service;

import com.kangyonggan.model.Article;
import com.kangyonggan.model.Attachment;

import java.util.List;

/**
 * @author kangyonggan
 * @since 16/6/25
 */
public interface ArticleService {

    List<Article> searchAllArticles(int pageNum, int pageSize, String categoryCode, String title, String startTime, String endTime) throws Exception;

    List<Article> searchArticles(int pageNum, int pageSize, String code, String title, String startTime, String endTime) throws Exception;

    List<Article> findArticesByCategoryCodeAndUserId(int pageNum, int pageSize, String categoryCode, Long userId);

    List<Article> findStarArticles(int pageNum, int pageSize);

    List<Article> findBellArticles(int pageNum, int pageSize);

    List<Article> findArticesByKey(int pageNum, int pageSize, String key);

    Article getArticle(Long id);

    Article findArticleById(Long id);

    Article findTotalArticleByUserId(Long userId);

    Article findTotalArticleByUserIdWithGroup(Long userId, String group);

    boolean updateArticleActions(Long id, String action);

    void saveArticle(Article article);

    void saveArticle(Article article, List<Attachment> files);

    void saveArticle(Article article, String ids);

    void deleteArticle(Long id);

    void updateArticle(Article article);
}
