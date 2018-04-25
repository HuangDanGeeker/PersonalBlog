create
use blog_system;

-- 建表
-- 1.创建员工表
drop table staff;
create TABLE staff (name VARCHAR(20), no VARCHAR(20) PRIMARY KEY, passwd INT DEFAULT 111111, birthday VARCHAR(20), phone VARCHAR(20)) CHARSET=utf8;
-- 2.创建客户表
drop table customer;
create TABLE customer (name VARCHAR(20), no VARCHAR(20) PRIMARY KEY, passwd INT DEFAULT 111111,birthday VARCHAR(20), phone VARCHAR(20), address VARCHAR(40)) CHARSET=utf8;
-- 3.创建客户操作记录表
create TABLE recd_custm_0902150228(no VARCHAR(20) PRIMARY KEY, operType INT, nums INT, dealerNo VARCHAR(20) DEFAULT '0', dealerName VARCHAR(20) DEFAULT '0', operDate DATETIME);
-- 4.创建柜员操作记录表
create TABLE recd_staff_1(operType VARCHAR(20), custmNo VARCHAR(20) NOT NULL, custmName VARCHAR(20) NOT NULL, creditCardNo varchar(20) NOT NULL ,nums INT, operDate VARCHAR(20));
drop TABLE  recd_staff_1;
-- 5.创建用户账号下所有的银行卡存款表
create TABLE  creditcard_0902150228(cardNo VARCHAR(20) PRIMARY KEY NOT NULL , nums INT DEFAULT 0, intrest FLOAT DEFAULT 0, dueTime VARCHAR(20));
-- 6.创建信用卡号&&账号集(存储最新创建的账号和信用卡账号)
create TABLE  num_set(no VARCHAR(20) PRIMARY KEY, type INT DEFAULT 0);
-- 7.创建存储利率表
create TABLE  rate_table(type VARCHAR(20) PRIMARY KEY, rate FLOAT DEFAULT 0);
-- 数据操作
-- 1.插入员工
INSERT INTO staff VALUES ('HuangDan', 0902150228, 111111, 970101, 18373151462);
INSERT INTO staff VALUES ('1', 1, 1, 970101, 18373151462);

-- 2.插入客户
INSERT INTO customer VALUES ('HuangDan', 0902150228, 111111, 970101, 18373151462, '中南大学-升华公寓');
INSERT INTO customer VALUES ('2', 4, 1, '2011-11-11', 18373151462, '中南大学-升华公寓');

-- 3.插入初始信用卡号&&账号
INSERT INTO num_set VALUES (0901150101, 1);
INSERT INTO num_set VALUES (0902150201, 2);
INSERT INTO num_set VALUES (0903150303, 3);