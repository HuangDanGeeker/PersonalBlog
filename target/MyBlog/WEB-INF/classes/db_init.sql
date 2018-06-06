use blog;

-- 建表
-- 1.创建user信息表
-- 2.创建blog记录表
create table blog_123 (title varchar(30), content varchar(40), submitTime DATETIME)CHARSET = 'UTF8';



-- 数据操作
drop table blog_12309;
CREATE TABLE `blog_12309` (
  `code` varchar(40) NOT NULL,
  `title` varchar(30) DEFAULT NULL,
  `content` longtext DEFAULT NULL,
  `submitTime` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;