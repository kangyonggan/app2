package com.kangyonggan.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table(name = "article_index")
@Data
public class ArticleIndex {
    /**
     * 主键, 和文章表的主键一致
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 是否删除 {0:未删除, 1:已删除}
     */
    @Column(name = "is_deleted")
    private Byte isDeleted;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 最后更新时间
     */
    @Column(name = "updated_time")
    private Date updatedTime;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章摘要
     */
    private String summary;

    /**
     * 文章内容
     */
    private String body;

    /**
     * 栏目名称
     */
    @Column(name = "category_name")
    private String categoryName;
}