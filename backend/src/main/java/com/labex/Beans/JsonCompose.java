package com.labex.Beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonCompose {
    private Integer compose_type;
    private String compose_name;
    private Integer teacher_id;
    private String clazz_no;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date start_time;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date end_time;
    private List<JsonComposeItem> index_id;

}
