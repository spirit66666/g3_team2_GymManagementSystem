DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `login_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录名',
                         `user_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
                         `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
                         `sex` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
                         `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
                         `address` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;


DROP TABLE IF EXISTS `register`;
CREATE TABLE register (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          pwd VARCHAR(255) NOT NULL,
                          phone VARCHAR(20) NOT NULL
);

drop table if exists `gym_user`;
create table `gym_user`
(
    `id`         int unsigned auto_increment,
    `username` varchar(16) not null,
    `password` varchar(32) not null,
    primary key (`id`)
) engine = InnoDB
  default charset = utf8mb4;

