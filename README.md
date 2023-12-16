# WebTechEduCenter
A web tech learning platform built by Vue2 + Element, which is also a demo frontend learning project in itself.

前端技术学习平台

# 需求说明
系统中涉及学期、教师、班级、学生、试题、考试、实验、作业等各类实体，相关需求如下：

（1）	每个学期均可开设前端技术课程；

（2）	每名教师负责教授若干个班级；

（3）	教师负责实验、作业、考试的录入；

（4）	每次作业由作业题构成、实验由实验步骤构成、考试由考试题构成；

（5）	作业、实验、考试结束后均能人工或自动批改，实验的实验报告在实验完毕后可自动生成；

（6）	试题可分为选择题、填空题、问答题与编程题，能够自动判分；

（7）	能够按比例计算总评分数并统计平均分、及格人数以及统计图表等；

（8）	增加自选功能，形成自己的特色。

# 数据库设计（指定）
数据库名为labex，数据库中必须包含以下表格，请勿修改其表名、字段名以及字段类型。
**学生表（t_student）**

| Field            | Type        | Null | Key | Default | Extra          |
|------------------|-------------|------|-----|---------|----------------|
| student_id       | int         | NO   | PRI | NULL    | auto_increment |
| student_no       | varchar(8)  | NO   | UNI | NULL    |                |
| student_name     | varchar(20) | NO   |     | NULL    |                |
| student_password | char(32)    | NO   |     | NULL    |                |
| clazz_no         | varchar(6)  | NO   | MUL | NULL    |                |
| memo             | text        | YES  |     | NULL    |                |

**答题表（t_student_item）**

| Field            | Type        | Null | Key | Default | Extra          |
|------------------|-------------|------|-----|---------|----------------|
| student_item_id  | int         | NO   | PRI | NULL    | auto_increment |
| student_id       | int         | NO   | MUL | NULL    |                |
| item_id          | int         | NO   | MUL | NULL    |                |
| content          | text        | YES  |     | NULL    |                |
| score            | tinyint     | NO   |     | NULL    |                |
| fill_time        | datetime    | YES  |     | NULL    |                |

注意：在上面的表格中，Key所在列中的UNI对应于唯一性索引，MUL对应于外键。


# Plans
|  计划   | 是否完成  |
|  :----  | :----:  |
| 学习Vue Router  | ✅ |
| Axios  | ✅ |
| localStorage  | ✅ |
| Echarts  | ✅ |
| Websocket  | ✅ |
| SSE  | ✅ |
| Web Worker  | ✅ |
| SpringBoot+Mybatis+Mybatis-plus  | ✅ |
| 单文件组件  | ✅ |
| 文档模板生成器DocxTemplate  | ✅ |
| 富文本编辑器WangEditor  | ✅ |
| 封装、注册组件  | ✅ |
| WangEditor组件key重复问题  | ➖ |
| WangEditor自定义编辑器  | ➖ |
| 后端部分业务逻辑完善  | ➖ |
| Echarts后端数据  | ➖ |
| 前端axios二次封装重构  | ➖ |
