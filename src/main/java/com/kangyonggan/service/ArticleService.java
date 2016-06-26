package com.kangyonggan.service;

import com.kangyonggan.model.Article;

import java.util.List;

/**
 * @author kangyonggan
 * @since 16/6/25
 */
public interface ArticleService {

    /**
     * 按条件查找用户自己的文章
     *
     * @param pageNum
     * @param pageSize
     * @param code
     * @param userId
     * @param title
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    List<Article> searchArticles(int pageNum, int pageSize, String code, Long userId, String title, String startTime, String endTime) throws Exception;

    List<Article> findArticesByCategoryCode(int pageNum, int pageSize, String categoryCode);

    Article getArticle(Long id);

    void saveArticle(Article article);

    void deleteArticleWithLogic(Article article);

    void updateArticle(Article article);

    void deleteArticle(Long id);
}
