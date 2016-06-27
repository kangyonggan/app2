package com.kangyonggan.mapper;

import com.kangyonggan.model.Article;
import com.kangyonggan.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMapper extends MyMapper<Article> {

    List<Article> selectArticlesByCategoryCode(@Param("categoryCode") String categoryCode);

    List<Article> selectStarArticles(@Param("userId") Long userId);

    Article selectArticleById(Long id);

    int selectArticleUser(@Param("articleId") Long articleId, @Param("userId") Long userId, @Param("type") String type);

    void insertArticleUser(@Param("articleId") Long articleId, @Param("userId") Long userId, @Param("type") String type);

    void deleteArticleUser(@Param("articleId") Long articleId, @Param("userId") Long userId, @Param("type") String type);
}