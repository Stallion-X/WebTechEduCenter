package com.labex.Beans;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName("t_course_clazz")
@AllArgsConstructor
@NoArgsConstructor
public class CourseClazzBean {
    @TableId(type= IdType.AUTO)
    private Integer course_clazz_id;
    private Integer course_id;
    private String clazz_no;
    @TableField(fill = FieldFill.INSERT)
    private Date fill_time;
}
