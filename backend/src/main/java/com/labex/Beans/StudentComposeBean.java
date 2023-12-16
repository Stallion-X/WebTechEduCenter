package com.labex.Beans;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName("t_student_compose")
@AllArgsConstructor
@NoArgsConstructor
public class StudentComposeBean {
    @TableId(type= IdType.AUTO)
    private Integer student_compose_id;
    private Integer compose_no;
    @TableField(exist = false)
    private String compose_name;
    @TableField(exist = false)
    private String clazz_no;
    @TableField(exist = false)
    private Date end_time;
    private Integer compose_state;
    private Integer student_id;
    private Integer teacher_id;
    private Integer score_total;
    @TableField(fill = FieldFill.INSERT)
    private Date fill_time;
}
