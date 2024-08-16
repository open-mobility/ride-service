CREATE TABLE IF NOT EXISTS om_bookings (
  booking_id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  customer_id BIGINT NULL,
  driver_id BIGINT NULL,
  pickup_lat VARCHAR(15) NULL,
  pickup_long VARCHAR(15) NULL,
  drop_off_lat VARCHAR(15) NULL,
  drop_off_long VARCHAR(15) NULL,
  dt_created TIMESTAMP NULL,
  dt_updated TIMESTAMP NULL,
  status VARCHAR(20) NULL DEFAULT 'PENDING'
);


create table IF NOT EXISTS om_service_area (service_area_id bigint generated by default as identity, area_name varchar(255), city_id bigint, country_code varchar(255), dt_created timestamp(6), dt_updated timestamp(6), geo_hashes varchar(255) array, primary key (service_area_id))