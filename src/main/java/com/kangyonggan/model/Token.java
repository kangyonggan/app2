package com.kangyonggan.model;

import java.util.Date;
import javax.persistence.*;

import lombok.Data;

@Data
public class Token {
    /**
     * 主键, 自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 记号代码
     */
    private String code;

    /**
     * 记号类型
     */
    private String type;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 失效时间
     */
    @Column(name = "expire_time")
    private Date expireTime;

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
}