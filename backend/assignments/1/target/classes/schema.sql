CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_date TIMESTAMP NOT NULL,
    modified_date TIMESTAMP,
    deleted_date TIMESTAMP
);

CREATE TABLE houses (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    created_date TIMESTAMP NOT NULL,
    modified_date TIMESTAMP,
    deleted_date TIMESTAMP
);

CREATE TABLE house_users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    house_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    role VARCHAR(50) NOT NULL,
    created_date TIMESTAMP NOT NULL,
    modified_date TIMESTAMP,
    deleted_date TIMESTAMP,
    CONSTRAINT fk_house_users_house FOREIGN KEY (house_id) REFERENCES houses(id),
    CONSTRAINT fk_house_users_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE rooms (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    house_id BIGINT NOT NULL,
    created_date TIMESTAMP NOT NULL,
    modified_date TIMESTAMP,
    deleted_date TIMESTAMP,
    CONSTRAINT fk_rooms_house FOREIGN KEY (house_id) REFERENCES houses(id)
);

CREATE TABLE device_inventory (
    kickston_id VARCHAR(6) PRIMARY KEY,
    device_username VARCHAR(255) NOT NULL,
    device_password VARCHAR(255) NOT NULL,
    manufacture_date_time TIMESTAMP NOT NULL,
    manufacture_factory_place VARCHAR(255) NOT NULL
);

CREATE TABLE devices (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    kickston_id VARCHAR(6) NOT NULL UNIQUE,
    house_id BIGINT NOT NULL,
    room_id BIGINT,
    created_date TIMESTAMP NOT NULL,
    modified_date TIMESTAMP,
    deleted_date TIMESTAMP,
    CONSTRAINT fk_devices_house FOREIGN KEY (house_id) REFERENCES houses(id),
    CONSTRAINT fk_devices_room FOREIGN KEY (room_id) REFERENCES rooms(id)
);

CREATE INDEX idx_house_users_house ON house_users(house_id);
CREATE INDEX idx_house_users_user ON house_users(user_id);
CREATE INDEX idx_rooms_house ON rooms(house_id);
CREATE INDEX idx_devices_house ON devices(house_id);
CREATE INDEX idx_devices_room ON devices(room_id);
