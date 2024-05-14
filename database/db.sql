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

/*创建gym 表*/
drop table if exists `reserve`;
create table `reserve`
(
    `reserveID` int unsigned auto_increment,
    `userID` int unsigned not null,
    `reserveDate` date not null,
    `reserveTime` time not null,
    `reserveNum` int unsigned not null,
    `reserveStatus` int unsigned not null,
    `reserveNote` varchar(255),

    primary key (`reserveID`),
    foreign key (`userID`) references `user` (`userID`)
) engine = InnoDB
  default charset = utf8mb4;

/*创建gym 表*/
drop table if exists `gym`;
create table `gym`
(
    `gymID` int unsigned auto_increment,
    `gymName` varchar(32) not null,
    `gymAddress` varchar(255) not null,
    `gymPhone` int(11) unique,
    `gymEmail` varchar(32) unique,

    primary key (`gymID`)
) engine = InnoDB
  default charset = utf8mb4;

insert into `gym` (`gymName`, `gymAddress`, `gymPhone`, `gymEmail`)
values ('健身房1', '北京市朝阳区阜通东大街10号', '13811111111', 'gym@1.com'),
       ('健身房2', '北京市海淀区西二旗北路10号', '13822222222', 'gym@2.com'),
       ('健身房3', '北京市海淀区西二旗北路10号', '13833333333', 'gym@3.com'),
       ('健身房4', '北京市海淀区西二旗北路10号', '13844444444', 'gym@4.com'),
       ('健身房5', '北京市海淀区西二旗北路10号', '13855555555', 'gym@5.com');

drop table if exists `facility`;
create table `facility`
(
    `facilityID` int unsigned auto_increment,
    `facilityName` varchar(32) not null,
    `facilityDescription` varchar(255) not null,
    `facilityPrice` decimal(10, 2) not null,
    `gymID` int unsigned not null,

    primary key (`facilityID`),
    foreign key (`gymID`) references `gym` (`gymID`)
) engine = InnoDB
  default charset = utf8mb4;

insert into `facility` (`facilityName`, `facilityDescription`, `facilityPrice`, `gymID`)
values ('瑜伽', '专业的瑜伽教练团队，提供全方位的瑜伽服务', 1000.00, 1),
       ('健身房', '专业的健身房，提供全方位的健身房服务', 2000.00, 1),
       ('舞蹈', '专业的舞蹈教练团队，提供全方位的舞蹈服务', 1500.00, 1),
       ('游泳', '专业的游泳教练团队，提供全方位的游泳服务', 1200.00, 1),
       ('瘦身', '专业的瘦身教练团队，提供全方位的瘦身服务', 1800.00, 1),
       ('健美', '专业的健美教练团队，提供全方位的健美服务', 2500.00, 1),
       ('瑜伽', '专业的瑜伽教练团队，提供全方位的瑜伽服务', 1000.00, 2);


drop table if exists `reservations`;
create table `reservations`
(
    `reservationID` int unsigned auto_increment,
    `userID` int unsigned not null,
    `reserveDate` date not null,
    `reserveTime` time not null,
    `reserveNum` int unsigned not null,
    `reserveStatus` int unsigned not null,
    `reserveNote` varchar(255),

    primary key (`reservationID`),
    foreign key (`userID`) references `user` (`userID`)
) engine = InnoDB
  default charset = utf8mb4;