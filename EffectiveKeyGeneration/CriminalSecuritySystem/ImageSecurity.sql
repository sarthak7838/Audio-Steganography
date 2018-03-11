/*
SQLyog Community Edition- MySQL GUI v5.22a
Host - 5.0.19-nt : Database - imagesecuritysystem
*********************************************************************
Server version : 5.0.19-nt
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

create database if not exists `imagesecuritysystem`;

USE `imagesecuritysystem`;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `uname` varchar(50) NOT NULL,
  `pwd` varchar(50) default NULL,
  PRIMARY KEY  (`uname`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`uname`,`pwd`) values ('',''),('aa','aa'),('admin','admin');

/*Table structure for table `user_records` */

DROP TABLE IF EXISTS `user_records`;

CREATE TABLE `user_records` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(100) default NULL,
  `dob` varchar(100) default NULL,
  `gender` varchar(100) default NULL,
  `address` varchar(200) default NULL,
  `cricase` varchar(100) default NULL,
  `location` varchar(100) default NULL,
  `state` varchar(100) default NULL,
  `thumb_imp` varchar(100) default NULL,
  `img_path` varchar(500) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user_records` */

insert  into `user_records`(`id`,`name`,`dob`,`gender`,`address`,`cricase`,`location`,`state`,`thumb_imp`,`img_path`) values (1,'Pramod','02-Jan-1990','Male','na','Manslaughter: Involuntary','Jail','Delhi','a1.JPG','001a02.JPG'),(2,'Somil','03-Feb-1990','Male','na','Medical Marijuana','Jail','Delhi','a2.JPG','001a02.JPG'),(3,'aaaaaaa','02-Feb-1990','Male','na','Medical Marijuana','Jail','na','b1.JPG','001a02.JPG'),(4,'umesh','02-Mar-1990','Male','new delhi','MIP: A Minor in Possession','Jail','new delhi','t2.JPG','14.jpg');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
