CREATE DATABASE `test` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

-- test.employee definition

CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

INSERT INTO test.employee (name) VALUES
	 ('paula sanchez'),
	 ('manny'),
	 ('henry');
