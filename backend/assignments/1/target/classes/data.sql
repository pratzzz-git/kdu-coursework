INSERT INTO users (email, password, created_date)
VALUES
('admin@smarthome.com', 'admin123', CURRENT_TIMESTAMP),
('user@smarthome.com', 'user123', CURRENT_TIMESTAMP);

INSERT INTO houses (name, address, created_date)
VALUES ('My First House', 'Bangalore', CURRENT_TIMESTAMP);

INSERT INTO house_users (house_id, user_id, role, created_date)
VALUES
(1, 1, 'ADMIN', CURRENT_TIMESTAMP),
(1, 2, 'USER', CURRENT_TIMESTAMP);

INSERT INTO rooms (name, house_id, created_date)
VALUES
('Living Room', 1, CURRENT_TIMESTAMP),
('Bedroom', 1, CURRENT_TIMESTAMP);

INSERT INTO device_inventory
(kickston_id, device_username, device_password, manufacture_date_time, manufacture_factory_place)
VALUES
('KD1001', 'device_admin', 'device_pass', CURRENT_TIMESTAMP, 'Bangalore'),
('KD1002', 'device_admin', 'device_pass', CURRENT_TIMESTAMP, 'Pune');

INSERT INTO devices
(kickston_id, house_id, room_id, created_date)
VALUES
('KD1001', 1, 1, CURRENT_TIMESTAMP);
