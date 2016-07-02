package com.kangyonggan.mapper;

import com.kangyonggan.model.Article;
import com.kangyonggan.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ArticleMapper extends MyMapper<Article> {

    List<Article> selectArticlesByCategoryCode(@Param("categoryCode") String categoryCode);

    List<Article> selectStarArticles(Long userId);

    List<Article> selectArticlesInIds(Map<String, Object> param);

    Article selectArticleById(Long id);

    Article selectTotalArticleByUserId(Long userId);

    Article selectTotalArticleByUserIdWithGroup(@Param("userId") Long userId, @Param("group") String group);

    int selectArticleUser(@Param("articleId") Long articleId, @Param("userId") Long userId, @Param("type") String type);

    void insertArticleUser(@Param("articleId") Long articleId, @Param("userId") Long userId, @Param("type") String type);

    void deleteArticleUser(@Param("articleId") Long articleId, @Param("userId") Long userId, @Param("type") String type);
}