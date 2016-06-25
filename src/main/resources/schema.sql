CREATE TABLE user
(
  id            BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  email         VARCHAR(128)                          NOT NULL
  COMMENT '电子邮箱',
  is_verified   TINYINT                               NOT NULL         DEFAULT 0
  COMMENT '邮箱是否校验 {0:未校验, 1:已校验}',
  password      VARCHAR(128)                          NOT NULL
  COMMENT '密码',
  salt          VARCHAR(128)                          NOT NULL
  COMMENT '密码盐',
  realname      VARCHAR(64)                           NOT NULL         DEFAULT ''
  COMMENT '真实姓名',
  mobile        VARCHAR(20)                           NOT NULL         DEFAULT ''
  COMMENT '手机号',
  small_avatar  VARCHAR(255)                          NOT NULL         DEFAULT ''
  COMMENT '小头像',
  medium_avatar VARCHAR(255)                          NOT NULL         DEFAULT ''
  COMMENT '中头像',
  large_avatar  VARCHAR(255)                          NOT NULL         DEFAULT ''
  COMMENT '大头像',
  sign          VARCHAR(255)                          NOT NULL         DEFAULT ''
  COMMENT '个性签名',
  is_locked     TINYINT                               NOT NULL         DEFAULT 0
  COMMENT '是否锁定 {0:未锁定, 1:已锁定}',
  is_deleted    TINYINT                               NOT NULL         DEFAULT 0
  COMMENT '是否删除 {0:未删除, 1:已删除}',
  created_time  DATETIME                              NOT NULL
  COMMENT '创建时间',
  updated_time  DATETIME                              NOT NULL
  COMMENT '最后更新时间',
  login_time    DATETIME                              NOT NULL
  COMMENT '最近登录时间'
)
  COMMENT '用户表';
CREATE UNIQUE INDEX id_UNIQUE ON user (id);
CREATE UNIQUE INDEX email_UNIQUE ON user (email);

INSERT INTO user (id, email, is_verified, password, salt, realname, mobile, created_time, updated_time, login_time)
VALUES
  (1, 'kangyonggan@gmail.com', 1, '341047a77b46b72db791287a0d3beeb9c771bfdf', '3d30f9ce25f02d0a', '康永敢', '18221372104',
   '2016-06-21 13:40:15', '2016-06-21 18:40:17',
   '2016-06-21 13:40:17'),
  (2, 'kangyonggan@qq.com', 1, '341047a77b46b72db791287a0d3beeb9c771bfdf', '3d30f9ce25f02d0a', '黄芳玲', '15151679072',
   '2016-06-21 17:12:16', '2016-06-21 17:12:23', '2016-06-21 17:12:26'),
  (3, 'houbinbin@qq.com', 1, '341047a77b46b72db791287a0d3beeb9c771bfdf', '3d30f9ce25f02d0a', '侯宾宾', '',
   '2016-06-21 17:12:16', '2016-06-21 17:12:23', '2016-06-21 17:11:26'),
  (4, 'liujinfu@qq.com', 1, '341047a77b46b72db791287a0d3beeb9c771bfdf', '3d30f9ce25f02d0a', '刘晋甫', '',
   '2016-06-21 17:12:16', '2016-06-21 17:12:23', '2016-06-21 17:10:26');

CREATE TABLE role
(
  id           BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  code         VARCHAR(64)                           NOT NULL
  COMMENT '角色代码',
  name         VARCHAR(128)                          NOT NULL
  COMMENT '角色名称',
  is_deleted   TINYINT                               NOT NULL         DEFAULT 0
  COMMENT '是否删除 {0:未删除, 1:已删除}',
  created_time DATETIME                              NOT NULL
  COMMENT '创建时间',
  updated_time DATETIME                              NOT NULL
  COMMENT '最后更新时间'
)
  COMMENT '角色表';
CREATE UNIQUE INDEX id_UNIQUE ON role (id);
CREATE UNIQUE INDEX code_UNIQUE ON role (code);

INSERT INTO role (id, code, name, created_time, updated_time)
VALUES (1, 'ROLE_ADMIN', '管理员', '2016-06-21 13:37:50', '2016-06-21 13:37:52'),
  (2, 'ROLE_USER', '普通用户', '2016-06-21 13:37:50', '2016-06-21 13:37:52');

CREATE TABLE menu
(
  id           BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  code         VARCHAR(64)                           NOT NULL
  COMMENT '菜单代码',
  name         VARCHAR(128)                          NOT NULL         DEFAULT ''
  COMMENT '菜单名称',
  pid          BIGINT(20)                            NOT NULL
  COMMENT '父菜单ID',
  url          VARCHAR(128)                          NOT NULL
  COMMENT '菜单URL',
  sort         INT(11)                               NOT NULL
  COMMENT '菜单排序(从1开始)',
  icon         VARCHAR(128)                          NOT NULL         DEFAULT ''
  COMMENT '菜单图标的样式',
  is_deleted   TINYINT                               NOT NULL         DEFAULT 0
  COMMENT '是否删除 {0:未删除, 1:已删除}',
  created_time DATETIME                              NOT NULL
  COMMENT '创建时间',
  updated_time DATETIME                              NOT NULL
  COMMENT '最后更新时间'
)
  COMMENT '菜单表';
CREATE UNIQUE INDEX id_UNIQUE ON menu (id);
CREATE UNIQUE INDEX code_UNIQUE ON menu (code);

INSERT INTO menu (id, code, name, pid, url, sort, icon, created_time, updated_time)
VALUES (1, 'root', '根菜单', '0', 'root', 1, '', '2016-06-21 15:14:35', '2016-06-21 15:14:38'),
  (2, 'sys', '系统', '1', 'sys/manage', 1, 'ace-icon fa fa-cog bigger-140', '2016-06-21 15:14:35',
   '2016-06-21 15:14:38'),
  (21, 'sys-user', '用户管理', '2', 'sys/user', 1, 'ace-icon fa fa-users bigger-120', '2016-06-21 15:14:35',
   '2016-06-21 15:14:38'),
  (22, 'sys-role', '角色管理', '2', 'sys/role', 2, 'ace-icon fa fa-hdd-o bigger-120', '2016-06-21 15:14:35',
   '2016-06-21 15:14:38'),
  (23, 'sys-menu', '菜单管理', '2', 'sys/menu', 3, 'ace-icon fa fa-tachometer bigger-120', '2016-06-21 15:14:35',
   '2016-06-21 15:14:38'),
  (24, 'sys-category', '栏目管理', '2', 'sys/category', 4, 'ace-icon fa fa-th bigger-120', '2016-06-21 15:14:35',
   '2016-06-21 15:14:38');

CREATE TABLE user_role
(
  user_id BIGINT(20) NOT NULL
  COMMENT '用户ID',
  role_id BIGINT(20) NOT NULL
  COMMENT '角色ID',
  CONSTRAINT `PRIMARY` PRIMARY KEY (user_id, role_id)
);

INSERT INTO user_role (user_id, role_id) VALUES (1, 1);

CREATE TABLE role_menu
(
  role_id BIGINT(20) NOT NULL
  COMMENT '角色ID',
  menu_id BIGINT(20) NOT NULL
  COMMENT '菜单ID',
  CONSTRAINT `PRIMARY` PRIMARY KEY (role_id, menu_id)
);

INSERT INTO role_menu (role_id, menu_id) SELECT
                                           1,
                                           id
                                         FROM menu;

CREATE TABLE category
(
  id               BIGINT(20) PRIMARY KEY  AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  code             VARCHAR(64)                            NOT NULL
  COMMENT '栏目代码',
  name             VARCHAR(128)                           NOT NULL
  COMMENT '栏目名称',
  pid              BIGINT(20)                             NOT NULL
  COMMENT '父栏目ID',
  sort             INT(11)                                NOT NULL
  COMMENT '栏目排序(从1开始)',
  icon             VARCHAR(128)                           NOT NULL         DEFAULT ''
  COMMENT '菜单图标的样式',
  is_need_approval TINYINT                                NOT NULL         DEFAULT 0
  COMMENT '是否需要审核 {0:不需要, 1:需要}',
  is_deleted       TINYINT                                NOT NULL         DEFAULT 0
  COMMENT '是否删除 {0:未删除, 1:已删除}',
  created_time     DATETIME                               NOT NULL
  COMMENT '创建时间',
  updated_time     DATETIME                               NOT NULL
  COMMENT '最后更新时间'
)
  COMMENT '栏目表';
CREATE UNIQUE INDEX id_UNIQUE ON category (id);
CREATE UNIQUE INDEX code_UNIQUE ON category (code);

INSERT INTO category (id, code, name, pid, sort, icon, is_need_approval, is_deleted, created_time, updated_time)
VALUES (1, 'root', '根栏目', 0, 1, 'ace-icon fa fa-leaf bigger-120', 0, 0, '2016-06-24 21:12:51', '2016-06-24 21:12:53'),
  (2, 'note', '随笔', 1, 1, 'ace-icon fa fa-pencil bigger-120', 0, 0, '2016-06-24 21:12:51', '2016-06-24 21:12:53'),
  (20, 'note-word', '每日一句', 2, 1, 'ace-icon fa fa-pencil-square-o bigger-120', 0, 0, '2016-06-24 21:12:51', '2016-06-24 21:12:53'),
  (21, 'note-diary', '每周一记', 2, 2, 'ace-icon fa fa-book bigger-120', 0, 0, '2016-06-24 21:12:51', '2016-06-24 21:12:53'),
  (22, 'note-message', '我的留言', 2, 3, 'ace-icon fa fa-comment bigger-120', 0, 0, '2016-06-24 21:12:51', '2016-06-24 21:12:53'),

  (3, 'enjoy', '娱乐', 1, 2, 'ace-icon fa fa-film bigger-120', 0, 0, '2016-06-24 21:12:51', '2016-06-24 21:12:53'),
  (30, 'enjoy-picture', '花样相册', 3, 1, 'ace-icon fa fa-picture-o bigger-120', 0, 0, '2016-06-24 21:12:51', '2016-06-24 21:12:53'),
  (31, 'enjoy-music', '音乐盛会', 3, 2, 'ace-icon fa fa-music bigger-120', 0, 0, '2016-06-24 21:12:51', '2016-06-24 21:12:53'),
  (32, 'enjoy-video', '视频专区', 3, 3, 'ace-icon fa fa-video-camera bigger-120', 0, 0, '2016-06-24 21:12:51', '2016-06-24 21:12:53'),

  (4, 'res', '资源', 1, 3, 'ace-icon fa fa-cloud bigger-120', 0, 0, '2016-06-24 21:12:51', '2016-06-24 21:12:53'),
  (40, 'res-download', '资源下载', 4, 1, 'ace-icon fa fa-download bigger-120', 0, 0, '2016-06-24 21:12:51', '2016-06-24 21:12:53'),
  (41, 'res-exchange', '学术交流', 4, 2, 'ace-icon fa fa-comments bigger-120', 0, 0, '2016-06-24 21:12:51',
   '2016-06-24 21:12:53'),
  (42, 'res-share', '经验分享', 4, 3, 'ace-icon fa fa-external-link bigger-120', 0, 0, '2016-06-24 21:12:51',
   '2016-06-24 21:12:53'),
  (43, 'res-note', '学习笔记', 4, 4, 'ace-icon fa fa-pencil bigger-120', 0, 0, '2016-06-24 21:12:51',
   '2016-06-24 21:12:53'),
  (44, 'res-course', '图文教程', 4, 5, 'ace-icon fa fa-lightbulb-o bigger-120', 0, 0, '2016-06-24 21:12:51',
   '2016-06-24 21:12:53');


CREATE TABLE article
(
  id              BIGINT(20) PRIMARY KEY  AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  title           VARCHAR(255)                           NOT NULL         DEFAULT ''
  COMMENT '文章标题',
  summary         VARCHAR(500)                           NOT NULL         DEFAULT ''
  COMMENT '文章摘要',
  body            LONGTEXT                               NOT NULL
  COMMENT '文章内容',
  tags            VARCHAR(500)                           NOT NULL         DEFAULT ''
  COMMENT '标签',
  tid             BIGINT(20)                             NOT NULL         DEFAULT 0
  COMMENT '被转发文章的ID',
  visitors        VARCHAR(500)                           NOT NULL         DEFAULT '0'
  COMMENT '可访问者',
  top             INT(11)                                NOT NULL         DEFAULT 0
  COMMENT '顶',
  low             INT(11)                                NOT NULL         DEFAULT 0
  COMMENT '踩',
  stick           TINYINT                                NOT NULL         DEFAULT 0
  COMMENT '是否置顶 {0:未置顶, 1:已置顶}',
  stick_time      DATETIME                               NOT NULL         DEFAULT '1900-01-01 00:00:00'
  COMMENT '置顶时间',
  created_user_id BIGINT(20)                             NOT NULL         DEFAULT 0
  COMMENT '发表人ID',
  is_deleted      TINYINT                                NOT NULL         DEFAULT 0
  COMMENT '是否删除 {0:未删除, 1:已删除}',
  created_time    DATETIME                               NOT NULL
  COMMENT '创建时间',
  updated_time    DATETIME                               NOT NULL
  COMMENT '最后更新时间'
)
  COMMENT '文章表';
CREATE UNIQUE INDEX id_UNIQUE ON article (id);

CREATE TABLE attachment
(
  id           BIGINT(20) PRIMARY KEY  AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  article_id   BIGINT(20)                             NOT NULL
  COMMENT '附件所属的文章ID',
  path         VARCHAR(500)                           NOT NULL
  COMMENT '附件路径',
  is_deleted   TINYINT                                NOT NULL         DEFAULT 0
  COMMENT '是否删除 {0:未删除, 1:已删除}',
  created_time DATETIME                               NOT NULL
  COMMENT '创建时间',
  updated_time DATETIME                               NOT NULL
  COMMENT '最后更新时间'
)
  COMMENT '附件表';
CREATE UNIQUE INDEX id_UNIQUE ON attachment (id);
