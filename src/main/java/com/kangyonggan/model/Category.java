package com.kangyonggan.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
public class Category {
    /**
     * 主键, 自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 栏目代码
     */
    private String code;

    /**
     * 栏目名称
     */
    private String name;

    /**
     * 父栏目ID
     */
    private Long pid;

    /**
     * 栏目排序(从1开始)
     */
    private Integer sort;

    /**
     * 菜单图标的样式
     */
    private String icon;

    /**
     * 是否异步 {0:不异步, 1:异步}
     */
    @Column(name = "is_ajax")
    private Byte isAjax;

    /**
     * 是否需要审核 {0:不需要, 1:需要}
     */
    @Column(name = "is_need_approval")
    private Byte isNeedApproval;

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

    @Transient
    private List<Category> childrens;
}