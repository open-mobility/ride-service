TRUNCATE table om_bookings;

INSERT INTO om_bookings (customer_id, driver_id, pickup_lat, pickup_long, drop_off_lat, drop_off_long, dt_created, dt_updated, status)
VALUES
  (1, 2, '40.7128', '-74.0060', '34.0522', '-118.2437', NOW(), NOW(), 'PENDING'),
  (3, 4, '37.7749', '-122.4194', '47.6062', '-122.3321', NOW(), NOW(), 'PENDING');

