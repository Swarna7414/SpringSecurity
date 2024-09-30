CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    firstname VARCHAR(50),
    lastname VARCHAR(50),
    address VARCHAR(255),
    age VARCHAR(10),
    phonenumber VARCHAR(15) NOT NULL,
    city VARCHAR(100),
    state VARCHAR(50),
    pincode VARCHAR(10),
    role VARCHAR(50)
);
