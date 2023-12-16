package com.labex.Beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewVO {
    private Integer student_id;
    private Integer item_id;
    private Integer compose_no;
    private String title;
    private String content;
    private String stu_answer;
    private String answer;
    private Boolean isCorrect;
    private Integer score;
    private String item_pic;
    private String item_attachment;
    private String stu_pic;
    private String stu_attachment;
}
