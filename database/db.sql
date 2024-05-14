/*创建名为db_gym的schema */
CREATE DATABASE IF NOT EXISTS `db_gym`;
USE `db_gym`;

/*创建user 表*/
drop table if exists `user`;
create table `user`
(
    `userID` int unsigned auto_increment,
    `userName` varchar(16) not null,
    `passWord` varchar(32) not null,
    `mobilePhone` int(11) unique,
    `email` varchar(32) unique,


    primary key (`userID`)
) engine = InnoDB
  default charset = utf8mb4;

/*创建administrator 表*/
drop table if exists `administrator`;
create table `administrator`
(
        `adminID` int unsigned auto_increment,
        `adminName` varchar(16) not null,
        `mobilePhone` int(11) unique,
        `email` varchar(32) unique,


    primary key (`adminID`)
) engine = InnoDB
  default charset = utf8mb4;