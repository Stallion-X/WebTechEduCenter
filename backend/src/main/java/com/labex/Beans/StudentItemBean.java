package com.labex.Beans;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName("t_student_item")
@AllArgsConstructor
@NoArgsConstructor
public class StudentItemBean {
    @TableId(type= IdType.AUTO)
    private Integer student_item_id;
    private Integer student_id;
    private Integer item_id;
    @TableField(exist = false)
    private Integer compose_no;
    @TableField(exist = false)
    private Integer compose_type;
    private String content;
    private Integer score;
    @TableField(exist = false)
    private String pic;
    @TableField(exist = false)
    private String attachment;
    @TableField(fill = FieldFill.INSERT)
    private Date fill_time;
}
