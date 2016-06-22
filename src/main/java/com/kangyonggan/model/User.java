package com.kangyonggan.model;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
public class User {
    /**
     * 主键, 自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 是否校验 {0:未校验, 1:已校验}
     */
    @Column(name = "is_verified")
    private Byte isVerified;

    /**
     * 密码
     */
    private String password;

    /**
     * 密码盐
     */
    private String salt;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 小头像
     */
    @Column(name = "small_avatar")
    private String smallAvatar;

    /**
     * 中头像
     */
    @Column(name = "medium_avatar")
    private String mediumAvatar;

    /**
     * 大头像
     */
    @Column(name = "large_avatar")
    private String largeAvatar;

    /**
     * 签名
     */
    private String sign;

    /**
     * 是否锁定 {0:未锁定, 1:已锁定}
     */
    @Column(name = "is_locked")
    private Byte isLocked;

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
     * 最近登录时间
     */
    @Column(name = "login_time")
    private Date loginTime;
}