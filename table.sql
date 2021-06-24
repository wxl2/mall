CREATE DATABASE IF NOT EXISTS mall 
DEFAULT CHARACTER SET utf8
DEFAULT COLLATE utf8_chinese_ci;

USE mall;

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) NOT NULL,
  `password` varchar(20) NOT NULL,
  `role` char(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

