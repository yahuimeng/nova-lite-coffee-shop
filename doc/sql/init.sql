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

CREATE TABLE IF NOT EXISTS shopping_cart_item (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    member_id BIGINT NOT NULL COMMENT '会员 ID',
    product_code VARCHAR(64) NOT NULL COMMENT '商品编码',
    product_name VARCHAR(128) NOT NULL COMMENT '商品名称',
    quantity INT NOT NULL COMMENT '数量',
    unit_price DECIMAL(10, 2) NOT NULL COMMENT '单价'
) COMMENT='购物车项表';

DELETE FROM shopping_cart_item WHERE member_id = 1001;

INSERT INTO shopping_cart_item (member_id, product_code, product_name, quantity, unit_price)
VALUES
    (1001, 'LATTE', '拿铁', 2, 28.00),
    (1001, 'MOCHA', '摩卡', 1, 32.00);

CREATE TABLE IF NOT EXISTS coffee_order (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    order_no VARCHAR(64) NOT NULL UNIQUE COMMENT '订单号',
    tenant_id VARCHAR(64) NOT NULL COMMENT '租户 ID',
    product_code VARCHAR(64) NOT NULL COMMENT '商品编码',
    product_name VARCHAR(128) NOT NULL COMMENT '商品名称',
    quantity INT NOT NULL COMMENT '购买数量',
    total_amount DECIMAL(10, 2) NOT NULL COMMENT '订单总金额',
    status VARCHAR(32) NOT NULL COMMENT '订单状态',
    created_time DATETIME NOT NULL COMMENT '创建时间'
) COMMENT='咖啡订单表';
