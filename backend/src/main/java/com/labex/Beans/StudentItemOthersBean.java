package com.labex.Beans;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("t_student_item_others")
@AllArgsConstructor
@NoArgsConstructor
public class StudentItemOthersBean {
    @TableId(type= IdType.AUTO)
    private Integer others_id;
    private Integer student_item_id;
    private Integer compose_no;
    private Integer compose_type;
    private String pic;
    private String attachment;
}
