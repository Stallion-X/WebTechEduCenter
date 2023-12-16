package com.labex.Beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("t_teacher")
@AllArgsConstructor
@NoArgsConstructor
public class TeacherBean {
    @TableId(type= IdType.AUTO)
    private Integer teacher_id;
    private String teacher_no;
    private String teacher_name;
    private String teacher_password;
    private String memo;
}
