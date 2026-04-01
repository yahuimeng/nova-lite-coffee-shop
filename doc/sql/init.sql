CREATE TABLE IF NOT EXISTS coffee_product (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    product_code VARCHAR(64) NOT NULL UNIQUE COMMENT '商品编码',
    name VARCHAR(128) NOT NULL COMMENT '商品名称',
    price DECIMAL(10, 2) NOT NULL COMMENT '商品价格',
    available TINYINT NOT NULL DEFAULT 1 COMMENT '是否可售，1-可售 0-不可售'
) COMMENT='咖啡商品表';

INSERT INTO coffee_product (product_code, name, price, available)
VALUES
    ('LATTE', '拿铁', 28.00, 1),
    ('AMERICANO', '美式', 22.00, 1),
    ('MOCHA', '摩卡', 32.00, 1),
    ('SEASONAL', '季节限定特调', 36.00, 0)
ON DUPLICATE KEY UPDATE
    name = VALUES(name),
    price = VALUES(price),
    available = VALUES(available);
