-- Create database
CREATE DATABASE IF NOT EXISTS product_manager DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE product_manager;

-- Create product table
CREATE TABLE IF NOT EXISTS product (
  id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'Primary key',
  name VARCHAR(100) NOT NULL COMMENT 'Product name',
  price DECIMAL(10, 2) NOT NULL DEFAULT 0 COMMENT 'Product price',
  stock INT NOT NULL DEFAULT 0 COMMENT 'Product stock',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Create time',
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
  INDEX idx_name (name),
  INDEX idx_create_time (create_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Product table';

-- Insert test data
INSERT INTO product (name, price, stock) VALUES
('iPhone 15 Pro', 8999.00, 50),
('Samsung Galaxy S24', 7999.00, 45),
('iPad Pro 12.9', 9999.00, 30),
('MacBook Air M3', 12999.00, 25),
('AirPods Pro 2', 1899.00, 100),
('Apple Watch Series 9', 3299.00, 60),
('Sony WH-1000XM5', 2499.00, 80),
('Dell XPS 13', 8999.00, 20),
('LG 27 inch 4K Monitor', 3999.00, 15),
('Logitech MX Master 3S', 799.00, 40);
