package com.kangyonggan.mapper;

import com.kangyonggan.model.Article;
import com.kangyonggan.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMapper extends MyMapper<Article> {

    List<Article> selectArticlesByCategoryCode(@Param("categoryCode") String categoryCode);

}