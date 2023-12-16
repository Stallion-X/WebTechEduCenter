package com.labex.Beans;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName("t_course")
@AllArgsConstructor
@NoArgsConstructor
public class CourseBean {
    @TableId(type= IdType.AUTO)
    private Integer course_id;
    private String course_name;
    private Integer teacher_id;
    private Integer term_id;
    @TableField(fill = FieldFill.INSERT)
    private Date fill_time;
}
