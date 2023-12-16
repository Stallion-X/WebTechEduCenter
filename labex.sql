CREATE DATABASE labex;
USE labex;
/*==============================================================*/
/* Table: t_teacher                                             */
/*==============================================================*/
CREATE TABLE t_teacher
(
   teacher_id           INT NOT NULL  COMMENT '' PRIMARY KEY AUTO_INCREMENT,
   teacher_no           VARCHAR(8) NOT NULL UNIQUE  COMMENT '',
   teacher_name         VARCHAR(20) NOT NULL  COMMENT '',
   teacher_password     VARCHAR(200) NOT NULL  COMMENT '',
   memo                 TEXT  COMMENT ''
);

/*==============================================================*/
/* Table: t_clazz                                               */
/*==============================================================*/
CREATE TABLE t_clazz
(
   clazz_no             VARCHAR(6) NOT NULL PRIMARY KEY  COMMENT '',
   teacher_id           INT NOT NULL  COMMENT '',
   FOREIGN KEY (teacher_id)
      REFERENCES t_teacher (teacher_id) ON DELETE CASCADE ON UPDATE CASCADE
);

/*==============================================================*/
/* Table: t_student                                             */
/*==============================================================*/
CREATE TABLE t_student
(
   student_id           INT NOT NULL  COMMENT '' PRIMARY KEY AUTO_INCREMENT,
   student_no           VARCHAR(8) NOT NULL UNIQUE  COMMENT '',
   student_name         VARCHAR(8) NOT NULL  COMMENT '',
   student_password     VARCHAR(200) NOT NULL  COMMENT '',
   clazz_no             VARCHAR(6)  COMMENT '',
   memo                 TEXT  COMMENT '',
   FOREIGN KEY (clazz_no)
      REFERENCES t_clazz (clazz_no) ON DELETE CASCADE ON UPDATE CASCADE
);

/*==============================================================*/
/* Table: t_item                                                */
/*==============================================================*/
CREATE TABLE t_item
(
   item_id              INT NOT NULL  COMMENT '' PRIMARY KEY AUTO_INCREMENT,
   item_type            INT NOT NULL DEFAULT 0  COMMENT '0-作业 1-实验 2-考试',
   question_type        INT NOT NULL DEFAULT 0  COMMENT '0-单选题 1-多选题 2-填空题 3-大题 4-编程题',
   teacher_id           INT NOT NULL  COMMENT '出题人',
   title                TEXT  COMMENT '标题',
   content              TEXT NOT NULL  COMMENT '题干',
   ops                  TEXT  COMMENT '选择/判断题选项',
   answer               TEXT NOT NULL  COMMENT '标准答案',
   full_score           TINYINT  COMMENT '',
   pic                  VARCHAR(500)  COMMENT '',
   attachment           VARCHAR(500)  COMMENT '',
   fill_time            DATETIME  COMMENT '',
   FOREIGN KEY (teacher_id)
      REFERENCES t_teacher (teacher_id) ON DELETE CASCADE ON UPDATE CASCADE
);

/*==============================================================*/
/* Table: t_student_item                                        */
/*==============================================================*/
CREATE TABLE t_student_item
(
   student_item_id      INT NOT NULL  COMMENT '' PRIMARY KEY AUTO_INCREMENT,
   student_id           INT NOT NULL  COMMENT '',
   item_id              INT NOT NULL  COMMENT '',
   content              TEXT  COMMENT '答题内容',
   score                TINYINT NOT NULL  COMMENT '',
   fill_time            DATETIME  COMMENT '',
   FOREIGN KEY (student_id)
      REFERENCES t_student (student_id) ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (item_id)
      REFERENCES t_item (item_id) ON DELETE CASCADE ON UPDATE CASCADE
);

/*==============================================================*/
/* Table: t_student_item_others                                        */
/*==============================================================*/
CREATE TABLE t_student_item_others
(
   others_id            INT NOT NULL  COMMENT '' PRIMARY KEY AUTO_INCREMENT,
   student_item_id      INT NOT NULL UNIQUE  COMMENT '',
   compose_no           INT NOT NULL  COMMENT '做这道题的时候 在哪张卷子里',
   compose_type         INT NOT NULL DEFAULT 0  COMMENT '做这道题的时候 在哪张卷子里',
   pic                  VARCHAR(500)  COMMENT '',
   attachment           VARCHAR(500)  COMMENT '',
   FOREIGN KEY (student_item_id)
      REFERENCES t_student_item (student_item_id) ON DELETE CASCADE ON UPDATE CASCADE
);

/*==============================================================*/
/* Table: t_compose                                              */
/*==============================================================*/
CREATE TABLE t_compose
(
   compose_id           INT NOT NULL PRIMARY KEY AUTO_INCREMENT  COMMENT '',
   compose_no           INT NOT NULL  COMMENT '组卷序号 和组卷类型一起唯一对应一张卷子',
   compose_type         INT NOT NULL DEFAULT 0  COMMENT '0-作业 1-实验 2-考试',
   compose_name         VARCHAR(100) NOT NULL  COMMENT '和组卷序号配对',
   compose_state        INT NOT NULL DEFAULT 0  COMMENT '0-未完成 1-已完成',
   teacher_id           INT NOT NULL  COMMENT '出卷老师',
   clazz_no             VARCHAR(100)  COMMENT '分给哪个班去做',
   item_id              INT NOT NULL  COMMENT '题目ID 一个组卷序号对应多个题目ID',
   item_index           INT  COMMENT '题号 一张卷子中的题号顺序',
   full_score           TINYINT DEFAULT 0  COMMENT '',
   total_time           INT DEFAULT 0  COMMENT '',
   start_time           DATETIME  COMMENT '',
   end_time             DATETIME  COMMENT '',
   fill_time            DATETIME  COMMENT '',
   FOREIGN KEY (teacher_id)
      REFERENCES t_teacher (teacher_id) ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (clazz_no)
      REFERENCES t_clazz (clazz_no) ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (item_id)
      REFERENCES t_item (item_id) ON DELETE CASCADE ON UPDATE CASCADE
);

/*==============================================================*/
/* Table: t_student_compose                                              */
/*==============================================================*/
CREATE TABLE t_student_compose
(
   student_compose_id   INT NOT NULL PRIMARY KEY AUTO_INCREMENT  COMMENT '',
   compose_no           INT NOT NULL  COMMENT '',
   compose_state        INT NOT NULL DEFAULT 0  COMMENT '0-未完成 1-已完成',
   student_id           INT NOT NULL  COMMENT '',
   teacher_id           INT NOT NULL  COMMENT '',
   score_total          TINYINT NOT NULL  COMMENT '',
   fill_time            DATETIME  COMMENT '',
   FOREIGN KEY (student_id)
      REFERENCES t_student (student_id) ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (teacher_id)
      REFERENCES t_teacher (teacher_id) ON DELETE CASCADE ON UPDATE CASCADE
);

/*==============================================================*/
/* Table: t_term                                          */
/*==============================================================*/
CREATE TABLE t_term
(
   term_id            INT NOT NULL PRIMARY KEY AUTO_INCREMENT  COMMENT '',
   term_name          VARCHAR(100) NOT NULL  COMMENT '',
   fill_time          DATETIME  COMMENT ''
);

/*==============================================================*/
/* Table: t_course                                            */
/*==============================================================*/
CREATE TABLE t_course
(
   course_id            INT NOT NULL PRIMARY KEY AUTO_INCREMENT  COMMENT '',
   course_name          VARCHAR(100) NOT NULL  COMMENT '',
   teacher_id           INT NOT NULL  COMMENT '',
   term_id              INT NOT NULL  COMMENT '',
   fill_time            DATETIME  COMMENT '',
   FOREIGN KEY (teacher_id)
      REFERENCES t_teacher (teacher_id) ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (term_id)
      REFERENCES t_term (term_id) ON DELETE CASCADE ON UPDATE CASCADE      
);

/*==============================================================*/
/* Table: t_course_clazz                                            */
/*==============================================================*/
CREATE TABLE t_course_clazz
(
   course_clazz_id      INT NOT NULL PRIMARY KEY AUTO_INCREMENT  COMMENT '',
   course_id            INT NOT NULL  COMMENT '',
   clazz_no             VARCHAR(100) NOT NULL  COMMENT '',
   fill_time            DATETIME  COMMENT '',
   FOREIGN KEY (clazz_no)
      REFERENCES t_clazz (clazz_no) ON DELETE CASCADE ON UPDATE CASCADE
);