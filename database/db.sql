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
        `passWord` varchar(32) not null,
        `mobilePhone` int(11) unique,
        `email` varchar(32) unique,


    primary key (`adminID`)
) engine = InnoDB
  default charset = utf8mb4;



/*创建gym 表*/
drop table if exists `gym`;
create table `gym`
(
    `gymID` int unsigned auto_increment,
    `gymName` varchar(32) not null,
    `gymAddress` varchar(255) not null,


    primary key (`gymID`)
) engine = InnoDB
  default charset = utf8mb4;

insert into `gym` (`gymName`, `gymAddress`)
values ('健身房', '北京市海淀区西二旗北三环西路10号'),
       ('游泳馆', '北京市海淀区西二旗北三环西路10号'),
       ('瑜伽馆', '北京市海淀区西二旗北三环西路10号'),
       ('舞蹈室', '北京市海淀区西二旗北三环西路10号'),
       ('健身器材', '北京市海淀区西二旗北三环西路10号');


/*创建 reserve 表*/
drop table if exists `reservations`;
create table `reservations`
(
    `reserveID` int unsigned auto_increment,
    `userID` int unsigned not null,
    `reserveDate` date not null,
    `reserveTime` varchar(32) not null,
    `reserveGym` varchar(32) not null,
    `reserveNum` int unsigned ,
    `reserveStatus` int unsigned ,
    `reserveNote` varchar(255),
    primary key (`reserveID`)
) engine = InnoDB
  default charset = utf8mb4;


/*创建 facility 表*/
drop table if exists `facility`;
create table `facility`
(
    `facilityID` int unsigned auto_increment,
    `facilityName` varchar(32) not null,
    `facilityDescription` varchar(255) not null,
    `facilityNum` int unsigned not null,
    `remainNum` int unsigned not null,
    `gymID` int unsigned not null,

    primary key (`facilityID`),
    foreign key (`gymID`) references `gym` (`gymID`)
 ) engine = InnoDB
   default charset = utf8mb4;

 insert into `facility` (`facilityName`, `facilityDescription`, `facilityNum`, `remainNum`, `gymID`)
 values ('瑜伽', '瑜伽馆', 10, 10, 1),
        ('健身房', '健身房', 10, 10, 2),
        ('游泳馆', '游泳馆', 10, 10, 3),
        ('舞蹈室', '舞蹈室', 10, 10, 4),
        ('健身器材', '健身器材', 10, 10, 5);
