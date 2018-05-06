use blog;

-- 建表
-- 1.创建员工表
drop table staff;
create TABLE staff (name VARCHAR(20), no VARCHAR(20) PRIMARY KEY, passwd INT DEFAULT 111111, birthday VARCHAR(20), phone VARCHAR(20)) CHARSET=utf8;




-- 数据操作
-- 1.插入user
INSERT INTO user VALUES ("123", "123", "123", "123", "123", "123");

-- 2.插入客户
INSERT INTO customer VALUES ('HuangDan', 0902150228, 111111, 970101, 18373151462, '中南大学-升华公寓');
INSERT INTO customer VALUES ('2', 4, 1, '2011-11-11', 18373151462, '中南大学-升华公寓');

-- 3.插入初始信用卡号&&账号
INSERT INTO num_set VALUES (0901150101, 1);
INSERT INTO num_set VALUES (0902150201, 2);
INSERT INTO num_set VALUES (0903150303, 3);