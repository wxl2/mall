CREATE DATABASE IF NOT EXISTS mall DEFAULT CHARACTER SET utf8;


USE mall;

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) NOT NULL,
  `password` varchar(20) NOT NULL,
  `role` char(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `mall`.`user`ADD INDEX(`username`);

CREATE TABLE IF NOT EXISTS `applyStore` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) NOT NULL,
  `status` varchar(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `isexists` (`username`),
  CONSTRAINT `isexists` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `mall`.`user`
ADD COLUMN `phone` varchar(16) NULL AFTER `role`,
ADD COLUMN `addr` varchar(50) CHARACTER SET utf8 NULL AFTER `phone`;

ALTER TABLE `mall`.`user`
DROP INDEX `username`,
ADD UNIQUE INDEX `username`(`username`) USING BTREE;

