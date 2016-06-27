package com.kangyonggan.model;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
public class Reply {
    /**
     * 主键, 自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 被评论文章的ID
     */
    @Column(name = "article_id")
    private Long articleId;

    /**
     * 顶
     */
    private Integer top;

    /**
     * 踩
     */
    private Integer low;

    /**
     * 评论人ID
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
     * 评论内容
     */
    private String content;

}