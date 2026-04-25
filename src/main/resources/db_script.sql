CREATE DATABASE customer_mgmt;
USE customer_mgmt;
show tables;
select * from users;

CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(100) NOT NULL,
    user_phone VARCHAR(15),
    user_email VARCHAR(100),
    gender VARCHAR(10),
    photo VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'USER'
);

CREATE TABLE customers (
    cust_id VARCHAR(50) PRIMARY KEY,
    cust_name VARCHAR(100) NOT NULL,
    cust_add VARCHAR(255),
    cust_phone VARCHAR(15),
    cust_email VARCHAR(100),
    cust_type ENUM('SILVER','GOLD','DIAMOND') NOT NULL
);

-- Pre-create admin (password = Admin1234)
 INSERT INTO users (user_name, user_email, password, role)
VALUES ('Admin', 'admin@csm.com', '{bcrypt}$2a$10$y840R2nATpbHjazw9Tv1fO1fTF/i/GVQF7no.CymCnmuXHbafKZ3y', 'ADMIN');