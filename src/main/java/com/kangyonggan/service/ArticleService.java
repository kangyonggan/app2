package com.kangyonggan.service;

import com.kangyonggan.model.Article;

import java.util.List;

/**
 * @author kangyonggan
 * @since 16/6/25
 */
public interface ArticleService {

    List<Article> searchArticles(int pageNum, int pageSize, String code, String title, String startTime, String endTime) throws Exception;

    List<Article> findArticesByCategoryCode(int pageNum, int pageSize, String categoryCode);

    List<Article> findStarArticles(int pageNum, int pageSize);

    List<Article> findBellArticles(int pageNum, int pageSize);

    Article getArticle(Long id);

    Article findArticleById(Long id);

    void saveArticle(Article article);

    void saveArticle(Article article, List<String> filenames);

    void deleteArticleWithLogic(Article article);

    void updateArticle(Article article);

    boolean updateArticleActions(Long id, String action);
}
