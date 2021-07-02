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

CREATE TABLE `mall`.`goodstype`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `typeid` int(0) NOT NULL,
  `typename` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `mall`.`goods`  (
  `goodsid` int(15) NOT NULL,
  `goodsname` varchar(30) CHARACTER SET utf8 NOT NULL,
  `price` float(10, 2) NOT NULL,
  `typeid` int(5) NOT NULL,
  `imgurl` varchar(100) CHARACTER SET utf8 NOT NULL,
  `goodsuser` varchar(25) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`goodsid`),
  UNIQUE INDEX `goodsid`(`goodsid`),
  CONSTRAINT `goodsuser` FOREIGN KEY (`goodsuser`) REFERENCES `mall`.`user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `goodstype` FOREIGN KEY (`typeid`) REFERENCES `mall`.`goodstype` (`typeid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;