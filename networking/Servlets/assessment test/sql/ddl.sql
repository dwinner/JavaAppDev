CREATE DATABASE IF NOT EXISTS `assessment`;

USE `assessment`;

CREATE TABLE IF NOT EXISTS `employee` (
    `employee_id` INT(10) UNSIGNED NOT NULL,
    `employee_key` VARCHAR(40) NOT NULL,
    PRIMARY KEY (`employee_id`)
)  ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `schedule` (
   `schedule_id` INT(10) UNSIGNED NOT NULL,
   `employee_id` INT(10) UNSIGNED NOT NULL,
   `startdatetime` TIMESTAMP NOT NULL,
   `enddatetime` TIMESTAMP NOT NULL,
   FOREIGN KEY (`employee_id`) REFERENCES `employee`(`employee_id`)
         ON UPDATE CASCADE ON DELETE CASCADE,
   PRIMARY KEY (`schedule_id`)   
)  ENGINE=InnoDB;