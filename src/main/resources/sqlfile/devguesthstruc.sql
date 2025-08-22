
DROP TABLE IF EXISTS `room_type_prices`;
CREATE TABLE `room_type_prices` (
  `id` int NOT NULL AUTO_INCREMENT,
  `location_id_fk` int NOT NULL,
  `room_type_id_fk` int NOT NULL,
  `amount` decimal(8,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;


DROP TABLE IF EXISTS `room_types`;
CREATE TABLE `room_types` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(20) NOT NULL,
  `is_active` bit(1) NOT NULL DEFAULT b'1',
  `guests_count` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

