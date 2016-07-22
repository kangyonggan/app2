CREATE DATABASE app
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_general_ci;

USE app;

CREATE TABLE user
(
  id                   BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  email                VARCHAR(128)                          NOT NULL
  COMMENT '电子邮箱',
  is_verified          TINYINT                               NOT NULL         DEFAULT 0
  COMMENT '邮箱是否校验 {0:未校验, 1:已校验}',
  password             VARCHAR(128)                          NOT NULL
  COMMENT '密码',
  salt                 VARCHAR(128)                          NOT NULL
  COMMENT '密码盐',
  error_password_count INT(11)                               NOT NULL         DEFAULT 0
  COMMENT '密码错误次数',
  error_password_time  DATETIME                              NULL
  COMMENT '最后密码错误时间',
  realname             VARCHAR(64)                           NOT NULL         DEFAULT ''
  COMMENT '真实姓名',
  mobile               VARCHAR(20)                           NOT NULL         DEFAULT ''
  COMMENT '手机号',
  small_avatar         VARCHAR(255)                          NOT NULL         DEFAULT ''
  COMMENT '小头像',
  medium_avatar        VARCHAR(255)                          NOT NULL         DEFAULT ''
  COMMENT '中头像',
  large_avatar         VARCHAR(255)                          NOT NULL         DEFAULT ''
  COMMENT '大头像',
  sign                 VARCHAR(255)                          NOT NULL         DEFAULT ''
  COMMENT '个性签名',
  is_locked            TINYINT                               NOT NULL         DEFAULT 0
  COMMENT '是否锁定 {0:未锁定, 1:已锁定}',
  is_deleted           TINYINT                               NOT NULL         DEFAULT 0
  COMMENT '是否删除 {0:未删除, 1:已删除}',
  created_time         DATETIME                              NOT NULL
  COMMENT '创建时间',
  updated_time         DATETIME                              NOT NULL
  COMMENT '最后更新时间',
  login_time           DATETIME                              NOT NULL
  COMMENT '最近登录时间'
)
  COMMENT '用户表';
CREATE UNIQUE INDEX id_UNIQUE ON user (id);
CREATE UNIQUE INDEX email_UNIQUE ON user (email);

INSERT INTO user (id, email, is_verified, password, salt, error_password_time, realname, mobile, created_time, updated_time, login_time)
VALUES
  (1, 'kangyonggan@gmail.com', 1, '341047a77b46b72db791287a0d3beeb9c771bfdf', '3d30f9ce25f02d0a', '2016-06-21 18:40:17',
      '康永敢', '18221372104',
      '2016-06-21 13:40:15', '2016-06-21 18:40:17',
      '2016-06-21 13:40:17'),
  (2, 'kangyonggan@qq.com', 1, '341047a77b46b72db791287a0d3beeb9c771bfdf', '3d30f9ce25f02d0a', '2016-06-21 18:40:17',
      '黄芳玲', '15151679072',
      '2016-06-21 17:12:16', '2016-06-21 17:12:23', '2016-06-21 17:12:26'),
  (3, 'houbinbin@qq.com', 1, '341047a77b46b72db791287a0d3beeb9c771bfdf', '3d30f9ce25f02d0a', '2016-06-21 18:40:17',
      '侯宾宾', '',
      '2016-06-21 17:12:16', '2016-06-21 17:12:23', '2016-06-21 17:11:26'),
  (4, 'liujinfu@qq.com', 1, '341047a77b46b72db791287a0d3beeb9c771bfdf', '3d30f9ce25f02d0a', '2016-06-21 18:40:17',
      '刘晋甫', '',
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
  sort         INT(11)                               NOT NULL         DEFAULT 1
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
  (2, 'sys', '系统', 1, 'sys/manage', 1, 'ace-icon fa fa-cog', '2016-06-21 15:14:35',
   '2016-06-21 15:14:38'),
  (20, 'sys-user', '用户管理', 2, 'sys/user/list', 1, 'ace-icon fa fa-users', '2016-06-21 15:14:35',
   '2016-06-21 15:14:38'),
  (21, 'sys-role', '角色管理', 2, 'sys/role/list', 2, 'ace-icon fa fa-hdd-o', '2016-06-21 15:14:35',
   '2016-06-21 15:14:38'),
  (22, 'sys-menu', '菜单管理', 2, 'sys/menu/list', 3, 'ace-icon fa fa-tachometer', '2016-06-21 15:14:35',
   '2016-06-21 15:14:38'),

  (3, 'pits', '维护', 1, 'pits/manage', 2, 'ace-icon fa fa-wrench', '2016-06-21 15:14:35', '2016-06-21 15:14:38'),
  (30, 'pits-page', '页面管理', 3, 'pits/page/list', 1, 'ace-icon fa fa-file', '2016-06-21 15:14:35',
   '2016-06-21 15:14:38'),
  (31, 'pits-user-edit', '资料修改', 3, 'pits/user/edit', 2, 'ace-icon fa fa-exclamation-circle', '2016-06-21 15:14:35',
   '2016-06-21 15:14:38'),
  (32, 'pits-user-password', '更新密码', 3, 'pits/user/password', 3, 'ace-icon fa fa-key', '2016-06-21 15:14:35',
   '2016-06-21 15:14:38'),
  (33, 'pits-category', '栏目管理', 3, 'pits/category/list', 4, 'ace-icon fa fa-th', '2016-06-21 15:14:35',
   '2016-06-21 15:14:38'),
  (34, 'pits-article', '文章管理', 3, 'pits/article/list', 5, 'ace-icon fa fa-desktop', '2016-06-21 15:14:35',
   '2016-06-21 15:14:38');

CREATE TABLE user_role
(
  user_id BIGINT(20) NOT NULL
  COMMENT '用户ID',
  role_id BIGINT(20) NOT NULL
  COMMENT '角色ID',
  CONSTRAINT `PRIMARY` PRIMARY KEY (user_id, role_id)
)
  COMMENT '用户角色中间表';

INSERT INTO user_role (user_id, role_id) VALUES (1, 1), (2, 2), (3, 2), (4, 2);

CREATE TABLE role_menu
(
  role_id BIGINT(20) NOT NULL
  COMMENT '角色ID',
  menu_id BIGINT(20) NOT NULL
  COMMENT '菜单ID',
  CONSTRAINT `PRIMARY` PRIMARY KEY (role_id, menu_id)
)
  COMMENT '角色菜单中间表';

INSERT INTO role_menu (role_id, menu_id) SELECT
                                           1,
                                           id
                                         FROM menu;

INSERT INTO role_menu (role_id, menu_id)
VALUES
  (2, 1),
  (2, 3),
  (2, 30),
  (2, 31),
  (2, 32);

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
  sort             INT(11)                                NOT NULL         DEFAULT 1
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
VALUES (1, 'root', '根栏目', 0, 1, 'ace-icon fa fa-leaf', 0, 0, '2016-06-24 21:12:51', '2016-06-24 21:12:53'),
  (10, 'star', '我收藏的', 1, 2, 'ace-icon fa fa-star-o', 0, 0, '2016-06-24 21:12:51',
   '2016-06-24 21:12:53'),
  (11, 'bell', '我参与的', 1, 3, 'ace-icon fa fa-bell', 0, 0, '2016-06-24 21:12:51',
   '2016-06-24 21:12:53'),

  (13, 'pancil', '随笔', 1, 4, 'ace-icon fa fa-pencil', 0, 0, '2016-06-24 21:12:51', '2016-06-24 21:12:53'),
  (130, 'word', '每日一句', 13, 1, 'ace-icon fa fa-pencil-square-o', 0, 0, '2016-06-24 21:12:51', '2016-06-24 21:12:53'),
  (131, 'diary', '每周一记', 13, 2, 'ace-icon fa fa-book', 0, 0, '2016-06-24 21:12:51', '2016-06-24 21:12:53'),

  (14, 'enjoy', '娱乐', 1, 5, 'ace-icon fa fa-film', 0, 0, '2016-06-24 21:12:51', '2016-06-24 21:12:53'),
  (140, 'picture', '花样相册', 14, 1, 'ace-icon fa fa-picture-o', 0, 0, '2016-06-24 21:12:51', '2016-06-24 21:12:53'),
  (141, 'music', '音乐盛会', 14, 2, 'ace-icon fa fa-music', 0, 0, '2016-06-24 21:12:51', '2016-06-24 21:12:53'),
  (142, 'video', '视频专区', 14, 3, 'ace-icon fa fa-video-camera', 0, 0, '2016-06-24 21:12:51', '2016-06-24 21:12:53'),

  (15, 'res', '资源', 1, 6, 'ace-icon fa fa-cloud', 0, 0, '2016-06-24 21:12:51', '2016-06-24 21:12:53'),
  (150, 'download', '资源下载', 15, 1, 'ace-icon fa fa-download', 0, 0, '2016-06-24 21:12:51', '2016-06-24 21:12:53'),
  (151, 'exchange', '学术交流', 15, 2, 'ace-icon fa fa-comments', 0, 0, '2016-06-24 21:12:51',
   '2016-06-24 21:12:53'),
  (152, 'share', '经验分享', 15, 3, 'ace-icon fa fa-external-link', 0, 0, '2016-06-24 21:12:51',
   '2016-06-24 21:12:53'),
  (153, 'note', '学习笔记', 15, 4, 'ace-icon fa fa-pencil', 0, 0, '2016-06-24 21:12:51',
   '2016-06-24 21:12:53'),
  (154, 'course', '图文教程', 15, 5, 'ace-icon fa fa-lightbulb-o', 0, 0, '2016-06-24 21:12:51',
   '2016-06-24 21:12:53');


CREATE TABLE article
(
  id            BIGINT(20) PRIMARY KEY  AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  title         VARCHAR(500)                           NOT NULL         DEFAULT ''
  COMMENT '文章标题',
  summary       VARCHAR(500)                           NOT NULL         DEFAULT ''
  COMMENT '文章摘要',
  body          LONGTEXT                               NOT NULL
  COMMENT '文章内容',
  tags          VARCHAR(500)                           NOT NULL         DEFAULT ''
  COMMENT '标签',
  category_code VARCHAR(64)                            NOT NULL
  COMMENT '栏目代码',
  category_name VARCHAR(128)                           NOT NULL
  COMMENT '栏目名称',
  visitors      VARCHAR(500)                           NOT NULL         DEFAULT '0'
  COMMENT '可访问者',
  top           INT(11)                                NOT NULL         DEFAULT 0
  COMMENT '顶',
  low           INT(11)                                NOT NULL         DEFAULT 0
  COMMENT '踩',
  star          INT(11)                                NOT NULL         DEFAULT 0
  COMMENT '收藏',
  reply         INT(11)                                NOT NULL         DEFAULT 0
  COMMENT '评论',
  sticky        TINYINT                                NOT NULL         DEFAULT 0
  COMMENT '是否置顶 {0:未置顶, 1:已置顶}',
  sticky_time   DATETIME                               NOT NULL         DEFAULT '1900-01-01 00:00:00'
  COMMENT '置顶时间',
  user_id       BIGINT(20)                             NOT NULL         DEFAULT 0
  COMMENT '发表人ID',
  is_deleted    TINYINT                                NOT NULL         DEFAULT 0
  COMMENT '是否删除 {0:未删除, 1:已删除}',
  created_time  DATETIME                               NOT NULL
  COMMENT '创建时间',
  updated_time  DATETIME                               NOT NULL
  COMMENT '最后更新时间'
)
  COMMENT '文章表';
CREATE UNIQUE INDEX id_UNIQUE ON article (id);

CREATE TABLE article_user
(
  article_id BIGINT(20)  NOT NULL
  COMMENT '文章ID',
  user_id    BIGINT(20)  NOT NULL
  COMMENT '用户ID',
  type       VARCHAR(32) NOT NULL
  COMMENT '顶/踩 {top:顶, low:踩, star:收藏}',
  CONSTRAINT `PRIMARY` PRIMARY KEY (article_id, user_id, type)
)
  COMMENT '文章顶踩收藏表';

CREATE TABLE reply
(
  id           BIGINT(20) PRIMARY KEY  AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  article_id   BIGINT(20)                             NOT NULL
  COMMENT '被评论文章的ID',
  content      LONGTEXT                               NOT NULL
  COMMENT '评论内容',
  top          INT(11)                                NOT NULL         DEFAULT 0
  COMMENT '顶',
  low          INT(11)                                NOT NULL         DEFAULT 0
  COMMENT '踩',
  user_id      BIGINT(20)                             NOT NULL         DEFAULT 0
  COMMENT '评论人ID',
  is_deleted   TINYINT                                NOT NULL         DEFAULT 0
  COMMENT '是否删除 {0:未删除, 1:已删除}',
  created_time DATETIME                               NOT NULL
  COMMENT '创建时间',
  updated_time DATETIME                               NOT NULL
  COMMENT '最后更新时间'
)
  COMMENT '评论表';
CREATE UNIQUE INDEX id_UNIQUE ON reply (id);

CREATE TABLE attachment
(
  id           BIGINT(20) PRIMARY KEY  AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  article_id   BIGINT(20)                             NOT NULL         DEFAULT 0
  COMMENT '附件所属的文章ID',
  name         VARCHAR(500)                           NOT NULL         DEFAULT ''
  COMMENT '附件名称',
  path         VARCHAR(500)                           NOT NULL         DEFAULT ''
  COMMENT '附件路径',
  type         VARCHAR(32)                            NOT NULL         DEFAULT ''
  COMMENT '附件类型',
  user_id      BIGINT(20)                             NOT NULL         DEFAULT 0
  COMMENT '上传人ID',
  is_deleted   TINYINT                                NOT NULL         DEFAULT 0
  COMMENT '是否删除 {0:未删除, 1:已删除}',
  created_time DATETIME                               NOT NULL
  COMMENT '创建时间',
  updated_time DATETIME                               NOT NULL
  COMMENT '最后更新时间'
)
  COMMENT '附件表';
CREATE UNIQUE INDEX id_UNIQUE ON attachment (id);

CREATE TABLE token
(
  id           BIGINT(20) PRIMARY KEY  AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  code         VARCHAR(128)                           NOT NULL
  COMMENT '记号代码',
  type         VARCHAR(500)                           NOT NULL         DEFAULT ''
  COMMENT '记号类型',
  user_id      BIGINT(20)                             NOT NULL         DEFAULT 0
  COMMENT '用户ID',
  expire_time  DATETIME                               NOT NULL
  COMMENT '失效时间',
  is_deleted   TINYINT                                NOT NULL         DEFAULT 0
  COMMENT '是否删除 {0:未删除, 1:已删除}',
  created_time DATETIME                               NOT NULL
  COMMENT '创建时间',
  updated_time DATETIME                               NOT NULL
  COMMENT '最后更新时间'
)
  COMMENT '记号表';
CREATE UNIQUE INDEX id_UNIQUE ON token (id);
CREATE UNIQUE INDEX code_UNIQUE ON token (code);

CREATE TABLE page
(
  id           BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  name         VARCHAR(128)                          NOT NULL
  COMMENT '页面名称',
  url          VARCHAR(128)                          NOT NULL
  COMMENT '页面URL',
  sort         INT(11)                               NOT NULL         DEFAULT 1
  COMMENT '页面排序(从1开始)',
  icon         VARCHAR(128)                          NOT NULL         DEFAULT 'ace-icon fa fa-bookmark-o'
  COMMENT '页面图标的样式',
  type         VARCHAR(500)                          NOT NULL         DEFAULT 'nav'
  COMMENT '页面类型',
  user_id      BIGINT(20)                            NOT NULL         DEFAULT 0
  COMMENT '用户ID',
  is_deleted   TINYINT                               NOT NULL         DEFAULT 0
  COMMENT '是否删除 {0:未删除, 1:已删除}',
  created_time DATETIME                              NOT NULL
  COMMENT '创建时间',
  updated_time DATETIME                              NOT NULL
  COMMENT '最后更新时间'
)
  COMMENT '页面表';
CREATE UNIQUE INDEX id_UNIQUE ON page (id);

INSERT INTO page (name, url, sort, icon, type, user_id, created_time, updated_time) VALUES
  ('项目托管', 'https://github.com', 1, 'ace-icon fa fa-bookmark', 'nav', 0, '2016-07-03 14:50:07', '2016-07-03 14:50:10'),
  ('菜鸟教程', 'http://www.w3school.com.cn', 2, 'ace-icon fa fa-bookmark', 'nav', 0, '2016-07-03 14:50:07', '2016-07-03 14:50:10'),
  ('Bootstrap中文网', 'http://v3.bootcss.com', 3, 'ace-icon fa fa-bookmark', 'nav', 0, '2016-07-03 14:50:07', '2016-07-03 14:50:10'),
  ('小说', 'http://top.qidian.com', 4, 'ace-icon fa fa-bookmark', 'nav', 0, '2016-07-03 14:50:07', '2016-07-03 14:50:10'),
  ('京东', 'http://www.jd.com', 5, 'ace-icon fa fa-bookmark', 'nav', 0, '2016-07-03 14:50:07', '2016-07-03 14:50:10'),
  ('淘宝', 'https://www.taobao.com', 6, 'ace-icon fa fa-bookmark', 'nav', 0, '2016-07-03 14:50:07', '2016-07-03 14:50:10'),
  ('百度地图', 'http://map.baidu.com', 7, 'ace-icon fa fa-bookmark', 'nav', 0, '2016-07-03 14:50:07', '2016-07-03 14:50:10'),

  ('我的VPN', 'https://vpnso.com/account', 1, 'ace-icon fa fa-bookmark-o', 'nav', 1, '2016-07-03 14:50:07', '2016-07-03 14:50:10'),
  ('谷歌邮箱', 'https://mail.google.com/mail/u/0/#inbox', 2, 'ace-icon fa fa-bookmark-o', 'nav', 1, '2016-07-03 14:50:07', '2016-07-03 14:50:10'),
  ('公司邮箱', 'https://outlook.office.com/owa', 3, 'ace-icon fa fa-bookmark-o', 'nav', 1, '2016-07-03 14:50:07', '2016-07-03 14:50:10'),
  ('住房公积金', 'https://persons.shgjj.com', 4, 'ace-icon fa fa-bookmark-o', 'nav', 1, '2016-07-03 14:50:07', '2016-07-03 14:50:10'),
  ('公司首页', 'https://auth.pactera.com', 5, 'ace-icon fa fa-bookmark-o', 'nav', 1, '2016-07-03 14:50:07',
   '2016-07-03 14:50:10'),
  ('application-properties',
   'http://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html', 6,
   'ace-icon fa fa-bookmark-o', 'nav', 1, '2016-07-03 14:50:07', '2016-07-03 14:50:10'),
  ('异世灵武天下', 'http://www.biquku.com/2/2731/1392191.html', 7, 'ace-icon fa fa-bookmark-o', 'nav', 1,
   '2016-07-03 14:50:07', '2016-07-03 14:50:10');

CREATE TABLE article_index
(
  id            BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  article_id    BIGINT(20)                            NOT NULL
  COMMENT '文章ID',
  title         LONGTEXT                              NOT NULL
  COMMENT '文章标题',
  summary       LONGTEXT                              NOT NULL
  COMMENT '文章摘要',
  body          LONGTEXT                              NOT NULL
  COMMENT '文章内容',
  category_name LONGTEXT                              NOT NULL
  COMMENT '栏目名称',
  is_deleted    TINYINT                               NOT NULL         DEFAULT 0
  COMMENT '是否删除 {0:未删除, 1:已删除}',
  created_time  DATETIME                              NOT NULL
  COMMENT '创建时间',
  updated_time  DATETIME                              NOT NULL
  COMMENT '最后更新时间'
)
  COMMENT '文章检索表';
CREATE UNIQUE INDEX id_UNIQUE ON article_index (id);
CREATE UNIQUE INDEX article_id_UNIQUE ON article_index (article_id);
ALTER TABLE article_index ENGINE = MyISAM;

ALTER TABLE `article_index` ADD FULLTEXT INDEX (`title`, `summary`, `body`, `category_name`);
REPAIR TABLE article_index QUICK;
