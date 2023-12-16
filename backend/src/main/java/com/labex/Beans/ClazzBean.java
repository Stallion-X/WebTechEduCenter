package com.labex.Beans;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("t_clazz")
@AllArgsConstructor
@NoArgsConstructor
public class ClazzBean {
    @TableId
    private String clazz_no;
    private Integer teacher_id;
}
