package com.labex.Beans;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName("t_compose")
@AllArgsConstructor
@NoArgsConstructor
public class ComposeBean {
    @TableId(type = IdType.AUTO)
    private Integer compose_id;
    private Integer compose_no;
    private Integer compose_type;
    private String compose_name;
    @TableField(exist = false)
    private Integer compose_state;
    private Integer teacher_id;
    private String clazz_no;
    private Integer item_id;
    private Integer item_index;
    private Integer full_score;
    private Integer total_time;
    private Date start_time;
    private Date end_time;
    @TableField(fill = FieldFill.INSERT)
    private Date fill_time;
}
