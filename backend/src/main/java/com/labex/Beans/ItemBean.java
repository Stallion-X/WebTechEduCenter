package com.labex.Beans;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@TableName(value = "t_item",autoResultMap = true)
@AllArgsConstructor
@NoArgsConstructor
public class ItemBean {
    @TableId(type= IdType.AUTO)
    private Integer item_id;
    private Integer item_type;
    private Integer question_type;
    @TableField(exist = false)
    private Integer item_index;
    private Integer teacher_id;
    private String title;
    private String content;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<JsonOptions> ops;
    private String answer;
    private Integer full_score;
    private String pic;
    private String attachment;
    @TableField(fill = FieldFill.INSERT)
    private Date fill_time;
}
