[TOC]

# MySQL-基础

## MySQL-简介

### 数据库三层结构

1. 所谓安装Mysql数据库，就是在主机安装一个数据库管理系统(DBMS)，这个管理程序可以管理多个数据库。DBMS(database manage system)
2. 一个数据库中可以创建多个表,以保存数据(信息)。
3. 数据库管理系统(DBMS)、数据库和表的关系如图所示:示意图

![](image/Snipaste_2022-11-23_14-37-24.png)



### 数据在数据库中的存储方式

![](image/Snipaste_2022-11-23_14-38-21.png)



### SQL语句分类

- DDL:数据定义语句[create表，库...]
- DML:数据操作语句[增加insert,修改update，删除delete]
- DQL:数据查询语句[select ]
- DCL:数据控制语句[管理数据库:比如用户权限grant revoke ]



### 创建数据库

```sql
#演示数据库的操作
# 创建一个名称为frx_db01的数据库。[图形化和指令 演示]

CREATE DATABASE IF NOT EXISTS db_name;
#使用指定创建数据库
CREATE DATABASE frx_db01;

#删除数据库
DROP DATABASE frx_db01;

# 创建一个使用utf8字符集的frx_db02数据库
CREATE DATABASE frx_db02  CHARACTER SET utf8

# 创建一个使用utf8字符集，并带校队规则的frx_db03数据库
CREATE DATABASE frx_db03 CHARACTER SET utf8 COLLATE utf8_bin
# 校对规则 utf8_bin 区分大小写 默认utf8_general_ci 不区分大小写
# 如果数据库下面的表没有指定字符集和校对规则，以它的数据库校对规则为准
# 下面是一条SQL语句，select 查询 * 表示所有字段
SELECT * FROM t1 
WHERE NAME='tom'
```



### 查看、删除数据库

```sql
# 演示删除和查询数据库
# 查看当前数据库服务器中的所有数据库
SHOW DATABASES

#查看前面创建的frx_db01数据库的定义信息
SHOW CREATE DATABASE frx_db01

#说明 在创建数据库，表的时候，为了规避关键字，可以使用反引号解决
CREATE DATABASE `CREATE`

#删除前面创建的frx_db01数据库
DROP DATABASE frx_db01

```



### 备份恢复数据库

```sql
#练习：database03.sq1 备份hsp_db02 和 hsp_db03 库中的数据，并恢复

#备份，要在Dos下执行mysqldump指令其实在mysql安装目录\bin
#这个备份文件，就是对应的sql语句
mysqldump -u root -p -B frx_db02 frx_db03 >d:\\bak.sql

DROP DATABASE frx_db03;

#恢复数据库(注意：使用 mysql -u root -p  进入Mysql命令行执行)
source d:\\bak.sql
#第二个恢复方法，直接将bak.sql的内容放到查询编辑器中，执行
```



### 创建表

```sql
CREATE TABLE table_name
(
    field1 datatype,
    field2 datatype
)CHARACTER SET 字符集 COLLATE 校对规则 ENGINE 存储引擎
#指令创建表
#注意：hsp_db02创建表时，要根据需保存的数据创建相应的列，并根据数据的类型定义相应的列类
#id           整形
#name         字符串
#password     字符串
#birthday     日期
CREATE TABLE `USER`(
	id INT,
	`name` VARCHAR(255),
	`password` VARCHAR(255),
	`birthday` DATE)
	CHARACTER SET utf8 COLLATE utf8_bin ENGINE INNODB;

```



### MySQL常用数据类型（列类型）

![](image/Snipaste_2022-11-23_14-47-07.png)





## MySQL-CURD

### 数据库 CURD 语句

```sh
# 创建（Create）、更新（Update）、读取（Read）和删除（Delete）
```

1. **Insert语句 (添加数据)**
2. **Update语句 (更新数据)**
3. **Delete语句 (删除语句)**
4. **Select语句 (查找语句)**



### INSERT 语句

- **基本语法**

```sh
# INSEET INTO table_name [(column [, column... ])]
# VALUES (value [, value...]);
```



```sql
# 练习insert语句
-- 创建一张商品表 goods(id int,goods_name varchar(10),price double);
-- 添加2条记录
CREATE TABLE goods(
	id INT,
	goods_name VARCHAR(10),
	price DOUBLE);
-- 添加数据
INSERT INTO goods( id,goods_name,price)
	VALUES(10,'华为手机',2000);

INSERT INTO goods(id,goods_name,price)
	VALUES(20,'苹果手机',3000);

SELECT * FROM goods

```



- **细节说明**

```sql
# 说明insert 语句的细节
-- 1.插入的数据应与字段的数据类型相同
-- 比如把'abc'添加到int 类型会错误
INSERT INTO `goods` (id,goods_name,price)
	VALUES('30','小米手机',2000);
	
-- 2.数据的长度应在列的规定范围内，例如：不能将一个长度为80的字符串加入到长度为40的列中。
INSERT INTO `goods` (id,goods_name,price)
	VALUES(40,'vivo手机vivo手机',3000);
	
-- 3.在values中列出的数据位置必须与被加入的列的位置相对应。
INSERT INTO `goods` (id,goods_name,price)
	VALUES('vivo手机',40,2000);
	
-- 4.字符和日期型数据应包含在单引号中。
INSERT INTO `goods` (id,goods_name,price)
	VALUES(40,'vivo手机',3000);
	
-- 5.列可以插入空值[前提是该字段允许为空],insert into table value(null)
INSERT INTO `goods` (id,goods_name,price)
	VALUES(40,'vivo手机',NULL);
	
-- 6.insert into tab_name(列名...) values (),(),() 形式添加多条记录
INSERT INTO `goods` (id,goods_name,price)

	VALUES(40,'三星手机',2300), VALUES(40,'海尔手机',1800);
-- 7.如果是给表中的所有字段添加数据，可以不写前面的字段名称
INSERT INTO `goods` VALUES (45,'华为手机',1500);

-- 8.默认值的使用，当不给某个字段值时，如果有默认值就会添加，否则报错
--   如果某个字段没有指定 not null,那么当添加数据时，没有给定值，则会给默认值
--   如果我们希望指定某个列默认值，
INSERT INTO `goods` (id,goods_name)
	VALUES (80,'格力手机');
SELECT * FROM goods;
INSERT INTO `goods2` (id,goods_name)
	VALUES (80,'格力手机');
SELECT * FROM goods2;

```



### UPDATE 语句

- **基本语法**

```sh
# UPDATE tab1_name 
#   set col_name1 = expr1 [, col_name2 = expr2 ...]
#   [where where_definition]
```



```sql
-- 演示update语句
-- 要求：在上面创建的employee表中修改表中的记录

-- 1.将所有员工薪水修改为5000元[如果没有带where条件，会修改所有的记录，因此要小心]
UPDATE employee SET salary =5000

-- 2.将姓名为 小妖怪 的员工薪水修改为3000元
UPDATE employee 
	SET salary = 3000 
	WHERE user_name='小妖怪';
	
-- 3.将老妖怪 的薪水在原有基础上增加1000元
INSERT INTO employee 
	VALUES(200,'老妖怪','男','2001-02-11','2021-01-05 10:10:10','寻路的',5000,'哈哈','e:\\b.jpg');
UPDATE employee
	SET salary=salary+1000
	WHERE user_name='老妖怪';
	
-- 可以修改多个列
UPDATE employee
	SET salary=salary+1000,job='出主意'
	WHERE user_name='老妖怪';
SELECT * FROM employee;

```



- **使用细节**

1. UPDATE语法可以用新值更新原有表行中的各列。
2. SET子句指示要修改哪些列和要给予哪些值。
3. WHERE子句指定应更新哪些行。如没有WHERE子句,则更新所有的行(记录)，因此老师提醒一定小心。
4. 如果需要修改多个字段,可以通过set字段1=值1,字段2=值2.…



### DELETE 语句

- **基本语法**

```sh
# DELETE FROM tab1_name 
#   [where where_definition]
```



```sql
 -- delete 语句演示
 -- 删除表中名称为'老妖怪'的记录
 DELETE FROM employee
	WHERE user_name='老妖怪'
	
 -- 删除表中所有记录
 DELETE FROM employee;
 
 -- delete语句不能删除某一列的值(可使用update 设为null或者'')
 UPDATE employee SET job ='' WHERE user_name='老妖怪';
 SELECT * FROM employee;
 
 -- 要删除表 
 DROP TABLE employee;

```



- **使用细节**

1. 如果不使用where子句，将删除表中所有数据。
2. Delete语句不能删除某一列的值(可使用update设为null或者“ ”)
3. 使用delete语句仅删除记录，不删除表本身。如要删除表，使用drop table语句。drop table表名;



### SELECT 语句

- **基本语法**

```sh
# SELECT [DISTINCT] * | {column1, column2, column3, ...}
#        FROM table_name;
```



- **注意事项**

1. Select 指定查询哪些列的数据。
2. column指定列名。
3. *号代表查询所有列。
4. From指定查询哪张表。
5. DISTINCT可选，指显示结果时，是否去掉重复数据



```sql
-- select 语句
CREATE TABLE student(
	id INT NOT NULL DEFAULT 1,
	NAME VARCHAR(20) NOT NULL DEFAULT '',
	chinese FLOAT NOT NULL DEFAULT 0.0,
	english FLOAT NOT NULL DEFAULT 0.0,
	math FLOAT NOT NULL DEFAULT 0.0);
INSERT INTO student(id,NAME,chinese,english,math) VALUES(1,'jack',89,78,90);
INSERT INTO student(id,NAME,chinese,english,math) VALUES(2,'张飞',67,98,56);
INSERT INTO student(id,NAME,chinese,english,math) VALUES(3,'宋江',87,78,77);
INSERT INTO student(id,NAME,chinese,english,math) VALUES(4,'关羽',85,78,90);
INSERT INTO student(id,NAME,chinese,english,math) VALUES(5,'赵云',89,78,94);
INSERT INTO student(id,NAME,chinese,english,math) VALUES(6,'欧阳锋',69,76,90);
INSERT INTO student(id,NAME,chinese,english,math) VALUES(7,'黄蓉',59,73,96);

SELECT * FROM student

-- 查询表中所有学生的信息
SELECT * FROM student;

-- 查询表中所有学生的姓名和对应的英语成绩
SELECT `name`,english FROM student;

-- 过滤表中重复数据 distinct
SELECT DISTINCT english FROM student;

-- 要查询的记录，每个字段都相同，才会去重
SELECT DISTINCT `name`,english FROM student;

```



- **使用表达式对查询的列进行运算**

```sh
# SELECT [DISTINCT] * | {column1 | expression,column2,|expression...}
#        FROM table_name;
```



- **在SELECT语句中可以使用 AS 语句**

```sh
# SELECT column_name AS 别名 FROM table_name
```



```sql
-- select 语句使用
-- 统计每个学生的总分
SELECT `name`,(chinese+english+math) FROM student;

-- 在所有学生总分加10分的情况
SELECT `name`,(chinese+english+math+10) AS total_score FROM student;
-- 使用别名表示学生分数

SELECT `name` AS '名字',(chinese+english+math) AS total_score FROM student;

SELECT `name`,(chinese+english+math)*1.6 AS total_score FROM student
 WHERE `name`='赵云';

SELECT `name`,(chinese+english+math) AS total_score FROM student
 WHERE `name`='关羽';
 
 
SELECT `math` AS`Math` FROM student

```



- **在where字句中经常使用的运算符**

```sql
-- select语句
-- 查询姓名为赵云的学生成绩
SELECT * FROM student
	WHERE `name`='赵云'

-- 查询英语成绩大于90分的同学
SELECT * FROM student
	WHERE english>90
	
-- 查询总分大于200分的所有同学
SELECT * FROM student
	WHERE (chinese+english+math)>200
	
-- 查询math大于60 并且(and) id大于4的学生成绩
SELECT * FROM student
	WHERE math>60 AND id>4
	
-- 查询英语成绩大于语文成绩的同学
SELECT * FROM student
	WHERE english>chinese
	
-- 查询总分大于200 分并且数学成绩小于语文成绩 的姓张的学生
-- % ：表示任意0个或多个字符
-- _ ： 表示任意单个字符
SELECT * FROM student
	WHERE (chinese+math+english)>200 AND 
	math<chinese AND `name` LIKE'张%';
	
-- 查询英语分数为80-90之间的同学
SELECT * FROM student
	WHERE english>=80 AND english<=90
	
-- 查询数学分数为89，90，91的同学
SELECT * FROM student
	WHERE math IN(89,90,91);
	
-- 查询所有姓李的学生
SELECT * FROM student
	WHERE `name` LIKE '李%';

```



- **使用 ORDER BY 子句排序查询结果**

```sh
# SELECT column1, column2, ...
# FROM table
# ORDER BY column1 ASC | DESC, ...
```

> ```
> ASC 升序[默认] DESC 降序
> ORDER BY 子句应该位于 SELECT 语句的结尾
> ```



```sql
-- 演示order by使用
-- 对数学成绩排序后输出【升序】
SELECT * FROM student
	ORDER BY math;
	
-- 对总分按从高到低的顺序输出[降序]
SELECT  `name`,(chinese+math+english) AS total_score FROM student
	ORDER BY total_score DESC;
	
-- 对姓李的学生成绩排序输出(升序)
SELECT `name`,(chinese+math+english) AS total_score FROM student
	WHERE `name` LIKE '李%'
	ORDER BY total_score;

```



## MySQL-函数

### 合计/统计函数

#### COUNT

```sh
# Select count(*) | count(列名) from table_name [WHERE where_definition]
# SELECT COUNT(DISTINCT column_name) FROM table_name
```



```sql
-- 演示mysql的统计函数的使用
-- 统计一个班级有多少学生
SELECT COUNT(*) FROM student;

-- 统计数学成绩大于90的学生有多少个
SELECT COUNT(*) FROM student
	WHERE math>90
	
-- 统计总分大于250的人数有多少
SELECT COUNT(*) FROM student
	WHERE (chinese+math+english)>250;
	
-- count(*)和count(列)的区别
-- 解释：conut(*)返回满足条件记录的总行数
-- count(列)：统计满足条件的某列有多少个，但是会排除为 null
CREATE TABLE t15(
	`name` VARCHAR(15));
INSERT INTO t15 VALUES('tom');
INSERT INTO t15 VALUES('jack');
INSERT INTO t15 VALUES('mary');
INSERT INTO t15 VALUES(NULL);

SELECT * FROM t15;

SELECT COUNT(*) FROM t15;-- 4
SELECT COUNT(`name`) FROM t15; -- 3

```



#### SUM

```sh
# SELECT SUM(column_name1), {SUM(...)} FROM table_name WHERE ...
```



```sql
-- 演示sum函数的使用
-- 统计一个班级数学总成绩
SELECT SUM(math) FROM student;
-- 统计一个班级语文、英语、数学各科的总成绩
SELECT SUM(chinese),SUM(math),SUM(english) FROM student;
-- 统计一个班级语文、英语、数学各科的总成绩
SELECT SUM(chinese+math+english) AS total_score FROM student
-- 统计一个班级语文成绩平均分
SELECT SUM(chinese)/COUNT(*) FROM student;

```



#### AVG

```sh
# AVG函数返回满足where条件的一列的平均值

# SELECT AVG(列名) {AVG(列名)...} from tablename [WHERE where_definition]
```



```sql
-- 演示avg的使用
-- 求一个班级数学平均分
SELECT AVG(math) FROM student;
-- 求一个班级总分平均分
SELECT AVG(chinese+math+english) FROM student;

```



#### MAX/MIN

```sh
# MAX/MIN 返回满足where条件的一列的最大/最小值
# SELECT MAX(列名) from tablename [WHERE where_definition]
```



```sql
-- 演示max和min的使用
-- 求班级最高分和最低分(数值范围在统计中特别有用)
SELECT MAX(math+chinese+english) FROM student;

	
-- 求出班级数学最高分和最低分
SELECT MAX(math),MIN(math) 
	FROM student;

```



#### GROUP BY

```sh
# SELECT column1, column2, .. FROM table GROUP BY column1
```



#### HAVING

```sql
CREATE TABLE dept( /*部门表*/
deptno MEDIUMINT   UNSIGNED  NOT NULL  DEFAULT 0,
dname VARCHAR(20)  NOT NULL  DEFAULT "",
loc VARCHAR(13) NOT NULL DEFAULT ""
) ;
INSERT INTO dept VALUES
	(10,'ACCOUNTING','NEW YORK'),
	(20,'RESEARCH','DALLAS'),
	(30,'SALES','CHICAGO'),
	(40,'OPERATIONS','BOSTON');
SELECT * FROM dept;


-- 员工表
# 创建表EMP成员
CREATE TABLE emp
(empno  MEDIUMINT UNSIGNED  NOT NULL  DEFAULT 0, /*编号*/
ename VARCHAR(20) NOT NULL DEFAULT "", /*名字*/
job VARCHAR(9) NOT NULL DEFAULT "",/*工作*/
mgr MEDIUMINT UNSIGNED ,/*上级编号*/
hiredate DATE NOT NULL,/*入职时间*/
sal DECIMAL(7,2)  NOT NULL,/*薪水*/
comm DECIMAL(7,2) ,/*奖金*/
deptno MEDIUMINT UNSIGNED NOT NULL DEFAULT 0 /*部门编号*/
) ;

-- 添加测试数据
INSERT INTO emp VALUES
	(7369, 'SMITH', 'CLERK', 7902, '1990-12-17', 800.00,NULL , 20), 
	(7499, 'ALLEN', 'SALESMAN', 7698, '1991-2-20', 1600.00, 300.00, 30), 
	(7521, 'WARD', 'SALESMAN', 7698, '1991-2-22', 1250.00, 500.00, 30), 
	(7566, 'JONES', 'MANAGER', 7839, '1991-4-2', 2975.00,NULL,20), 
	(7654, 'MARTIN', 'SALESMAN', 7698, '1991-9-28',1250.00,1400.00,30),
	(7698, 'BLAKE','MANAGER', 7839,'1991-5-1', 2850.00,NULL,30), 
	(7782, 'CLARK','MANAGER', 7839, '1991-6-9',2450.00,NULL,10), 
	(7788, 'SCOTT','ANALYST',7566, '1997-4-19',3000.00,NULL,20), 
	(7839, 'KING','PRESIDENT',NULL,'1991-11-17',5000.00,NULL,10),
	(7844, 'TURNER', 'SALESMAN',7698, '1991-9-8', 1500.00, NULL,30), 
	(7900, 'JAMES','CLERK',7698, '1991-12-3',950.00,NULL,30), 
	(7902, 'FORD', 'ANALYST',7566,'1991-12-3',3000.00, NULL,20), 
	(7934,'MILLER','CLERK',7782,'1992-1-23', 1300.00, NULL,10);
	
	SELECT * FROM emp;

-- 工资级别表
CREATE TABLE salgrade
(
grade MEDIUMINT UNSIGNED NOT NULL DEFAULT 0,
losal DECIMAL(17,2)  NOT NULL,
hisal DECIMAL(17,2)  NOT NULL
);

#测试数据
INSERT INTO salgrade VALUES (1,700,1200);
INSERT INTO salgrade VALUES (2,1201,1400);
INSERT INTO salgrade VALUES (3,1401,2000);
INSERT INTO salgrade VALUES (4,2001,3000);
INSERT INTO salgrade VALUES (5,3001,9999);	

SELECT * FROM salgrade;

# 演示group by+having
GROUP BY 用于对查询结果分组统计，
-- having子句用于限制分组显示结果
-- 如何显示每个部门的平均工资和最高工资
# 按照部门来分组查询
SELECT AVG(sal),MAX(sal),deptno
	FROM emp GROUP BY deptno;
	
-- 显示每个部门的每种岗位的平均工资和最低工资
-- 1.显示每个部门的平均工资和最低工资
-- 2.显示每个部门的每种岗位的平均工资和最低工资
SELECT AVG(sal),MIN(sal),deptno,job
	FROM emp GROUP BY deptno,job;
	
-- 显示平均工资低于2000的部门号和他的平均工资
-- 1.显示各个部门的平均工资和部门号和它的平均工资
-- 2.在1的结果基础上，进行过滤，保留 avg(sal)<2000
-- 3.使用别名进行过滤
SELECT AVG(sal),deptno
	FROM emp GROUP BY deptno
	HAVING AVG(sal)<2000;
	
-- 使用别名 
SELECT AVG(sal) AS avg_sal,deptno
	FROM emp GROUP BY deptno
		HAVING avg_sal<2000;

```





### 字符串相关函数

```sql
-- 演示字符串相关函数的使用
-- CHARSET(str) 返回字串字符集
SELECT CHARSET(ename) FROM emp;

-- CONCAT(string2 [,....]) 连接字串
SELECT CONCAT(ename,' 工作是',job) FROM emp;

-- INSTR (string,substring )返回substring在string中出现的位置，没有返回0
-- dual 亚元表，系统表 可以作为测试表使用
SELECT INSTR('hanshunping','ping')FROM DUAL;

-- UCASE(string2) 转换成大写
SELECT UCASE(ename)FROM emp;

-- LCASE(string2) 转换成小写
SELECT LCASE(ename) FROM emp;

-- LEFT (string2,length) 从string2中的左边起取length个字符
-- RIGTH(string2,length)
SELECT LEFT(ename,2) FROM emp;

-- LENGTH (string) string 长度[按照字节]
SELECT LENGTH(ename) FROM emp;

-- REPLACE (str,serch_str,replace_str)
-- 在str中用replace_str替换search_str
-- 如果是manager 就替换成经理
SELECT ename,REPLACE(job,'MANAGER','经理') FROM emp;

-- STRCMP(string1,string2) 涿字符比较俩字串大小
SELECT STRCMP('hsp','Jsp')FROM DUAL;

-- SUBSTRING(str,position [length]) 
-- 从str的position开始【从1开始计算】，取length个字符
-- 从ename列的第一个位置 开始取出2个字符
SELECT SUBSTRING(ename,1,2)FROM emp;

-- LTRTM(string2)RTRIM(string2) TRIM(string)
-- 去除前段空格后端空格
SELECT LTRIM(' 韩顺平教育')FROM DUAL;
SELECT RTRIM('韩顺平教育 ')FROM DUAL;
SELECT TRIM(' 韩顺平教育 ')FROM DUAL;


```



### 数学相关函数

```sql
-- 演示数学相关函数

-- ABS(num) 绝对值
SELECT ABS(10) FROM DUAL;

-- BIN(decimal_number)   十进制转二进制
SELECT BIN(10) FROM DUAL;

-- CEILING(number2 )向上取整 ，得到比num2 大的最小整数
SELECT CEILING(1.1) FROM DUAL;

-- CONV(number2,from_base,to base)  进制转化
-- 下面的含义是 8 是十进制的8 转成2进制输出
SELECT CONV(8,10,2) FROM DUAL;

-- 下面的含义是 16 是十六进制的16 转成10进制输出
SELECT CONV(16,16,10) FROM DUAL;

-- FLOOR(number2)    向下取整，得到比 num2小的最大整数
SELECT FLOOR(-1.1) FROM DUAL;

-- FORMAT (number,decial_places) 保留小数位数,四舍五入
SELECT FORMAT(78.125458,2) FROM DUAL;

-- HEX(DeciamlNumber )转十六进制
SELECT HEX(5) FROM DUAL;

-- LEAST(number,number2[...]) 求最小值
SELECT LEAST(0,1,-10,4) FROM DUAL;

-- MOD(nuerator,denominator)  求余
SELECT MOD(10,3) FROM DUAL;

-- RAND([seed]) RAND([seed])  返回随机数 其范围为 0<=v<=1.0
-- 如果使用rand() 每次返回不同的随机数，在0<=v<=1.0,如果seed不变，该随机数也不变了
SELECT RAND() FROM DUAL;


```



### 日期事件相关函数

```sql
-- 日期时间相关函数

-- CURRENT_DATE( )        当前日期
SELECT CURRENT_DATE FROM DUAL;

-- CURRENT_TIME( )        当前时间
SELECT CURRENT_TIME FROM DUAL;

-- CURRENT_TIMESTAMP( )   当前时间戳
SELECT CURRENT_TIMESTAMP() FROM DUAL;

-- 创建测试表 信息表
CREATE TABLE mes(
	id INT,
	content VARCHAR(30),
	send_time DATETIME);


-- 添加一条记录
INSERT INTO mes
	VALUES(1,'北京新闻',CURRENT_TIMESTAMP());

INSERT INTO mes VALUE(2,'上海新闻',NOW());
INSERT INTO mes VALUE(3,'广州新闻','2020-11-11');
	

SELECT *FROM mes;
SELECT NOW() FROM DUAL;

-- 上应用案例
-- 显示所有新闻信息，发布日期只显示日期，不用显示时间
SELECT id,content,DATE(send_time)
	FROM mes;
	
-- 请查询在10分钟内发布的帖子
SELECT  * FROM mes -- 发送时间加上10分钟 开是否大于或等于当前时间
	WHERE DATE_ADD(send_time,INTERVAL 10 MINUTE)>=NOW()
	
SELECT * FROM mes --  现在时间减去10分钟 看是否在发送时间之前
	WHERE send_time>= DATE_SUB(NOW(),INTERVAL 10 MINUTE)
	

-- 请在mysql的sql语句中求出 2011-11-11 和 1990-1-1 相差多少天
SELECT DATEDIFF('2011-11-11','1990-01-01') FROM DUAL;

-- 请用mysql的 sql语句求出你活了多少天？[练习]
SELECT DATEDIFF('2021-08-11','2001-01-01') FROM DUAL

-- 如果你能活80岁，求出你还能活多少天
-- year可以是年月日时分秒都行 ，'2001-01-05'可以是date,datetime timestamp
SELECT DATEDIFF(DATE_ADD('2001-01-05',INTERVAL 80 YEAR),NOW()) FROM DUAL

SELECT TIMEDIFF('10:11:11','06:10:10') FROM DUAL;

-- YEAR|Month|DAY|DATE|(datetime)
SELECT YEAR(NOW()) FROM DUAL;
SELECT MONTH(NOW()) FROM DUAL;
SELECT DAY(NOW()) FROM DUAL;
SELECT YEAR('2013-11-10') FROM DUAL;

-- unix_timestamp()1970-1-1 到现在的秒数
SELECT UNIX_TIMESTAMP()/(24*3600*365) FROM DUAL;

-- FROM_UNIXTIME()  可以把一个unix_timestamp秒数，转成指定格式的日期

-- %Y-%m-%d 格式是规定好的，表示年月日
SELECT FROM_UNIXTIME(1618483484,'%Y-%m-%d') FROM DUAL;
SELECT FROM_UNIXTIME(1618493484,'%Y-%m-%d %H:%i:%s')FROM DUAL;
-- 意义：在开发中，可以存放一个整数，然后表示时间，通过FROM_UNIXTIME转换


```



### 加密和系统函数

```sql
-- 演示加密函数和系统函数

-- USER()  查询用户
-- 可以查看登录到mysql的有哪些用户，以及登录的ip
SELECT USER() FROM DUAL; -- 用户@ip地址

-- DATABASE() 查询当前使用数据库名称
SELECT DATABASE();

-- MD5(str)   为字符串算出一个 MD5 32的字符串，常用(用户密码)加密
-- root 密码是 hsp -> 加密md5 ->在数据库中存放的是加密后的密码
SELECT MD5('hsp') FROM DUAL;
SELECT LENGTH(MD5('hsp')) FROM DUAL;

-- 演示用户表，存放密码时，是md5
CREATE TABLE users(
	id INT,
	NAME VARCHAR(32) NOT NULL DEFAULT'',
	pwd CHAR(32) NOT NULL DEFAULT '');

INSERT INTO users
	VALUES(100,'冯荣旭',MD5('hsp'));
	
SELECT *FROM users;

SELECT * FROM users -- SQL 注入问题
	WHERE `name`='冯荣旭' AND pwd =MD5('hsp')
	
-- PASSWORD(str) -- 加密函数，MySQL数据库的用户密码就是 PASSWORD函数加密

SELECT PASSWORD('hsp') FROM DUAL;

-- select * from mysql.user \G 从原文密码str 计算并返回密码字符串
-- 通常用于对mysql数据库的用户密码加密
-- mysql.user 表示  数据库.表
SELECT *FROM mysql.user


```





### 流程控制函数

```sql
 #演示流程控制语句
 
 # IF(expr1,expr2,expr3) 如果expr1为True,则返回expr2 否则返回expr3
 SELECT IF(TRUE,'北京','上海') FROM DUAL;
 
 #IFNULL(expr1,expr2)  如果expr1不为空null,则返回expr1,否则返回expr2
 SELECT IFNULL('jack','韩顺平教育') FROM DUAL;
 
 # SELECT CASE WHEN expr1 THEN expr2 WHEN expr3 THEN expr4 ELSE expr5 END;[类似多重分支]
 # 如果expr1 为TRUE,则返回expr2,如果 expr2 为t，返回 expr4,否则返回 expr5
 SELECT CASE 
	WHEN TRUE THEN 'jack' 
	WHEN FALSE THEN 'tom' 
	ELSE 'mary' END

-- 1.查询emp 表，如果comm 是null 则显示0.0
--  老师说明,判断是否为null 要使用 is null,判断不为空 使用 is not
SELECT ename,IF (comm IS NULL,0.0,comm)
	FROM emp;
SELECT ename,IFNULL(comm,0.0)
	FROM emp;
	
-- 2.如果emp 表的 job 是 CLERK 则显示职员，如果是 MANAGER 则显示经理
--   如果是SALESMAN 则显示 销售人员，其他正常显示
SELECT ename,(
	SELECT CASE
	WHEN job ='CLERK' THEN '职员' 
	WHEN job ='manager' THEN '经理' 
	WHEN job ='SALESMAN'THEN'销售人员'
	ELSE job END) AS 'job',job
	FROM emp;

```





## MySQL-多表查询

### 多表查询

> ```sh
> # 多表查询是指基于两个和两个以上的表查询.在实际应用中,查询单个表可能不能满足你的需求.
> ```



```sql
-- 多表查询
-- 显示雇员名，雇员工资及所在部门的名字【笛卡尔积】
SELECT * FROM salgrade
SELECT * FROM emp
SELECT * FROM dept
/*
   分析
   1.雇员名，雇员工资 来自 emp表
   2.部门的名字，来自dept表
   (1)从第一张表中，取出一行和第二张表的每一行进行组合，返回结果[含有两张表的所有列]
   (2)一共返回的记录数第一张表行数*第二张表的行数
   (3)这样多表查询默认处理返回的结果，称为【笛卡尔积】
   (4)解决这个多表的关键就是要写出正确的过滤条件 where
   3.当我们需要指定显示某个表的列时，需要 表.列名
   
   
*/
SELECT  ename,sal,dname,emp.deptno 
	FROM emp,dept
	WHERE emp.deptno=dept.deptno
-- 小技巧：多表查询的条件下不能少于 表的个数-1，否则会出现笛卡尔积

-- 如何显示部门号为10的部门名、员工名和工资
SELECT  ename,sal,dname,emp.deptno 
	FROM emp,dept
	WHERE emp.deptno=dept.deptno AND emp.deptno=10
	
-- 显示各个员工的姓名，工资，及其工资的级别
SELECT ename,sal,grade
	FROM emp,salgrade
	WHERE sal >= losal AND sal<=hisal;
	
-- 显示雇员名，雇员工资及所在部门的名字，并按部门排序
  SELECT ename,sal,dname,emp.deptno
		FROM emp,dept
		WHERE emp.deptno=dept.deptno
		ORDER BY emp.deptno DESC

```



- **自连接**

> ```sh
> # 自连接是指在同一张表的连接查询[将同一张表看做两张表].
> ```



```sql
-- 多表查询的 自连接

-- 思考题：显示公司员工名字和他的上级名字
SELECT *FROM emp
-- 分析：员工名字 在emp,上级的名字 emp
-- 员工和上级是通过 emp表的 mgr 列并联
-- 小结：1.把同一张表当做两张表来使用
--       2.需要给表取别名[表别名]
--       3.列名不明确，可以指定列的别名，列名 AS 列的别名
SELECT worker.ename AS '职员名',boss.ename AS '上级名'
	FROM emp worker,emp boss
	WHERE worker.mgr=boss.empno;

```



### MySQL表子查询

> ```sh
> # 子查询是指嵌入在其它 SQL 语句中的 SELETE 语句,也叫嵌套查询
> ```



```sh
# 单行子查询
# 单行子查询是指只返回一行数据的子查询语句
# 多行子查询
# 多行子查询指返回多行数据的子查询 ,使用关键字 in
```



```sql
-- 子查询的演示
-- 请思考：如何显示与SMITH 同一部门的所有员工

/*
  1.先查询到SMITH的部门号得到
  2.把上面的select语句当做一个子查询来使用
*/
SELECT deptno
	FROM emp
	WHERE ename='SMITH'
	
SELECT *
	FROM emp
	WHERE deptno =(
		SELECT deptno
		FROM emp
		WHERE ename='SMITH'
	)
-- 课堂练习：如何查询和部门10的工作相同的雇员的
--   名字、岗位、工资、部门号、但是不含10部门自己的

/*
   1.查询到10号部门有哪些工作
   2.把上面查询的结果当做子查询
*/
SELECT DISTINCT job 
	FROM emp
	WHERE deptno=10;
	
-- 下面语句完整
SELECT ename,job,sal,deptno
	FROM emp
	WHERE job IN (
		SELECT DISTINCT job 
		FROM emp
		WHERE deptno=10
	)AND deptno !=10

```



```sql
-- all 和 any 的使用

-- 请思考：显示工资比部门30的所有员工的工资高的员工的姓名、工资和部门号

SELECT ename,sal,deptno
	FROM emp
	WHERE sal> ALL(
		SELECT sal
		FROM emp
		WHERE deptno =30)
		
-- 可以这样写
SELECT ename,sal,deptno
	FROM emp
	WHERE sal> (
		SELECT MAX(sal)
		FROM emp
		WHERE deptno =30)
		

-- 请思考：如何显示工资比部门30的其中一个员工的工资高的员工的姓名、工资和部门号

SELECT ename,sal,deptno
	FROM emp
	WHERE sal> ANY(
		SELECT sal
		FROM emp
		WHERE deptno =30)
-- 可以这样写
SELECT ename,sal,deptno
	FROM emp
	WHERE sal> (
		SELECT MIN(sal)
		FROM emp
		WHERE deptno =30)

```



- **多列子查询**

> ```sh
> # 多列子查询是指查询返回多个列数据的子查询语句。
> ```



```sql
-- 多列子查询
-- 请思考如何查询与allen的部门和岗位完全相同的所有雇员(并且不含smith本人)
-- (字段1，字段2...)=(select 字段1 ，字段2 from...)

-- 分析：1.得到allen的部门和岗位

SELECT deptno,job
	FROM emp
	WHERE ename='ALLEN'
	
-- 分析：2.把上面的查询当做子查询来使用，并且使用多列子查询的语法进行匹配
SELECT * 	
	FROM emp
	WHERE (deptno,job)=(
		SELECT deptno,job
		FROM emp
		WHERE ename='ALLEN'
		)AND ename != 'ALLEN'
		
-- 请查询和宋江同学数学，英语，语文
-- 成绩完全相同的同学		
SELECT *
	FROM student
	WHERE (chinese,english,math)=(	
		SELECT chinese,english,math 
		FROM student
		WHERE `name`='宋江');

```



- **在 from 子句中使用子查询**

```sql
-- 子查询 练习

-- 请思考：查找每个部门工资高于本部门平均工资的人的资料
-- 这里要用到数据查询的小技巧，把一个子查询当做一个临时表使用

-- 1.先得到每个部门的 部门号和对应的平均工资
SELECT deptno,AVG(sal)
	FROM emp
	GROUP BY deptno
	
	
-- 2.把上面的结果当做子查询，和emp进行多表查询
SELECT  ename,sal,temp.avg_sal,emp.deptno
	FROM emp,(
		SELECT deptno,AVG(sal) AS avg_sal
		FROM emp
		GROUP BY deptno
		)temp
		WHERE emp.deptno=temp.deptno AND emp.sal>temp.avg_sal
		
-- 查找每个部门工资最高的人详细资料
SELECT  ename,sal,temp.max_sal,emp.deptno
	FROM emp,(
		SELECT deptno,MAX(sal) AS max_sal
		FROM emp
		GROUP BY deptno
		)temp
		WHERE emp.deptno=temp.deptno AND emp.sal=temp.max_sal
		
-- 查询每个部门的信息(包括：部门名，编号，地址)和人员数量
-- 1.部门名，编号，地址
-- 2.各个部门的数量
SELECT COUNT(*),deptno 
	FROM emp
	GROUP BY deptno
	
	
	
SELECT dname,dept.deptno ,loc,tmp.per_num AS '人数'
	FROM dept,(
		SELECT COUNT(*) AS per_num,deptno 
		FROM emp
		GROUP BY deptno
		) tmp
		WHERE tmp.deptno=dept.deptno
		
-- 还有一种写法 表.* 表示将该所有列都显示出来
-- 在多表查询中，当多个表的列不重复时，才可以直接写列名

SELECT tmp.* ,dname,loc
	FROM dept,(
		SELECT COUNT(*) AS per_num,deptno
		FROM emp
		GROUP BY deptno
		) tmp
		WHERE tmp.deptno=dept.deptno	


```



### 表复制

- **自我复制数据(蠕虫复制)**

```sql
-- 表的复制
-- 为了对某个sql语句进行效率测试，我们需要海量数据时，可以使用此法为表创建海量数据

CREATE TABLE my_tab01(
	id INT,
	`name` VARCHAR(32),
	sal DOUBLE,
	job VARCHAR(32),
	deptno INT);
DESC my_tab01
SELECT * FROM my_tab01

-- 演示如何自我复制
-- 1.先把emp表的记录复制到my_tab01
INSERT INTO my_tab01
	(id,`name`,sal,job,deptno)
	SELECT empno,ename,sal,job,deptno FROM emp;
	
-- 2.自我复制
INSERT INTO my_tab01
	SELECT * FROM my_tab01;
	
-- 如何删除一张表重复记录
-- 1.先创建一张表 my_tab02,
-- 2.让 my_tab02 有重复的记录

CREATE TABLE my_tab02 LIKE emp;-- 这个语句把emp表的结构(列)，复制到my_tab02

DESC my_tab02; 

INSERT INTO my_tab02
	SELECT * FROM emp;
	
SELECT * FROM my_tab02;
-- 3.考虑去重
/*
	(1)先创建一张临时表，my_tmp,该表的结构和 my_tab02一样
	(2)把my_tmp的记录通过 distinct关键字 处理后，把记录复制到my_tmp
	(3)清除掉my_tab02 记录
	(4)把 my_tmp 表的记录复制到my_tab02
	(5)drop 掉 临时表my_tmp
*/
DROP TABLE my_tmp
-- (1)先创建一张临时表，my_tmp,该表的结构和 my_tab02一样
CREATE TABLE my_tmp LIKE my_tab02
-- (2)把my_tmp的记录通过 distinct关键字 处理后，把记录复制到my_tmp
INSERT INTO my_tmp
	SELECT DISTINCT * FROM my_tab02
-- (3)清除掉my_tab02 记录
DELETE FROM my_tab02
-- (4)把 my_tmp 表的记录复制到my_tab02
INSERT INTO my_tab02
	SELECT * FROM my_tmp
-- (5)drop 掉 临时表my_tmp
DROP TABLE my_tmp

SELECT * FROM my_tab02


```



### 合并查询

- **介绍**

```sh
# 有时在实际应用中，为了合并多个select语句的结果，可以使用集合操作符号union , union all

# nuion all
# 该操作符用于取得两个结果集的并集。当使用该操作符时，不会取消重复行。
# union
# 该操作赋与union all相似,但是会自动去掉结果集中重复行。
```

```sql
-- 合并查询

SELECT ename,sal,job FROM emp WHERE sal>2500; -- 5

SELECT ename,sal,job FROM emp WHERE job='MANAGER'; -- 3

-- union all 就是将两个查询结果合并，不会去重
SELECT ename,sal,job FROM emp WHERE sal>2500
UNION ALL
SELECT ename,sal,job FROM emp WHERE job='MANAGER';-- 8

-- union  就是两个查询结果合并，会去重，不会出现同步记录
SELECT ename,sal,job FROM emp WHERE sal>2500
UNION 
SELECT ename,sal,job FROM emp WHERE job='MANAGER';-- 6

```



- **外连接**

1. 左外连接(如果左侧的表完全显示我们就说是左外连接) select...from 表1 left join 表2 on条件 [表1：左表 表2：右表]
2. 右外连接(如果右侧的表完全显示我们就说是右外连接) select...from 表1 rigth join 表2 on条件 [表1：左表 表2：右表]



```sql
-- 外连接
-- 比如:列出部门名称和这些部门的员工名称和工作，同时要求 显示出那些没有员工的部门

-- 使用我们学习过的多表查询的sql,看看效果

SELECT dname,ename,job 
	FROM emp,dept
	WHERE emp.deptno=dept.deptno
	ORDER BY dname
	
-- 先创建 stu
CREATE TABLE stu(
	id INT,
	`name` VARCHAR(32));
INSERT INTO stu VALUES(1,'Jack'),(2,'tom'),(3,'kity'),(4,'nono');
SELECT * FROM stu;

-- 创建exam
CREATE TABLE exam(
	id INT,
	grade INT);
INSERT INTO exam VALUES(1,56),(2,76),(11,8);
SELECT * FROM exam;


-- 使用左连接
-- (显示所有人的成绩，如果没有成绩，也要显示该人的姓名和id号，成绩显示为空)
SELECT `name`,stu.id,grade
	FROM stu,exam
	WHERE stu.id=exam.id;
	
-- 改成左外连接
SELECT `name`,stu.id,grade
	FROM stu LEFT JOIN exam
	ON stu.id=exam.id;
	
-- 使用右外连接(显示所有人的成绩，如果没有名字匹配，显示为空)
-- 即：右边的表(exam)和左表没有匹配的记录，也会把右表的记录显示出来
SELECT `name`,stu.id,grade
	FROM stu RIGHT JOIN exam
	ON stu.id=exam.id;

```





## MySQL-约束与自增长

### MySQL约束

- **基本介绍**

```sh
# 约束用于确保数据库的数据满足特定的商业规则。在mysql中，约束包括: 
# not null、unique,primary key,foreign key,和check五种。
```



### PRIMART KEY(主键)-基本使用

- **细节说明**

1. primary key不能重复而且不能为null。
2. 一张表最多只能有一个主键,但可以是复合主键主键的指定方式有两种
3. 直接在字段名后指定:字段名primakry key在表定义最后写primary key(列名);
4. 使用desc表名，可以看到primary key的情况.
5. 在实际开发中，每个表往往都会设计一个主键.



```sql
-- 主键使用


-- id name email
CREATE TABLE t17(
	id INT PRIMARY KEY,-- 表示id列是主键
	`name` VARCHAR(32),
	email VARCHAR(32));
	
-- 主键列的值是不可以为 空
INSERT INTO t17
	VALUES(1,'jack','jack@sohu.com')
INSERT INTO t17
	VALUES(2,'tom','tom@sohu.com')
INSERT INTO t17(
	VALUES(1,'frx','frx@sohu.com')
		
SELECT * FROM t17

-- 主键使用的细节讨论
-- primary key 不能重复且不能为null
INSERT INTO t17(
	VALUES(NULL,'frx','frx@sohu.com')
	
-- 一张表最多只能有一个主键，但可以是复合主键(比如 id+name)
-- 演示复合主键
CREATE TABLE t18(
	id INT ,
	`name` VARCHAR(32) ,
	email VARCHAR(32),
	PRIMARY KEY(id,`name`));-- 这里就是复合主键

INSERT INTO t18
	VALUES(1,'tom','tom@sohu.com');
INSERT INTO t18
	VALUES(1,'jack','jack@sohu.com');
SELECT * FROM t18

-- 主键的指定方式 有两种
-- 直接在字段名后指定：字段名 primary key
CREATE TABLE t19(
	id INT,
	`name` VARCHAR(32) PRIMARY KEY,
	email VARCHAR(32));
	
-- 在表定义最后写 primary key(列名);
CREATE TABLE t20(
	id INT,
	`name` VARCHAR(32) ,
	email VARCHAR(32),
	PRIMARY KEY(`name`));
	
-- 使用desc 表名，可以看到primary key的情况下
DESC t20 -- 查看t20 表的结果，显示约束情况
DESC t18

```



### NOT NULL和UNIQUE(唯一)

```sh
# NOT NULL 非空，当插入数据时，必须提供数据
# UNIQUE 唯一，该列不可重复，若没有指定 NOT NULL，则可以有多个 NULL
```



```sql
-- unique的使用

CREATE TABLE t21(
	id INT UNIQUE,-- 表示id列是不可以重复的
	`name` VARCHAR(32),
	email VARCHAR(32)
	);
	
INSERT INTO t21
	VALUES(1,'jack','jack@sohu.com');
INSERT INTO t21
	VALUES(1,'tom','tom@sohu.com');	
	
-- unique的使用细节
-- 1.如果没有指定 not null,则unique字段可以有多个null
-- 如果一个列(字段)，是unique not null 使用效果类似 primary key
INSERT INTO t21
	VALUES(NULL,'tom','tom@sohu.com');
-- 2.一张表可以有多个unique字段
CREATE TABLE t22(
	id INT UNIQUE,-- 表示id列是不可以重复的
	`name` VARCHAR(32) UNIQUE, -- 表示name不可以重复
	email VARCHAR(32)
	);
DESC t22

```



### FOREIGN KEY （外键）

![](image/Snipaste_2022-11-24_19-11-26.png)



![](image/Snipaste_2022-11-24_19-11-52.png)

![](image/Snipaste_2022-11-24_19-12-12.png)



```sql
-- 外键演示

-- 创建 主表 my_class
CREATE TABLE my_class(
	id INT PRIMARY KEY, -- 班级编号
	`name` VARCHAR(32) NOT NULL DEFAULT '');
	
-- 创建 从表 my_stu
CREATE TABLE my_stu(
	id INT PRIMARY KEY,-- 学生编号
	`name` VARCHAR(32) NOT NULL DEFAULT '',
	class_id INT, -- 学生所在班级编号 
	-- 下面指定外键关系
	FOREIGN KEY (class_id) REFERENCES my_class(id))
	
-- 测试数据
INSERT INTO my_class
	VALUES(100,'java'),(200,'web');
SELECT * FROM my_class	

INSERT INTO my_stu
	VALUES(1,'tom',100);
INSERT INTO my_stu
	VALUES(2,'jack',200);
INSERT INTO my_stu
	VALUES(4,'marry',NULL);	-- 可以。外键没有写 not null
INSERT INTO my_stu
	VALUES(3,'frx',300);-- 添加失败 300号班机不存在
SELECT * FROM my_stu
SELECT * FROM my_class

-- 一旦建立主外键关系，数据不能随意删除了
DELETE FROM my_class
	WHERE id=100 -- 没有任何一条记录指向 主表100,就可以删去

```



### check

```sql
-- 演示check的使用

-- mysql5.7目前还不支持check,只做语法校验，但不会生效

-- 测试
CREATE TABLE t23(
	id INT PRIMARY KEY,
	`name` VARCHAR(32),
	sex VARCHAR(6) CHECK (sex IN('man','woman')),
	sal DOUBLE CHECK(sal>1000 AND sal<2000));
	
-- 添加数据
INSERT INTO t23
	VALUES(1,'jack','mid',1);
SELECT * FROM t23

```



### 自增长



- **使用细节**

1. 一般来说自增长是和primary key配合使用的
2. 自增长也可以单独使用[但是需要配合一个unique]
3. 自增长修饰的字段为整数型的(虽然小数也可以但是非常非常少这样使用)
4. 自增长默认从1开始,你也可以通过如下命令修改 [altertable表名auto increment=新的开始值] ;
5. 如果你添加数据时，给自增长字段(列)指定的有值，则以指定的值为准,如果指定了自增长，一般来说，就按照自增长的规则来添加数据



```sql
-- 演示自增长的使用
-- 创建表
CREATE TABLE t24(
	id INT PRIMARY KEY AUTO_INCREMENT,
	email VARCHAR(32) NOT NULL DEFAULT '',
	`name` VARCHAR(32) NOT NULL DEFAULT '');
DESC t24

-- 测试自增长的使用
INSERT INTO t24
	VALUES(NULL,'jack@qq.com','jack')
	
INSERT INTO t24
	(email,`name`)VALUES('jack@qq.com','jack');

SELECT * FROM t24

-- 修改默认的自增长开始值
ALTER TABLE t25 AUTO_INCREMENT =100
CREATE TABLE t25(
	id INT PRIMARY KEY AUTO_INCREMENT,
	email VARCHAR(32) NOT NULL DEFAULT '',
	`name` VARCHAR(32) NOT NULL DEFAULT '');
INSERT INTO t25
	VALUES(NULL,'jack@qq.com','jack');
INSERT INTO t25
	VALUES(666,'jack@qq.com','jack');
INSERT INTO t25
	VALUES(NULL,'mary@qq.com','mary');
SELECT * FROM t25;

-- 如果指定了自增长，一般来说，就按照自增长的规则来添加数据

```





## MySQL-索引与事务

### MySQL索引（index）

#### 索引快速入门

1. 说起提高数据库性能,索引是最物美价廉的东西了。不用加内存，不用改程序,不用调sql，查询速度就可能提高百倍干倍。
2. 没有建立索引的字段，查询起来依旧很慢



#### 索引的原理

![](image/Snipaste_2022-11-24_19-18-40.png)

![](image/Snipaste_2022-11-24_19-19-26.png)

1. 没有索引为什么会慢?因为全表扫描.
2. 使用索引为什么会快?形成一个索引的数据结构，比如二叉树



- **索引的代价**

1. 磁盘占用
2. 对dml(update delete insert)语句的效率影响



#### 索引的类型

![](image/Snipaste_2022-11-24_19-23-18.png)





#### 索引的使用

- **添加索引**

```sql
-- 1.添加PRIMARY KEY（主键索引） 
ALTER TABLE `table_name` ADD PRIMARY KEY ( `column` ) 
-- 2.添加UNIQUE(唯一索引) 
ALTER TABLE `table_name` ADD UNIQUE ( 
`column` 
) 
-- 3.添加INDEX(普通索引) 
ALTER TABLE `table_name` ADD INDEX index_name ( `column` ) 
-- 4.添加FULLTEXT(全文索引) 
ALTER TABLE `table_name` ADD FULLTEXT ( `column`) 
-- 5.添加多列索引 
ALTER TABLE `table_name` ADD INDEX index_name ( `column1`, `column2`, `column3` )
```



- **删除索引**

```sql
-- 删除索引
DROP INDEX index_name ON `table_name`
ALTER TABLE `table_name` DROP INDEX index_name;

-- 删除主键索引
ALTER table `table_name` DROP PRIMARY KEY;
```



- **查询索引**

```sql
SHOW INDEX DROM table_name;

SHOW INDEXES DROM table_name;

SHOW KEYS FROM table_name;

DESC table_name
```



```sql
-- 演示mysql的索引的使用
-- 创建索引
CREATE TABLE t25(	
	id INT,
	`name` VARCHAR(32));
	
	
-- 查询表是否有索引
SHOW INDEXES FROM t25;

-- 添加索引
-- 添加唯一索引
CREATE UNIQUE INDEX id_index ON t25(id);

-- 添加普通索引方式1
CREATE INDEX id_index ON t25(id);

-- 如何选择
-- 1.如果某列的值，是不会重复的，则优先考虑使用unique索引,否则使用普通索引

-- 添加普通索引方式2
ALTER TABLE t25 ADD INDEX id_index(id)

-- 添加主键索引
CREATE TABLE t26(	
	id INT ,-- 1. primary key
	`name` VARCHAR(32));
-- 2.
ALTER TABLE t26 ADD PRIMARY KEY(id)

SELECT * FROM t26

-- 删除索引
SELECT * FROM t25
DROP INDEX id_index ON t25


-- 删除主键索引
ALTER TABLE t26 DROP PRIMARY KEY


-- 修改索引，先删除，在添加新的索引
-- 查询索引
-- 1.方式
SHOW INDEX FROM t25
-- 2.方式
SHOW INDEXES FROM t25
-- 3.方式
SHOW KEYS FROM t25
-- 4.方式
DESC t25

-- 创建一张订单表order (id号，商品名,订购人，数量).
-- 要求id号为主键，请使用2种方式来创建主键.
-- (提示:为练习方便，可以是order1 , order2 )
CREATE TABLE ORDER
	( id INT PRIMARY KEY,
	`goods_name` VARCHAR(32),
	person VARCHAR(32),
	num INT);-- 方式一
	
ALTER TABLE ORDER ADD PRIMARY KEY(id) -- 方式二

-- 创建一张特价菜谱表menu(id号，菜谱名,厨师，点餐人身份证，价格).
-- 要求id号为主键，点餐人身份证是unique请使用两种方式
-- 来创建unique.(提示:为练习方便，可以是menu1 , menu2

CREATE TABLE menu(
	id INT,
	`name` VARCHAR(32),
	 厨师 VARCHAR(32),
	身份证 CHAR(18) UNIQUE ,
	price DOUBLE); -- 方式一
CREATE UNIQUE INDEX 身份证 ON menu (身份证)
SHOW INDEX FROM menu


-- 创建一张运动员表sportman (id号，名字，特长).
-- 要求id号为主键，名字为普通索引，
-- 请使用2种方式来创建索引
-- (提示:为练习方便，可以是不同表名sportman1 , sportman2

CREATE TABLE sportman(
	id INT PRIMARY KEY,
	`name` VARCHAR(32),
	hobby VARCHAR(32));
	
CREATE INDEX name_index ON sportman(NAME) -- 方式一
ALTER TABLE sportman ADD INDEX name_index(NAME)-- 方式二

```



#### 哪些列适合使用索引

1. 较频繁的作为查询条件字段应该创建索引
2. 唯一性太差的字段不适合单独创建索引
3. 更新非常频繁的字段不适合创建索引
4. 不会出现在WHERE子句中字段不应该创建索引





### MySQL事务

![](image/Snipaste_2022-11-24_19-36-14.png)



#### 什么是事务

```sh
# 事务用于保证数据的一致性,它由一组相关的dml语句组成,该组的dml语句要么全部成功，要么全部失败。如:转账就要用事务来处理,用以保证数据的一致性
```



#### 事务和锁

```sh
# 当执行事务操作时（DML语句），MySQL会在表上加锁，防止其他用户更改表的数据
```



- **基本操作**

```sql
-- 开始一个事务
START TRANSACTION;

-- 设置一个保存点
SAVEPOINT save_point_name;

-- 回退事务
ROLLBACK TO save_ponit_name;

-- 回退全部事务
ROLLBACK;

-- 提交事务，所有操作生效，不能回退
COMMIT;
```



```sql
-- 事务的一个重要的概念和具体操作
-- 演示
-- 1.创建一张测试表
CREATE TABLE t27(
	id INT,
	`name` VARCHAR(32));

-- 2.开始事务
START TRANSACTION
-- 3.设置保存点
SAVEPOINT a
-- 执行dml操作
INSERT INTO t27 VALUES(100,'tom');
SELECT * FROM t27;

SAVEPOINT b
-- 执行dml操作
INSERT INTO t27 VALUES(200,'jack');

-- 回退到b
ROLLBACK TO b
-- 继续回退 a
ROLLBACK TO a
-- 如果这样，表示直接回退到事务开始的状态
ROLLBACK
COMMIT 
```



#### 回退事务

在介绍回退事务前，先介绍一下保存点(savepoint).保存点是事务中的点.用于取消部分事务，当结束事务时(commit),会自动的删除该事务所定义的所有保存点.当执行回退事务时，通过指定保存点可以回退到指定的点。



#### 提交事务

使用commit语句可以提交事务.当执行了commit语句子后,会确认事务的变化、结金事除徨专野放锁数据生效。当使用commit语句结束事务子后，其它会话[其他连接]将可以查着到事务变化后的新数据[所有数据就正式生效.]



```sql
-- 讨论事务细节
-- 1.如果不开始事务，默认情况下，dml操作是自动提交的，不能回滚
INSERT	INTO t27 VALUES(300,'milan'); -- 自动提交commit

SELECT * FROM t27


-- 2.如果开始一个事务，你没有创建保存点.你可以执行rollback,
-- 默认就是回退到你事务开始的状态.
START	TRANSACTION
INSERT INTO t27 VALUES(400,'king');
INSERT INTO t27 VALUES(500,'scott')
ROLLBACK -- 表示直接回退到事务开始的状态

-- 3.你也可以在这个事务中(还没有提交时),创建多个保存点.
-- 比如: savepoint aaa;执行dml , savepoint bbb;


-- 4.你可以在事务没有提交前，选择回退到哪个保存点.

-- 5.mysql的事务机制需要innodb的存储引擎才可以使用,myisam不支持.
-- 6.开始一个事务start transaction, set autocommit=off;


```



### MySQL事务隔离级别

#### 事务隔离级别介绍

1. 多个连接开启各自事务操作数据库中数据时，数据库系统要负责隔离操作，以保证各个连接在获取数据时的准确性。（通俗解释)
2. 如果不考虑隔离性,可能会引发如下问题:
   - 脏读
   - 不可重复读
   - 幻读

- **介绍**

1. 脏读（dirty read）：当一个事务读取另一个事务尚未提交的改变（update，insert，delete），产生脏读
2. 不可重复读（nonrepeatable read）：同一查询在同一事务中多次进行，由于其他提事务所做的**修改**，每次返回不同的结果集
3. 幻读（phantom read）：同一查询结果在同一事务中多次进行，由于其他提交事务所作的**插入或删除**操作，每次返回不同的结果集



#### 事务隔离级别

| Mysql隔离级别(4种)        | 脏读 | 不可重复读 | 幻读 | 加锁读 |
| ------------------------- | ---- | ---------- | ---- | ------ |
| 读未提交(Read uncommited) | √    | √          | √    | 不加锁 |
| 读已提交(Read commited)   | ×    | √          | √    | 不加锁 |
| 可重复读(Repeatable)      | ×    | ×          | √    | 不加锁 |
| 可串行化(Serializable)    | ×    | ×          | ×    | 加锁   |



### 设置事务隔离级别

```sql
-- 查看当前会话隔离级别
select @@tx_isolation
-- mysql> select @@tx_isolation;
-- +-----------------+
-- | @@tx_isolation  |
-- +-----------------+
-- | REPEATABLE-READ |
-- +-----------------+
-- 1 row in set (0.00 sec)

-- 查看系统当前隔离级别
select @@global.tx_isolation;
-- mysql> select @@global.tx_isolation;
-- +-----------------------+
-- | @@global.tx_isolation |
-- +-----------------------+
-- | REPEATABLE-READ       |
-- +-----------------------+
-- 1 row in set (0.00 sec)

-- 设置当前会话隔离级别
set session transaction isolation level READ COMMITTED;
SET [SESSION | GLOBAL] TRANSACTION ISOLATION LEVEL {READ UNCOMMITTED | READ COMMITTED | REPEATABLE READ | SERIALIZABLE}

# SESSION：表示修改的事务隔离级别将应用于当前 session（当前 cmd 窗口）内的所有事务；
# GLOBAL：表示修改的事务隔离级别将应用于所有 session（全局）中的所有事务，且当前已经存在的 session 不受影响；
```

```sql
-- 演示mysql的事务隔离级别

-- 1.开启两个mysql的控制台
-- 2.查看当前mysql的隔离级别
SELECT @@tx_isolation;

-- mysql> SELECT @@tx_isolation;
-- +-----------------+
-- | @@tx_isolation  |
-- +-----------------+
-- | REPEATABLE-READ |
-- +-----------------+

-- 3.把其中一个控制台的隔离级别设置 Read uncommitted
SET SESSION TRANSACTION ISOLATION LEVEL READ UNCOMMITTED
-- mysql> SELECT @@tx_isolation;
-- +------------------+
-- | @@tx_isolation   |
-- +------------------+
-- | READ-UNCOMMITTED |
-- +------------------+
-- 1 row in set (0.00 sec)

-- 4.创建表
CREATE TABLE `account`(
	id INT,
	`name` VARCHAR(32),
	money INT);
	
	
-- 查看当前会话隔离级别
SELECT @@tx_isolation;
-- 查看系统当前隔离级别
SELECT @@ global.tx_isplation
-- 设置当前会话隔离级别
SET SESSION TRANSACTION ISOLATION LEVEL READ UNCOMMITTED
-- 设置系统当前隔离级别
SET GLOBAL TRANSACTION ISOLATION LEVEL [设置你想设置的级别]

```



- **演示脏读、不可重复读、幻读**

> ```
> 两个窗口，一个窗口进行查询，一个进行 增删改
> ```

**脏读**

```
设置窗口均为 读未提交
set session transaction isolation level READ UNCOMMITTED;
```

![](image/Snipaste_2022-11-24_21-21-28.png)

![](image/Snipaste_2022-11-24_21-22-45.png)



> ```
> 提升隔离级别后，不会出现
> ```



**不可重复读**

![](image/Snipaste_2022-11-24_21-30-47.png)



![](image/Snipaste_2022-11-24_21-31-58.png)



**解决不可重复读  -- 隔离级别设置可重复读**

![](image/Snipaste_2022-11-24_21-35-33.png)



![](image/Snipaste_2022-11-24_21-37-20.png)



**幻读**

> ```
> MySQL的读已提交没有完全解决 幻读问题
> ```

![](image/Snipaste_2022-11-24_21-53-31.png)



![](image/Snipaste_2022-11-24_21-58-48.png)





### MySQL事务 ACID

#### 事务的 ACID 特性

1. 原子性(Atomicity) 原子性是指事务是一个不可分割的工作单位，事务中的操作要么都发生，要么都不发生。
2. 一致性(Consistency) 事务必须使数据库从一个一致性状态变换到另外一个一致性状态
3. 隔离性(lsolation) 事务的隔离性是多个用户并发访问数据库时，数据库为每一个用户开启的事务，不能被其他事务的操作数据所干扰，多个并发事务之间要相互隔离。
4. 持久性(Durability) 持久性是指一个事务一旦被提交，它对数据库中数据的改变就是永久性的，接下来即使数据库发生故障也不应该对其有任何影响





## MySQL-表类型和存储引擎





## MySQL-视图与管理













# MySQL-进阶



## MySQL-存储引擎





## MySQL-索引





## MySQL-SQL优化



## MySQL-视图/存储过程/触发器



## MySQL-锁



## MySQL-InnoDB引擎



## MySQL-管理









# MySQL-运维



## MySQL-日志





## MySQL-主从复制







## MySQL-分库分表





## MySQL-读写分离









