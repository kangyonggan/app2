CREATE TABLE user
(
  id            BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  email         VARCHAR(128)                          NOT NULL
  COMMENT '电子邮箱',
  is_verified   TINYINT                               NOT NULL         DEFAULT 0
  COMMENT '是否校验 {0:未校验, 1:已校验}',
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
  mood          VARCHAR(255)                          NOT NULL         DEFAULT ''
  COMMENT '心情',
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
);
CREATE UNIQUE INDEX id_UNIQUE ON user (id);
CREATE UNIQUE INDEX email_UNIQUE ON user (email);

INSERT INTO user (id, email, is_verified, password, salt, realname, mobile, mood, created_time, updated_time, login_time)
VALUES
  (1, 'kangyonggan@gmail.com', 1, '9606b0029ba4a8c9369f288cced0dc465eb5eabd', '3685072edcf8aad8', '康永敢', '18221372104',
   '今天真TMD倒霉, 在世纪大道换乘的时候手机被偷了, 还好我是个穷光蛋, 要不要就不仅仅是丢手机了, 估计就连银行卡都要被盗刷了, 那人或那人后面的人绝逼是个程序员!!!',
   '2016-06-21 13:40:15', '2016-06-21 18:40:17',
   '2016-06-21 13:40:17'),
  (2, 'kangyonggan@qq.com', 1, '9606b0029ba4a8c9369f288cced0dc465eb5eabd', '3685072edcf8aad8', '黄芳玲', '15151679072', '',
      '2016-06-21 17:12:16', '2016-06-21 17:12:23', '2016-06-21 17:12:26'),
  (3, 'houbinbin@qq.com', 1, '9606b0029ba4a8c9369f288cced0dc465eb5eabd', '3685072edcf8aad8', '侯彬彬', '', '',
      '2016-06-21 17:12:16', '2016-06-21 17:12:23', '2016-06-21 17:11:26'),
  (4, 'liujinfu@qq.com', 1, '9606b0029ba4a8c9369f288cced0dc465eb5eabd', '3685072edcf8aad8', '刘金甫', '', '',
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
);
CREATE UNIQUE INDEX id_UNIQUE ON role (id);
CREATE UNIQUE INDEX code_UNIQUE ON role (code);

INSERT INTO role (id, code, name, created_time, updated_time)
VALUES (1, 'ROLE_ADMIN', '管理员', '2016-06-21 13:37:50', '2016-06-21 13:37:52');

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
  COMMENT '菜单排序',
  icon         VARCHAR(128)                          NOT NULL         DEFAULT ''
  COMMENT '菜单图标的样式',
  is_deleted   TINYINT                               NOT NULL         DEFAULT 0
  COMMENT '是否删除 {0:未删除, 1:已删除}',
  created_time DATETIME                              NOT NULL
  COMMENT '创建时间',
  updated_time DATETIME                              NOT NULL
  COMMENT '最后更新时间'
);
CREATE UNIQUE INDEX id_UNIQUE ON menu (id);
CREATE UNIQUE INDEX code_UNIQUE ON menu (code);
CREATE UNIQUE INDEX url_UNIQUE ON menu (url);

INSERT INTO menu (id, code, name, pid, url, sort, icon, created_time, updated_time)
VALUES (1, 'root', '根菜单', '0', 'root', 1, '', '2016-06-21 15:14:35', '2016-06-21 15:14:38'),
  (2, 'sys-manage', '系统', '1', 'sys/manage', 1, 'ace-icon fa fa-cog bigger-140', '2016-06-21 15:14:35',
   '2016-06-21 15:14:38'),
  (21, 'sys-user', '用户管理', '2', 'sys/user', 1, 'ace-icon fa fa-users bigger-120', '2016-06-21 15:14:35',
   '2016-06-21 15:14:38'),
  (22, 'sys-role', '角色管理', '2', 'sys/role', 2, 'ace-icon fa fa-hdd-o bigger-120', '2016-06-21 15:14:35',
   '2016-06-21 15:14:38'),
  (23, 'sys-menu', '菜单管理', '2', 'sys/menu', 3, 'ace-icon fa fa-list bigger-120', '2016-06-21 15:14:35',
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
