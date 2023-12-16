package com.labex.Beans;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName("t_term")
@AllArgsConstructor
@NoArgsConstructor
public class TermBean {
    @TableId(type= IdType.AUTO)
    private Integer term_id;
    private String term_name;
    @TableField(fill = FieldFill.INSERT)
    private Date fill_time;
}
