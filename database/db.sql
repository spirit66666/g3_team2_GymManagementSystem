
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

