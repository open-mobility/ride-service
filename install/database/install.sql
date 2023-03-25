-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ride_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ride_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ride_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci ;
USE `ride_db` ;

-- -----------------------------------------------------
-- Table `om_bookings`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `om_bookings` (
  `booking_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `customer_id` BIGINT UNSIGNED NULL,
  `driver_id` BIGINT UNSIGNED NULL,
  `pickup_lat` VARCHAR(15) NULL,
  `pickup_long` VARCHAR(15) NULL,
  `drop_off_lat` VARCHAR(15) NULL,
  `drop_off_long` VARCHAR(15) NULL,
  `dt_created` DATETIME NULL,
  `dt_updated` DATETIME NULL,
  `status` ENUM('PENDING', 'DONE', 'CANCELED', 'IN_PROGRESS') NULL DEFAULT 'PENDING',
  PRIMARY KEY (`booking_id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
