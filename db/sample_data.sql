-- Sample data for Inventory Management System

INSERT INTO categories (name) VALUES ('Electronics'), ('Groceries'), ('Clothing');

INSERT INTO suppliers (name, contact_info) VALUES
('ABC Electronics', 'abc@electronics.com'),
('Fresh Farms', 'contact@freshfarms.com'),
('Fashion Hub', 'info@fashionhub.com');

INSERT INTO products (name, category_id, supplier_id, price, stock) VALUES
('Laptop', 1, 1, 75000, 10),
('Smartphone', 1, 1, 30000, 25),
('Rice', 2, 2, 50, 100),
('T-Shirt', 3, 3, 500, 40);
