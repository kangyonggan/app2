package com.kangyonggan.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
public class Article {
    /**
     * 主键, 自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章摘要
     */
    private String summary;

    /**
     * 标签
     */
    private String tags;

    /**
     * 栏目代码
     */
    @Column(name = "category_code")
    private String categoryCode;

    /**
     * 栏目名称
     */
    @Column(name = "category_name")
    private String categoryName;

    /**
     * 可访问者
     */
    private String visitors;

    /**
     * 顶
     */
    private Integer top;

    /**
     * 踩
     */
    private Integer low;

    /**
     * 收藏
     */
    private Integer star;

    /**
     * 评论
     */
    private Integer reply;

    /**
     * 是否置顶 {0:未置顶, 1:已置顶}
     */
    private Byte sticky;

    /**
     * 置顶时间
     */
    @Column(name = "sticky_time")
    private Date stickyTime;

    /**
     * 发表人ID
     */
    @Column(name = "user_id")
    private Long userId;

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
     * 文章内容
     */
    private String body;

    @Transient
    private String realname;

    @Transient
    private String sign;

    @Transient
    private String smallAvatar;
}