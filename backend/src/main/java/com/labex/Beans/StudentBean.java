package com.labex.Beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("t_student")
@AllArgsConstructor
@NoArgsConstructor
public class StudentBean {
    @TableId(type= IdType.AUTO)
    private Integer student_id;
    private String clazz_no;
    private String student_no;
    private String student_name;
    private String student_password;
    private String memo;
}
