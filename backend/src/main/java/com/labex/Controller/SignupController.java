package com.labex.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.labex.Beans.InfoBean;
import com.labex.Beans.StudentBean;
import com.labex.Beans.TeacherBean;
import com.labex.Services.Impl.StudentServiceImpl;
import com.labex.Services.Impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin
public class SignupController {
    @Autowired
    StudentServiceImpl studentService;
    @Autowired
    TeacherServiceImpl teacherService;
    @Autowired
    BCryptPasswordEncoder PwdSecurity;
    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("/signup")
    public String signup(
            @RequestParam String username,
            @RequestParam String nickname,
            @RequestParam String password,
            @RequestParam int type
    ) throws JsonProcessingException {
        if (type == 0) {
            StudentBean user=new StudentBean();
            user.setStudent_no(username);
            user.setStudent_password(PwdSecurity.encode(password));
            user.setStudent_name(nickname);
            user.setClazz_no(username.substring(0,6));
            HashMap<String, Object> columnMap = new HashMap<>();
            columnMap.put("student_no",username);
            if (studentService.listByMap(columnMap).size()==0) {
                if (studentService.save(user)) {
                    InfoBean infoBean = new InfoBean();
                    infoBean.setStatus_code(0);
                    infoBean.setInfo("注册成功");
                    return objectMapper.writeValueAsString(infoBean);
                }
                else {
                    InfoBean infoBean = new InfoBean();
                    infoBean.setStatus_code(1);
                    infoBean.setInfo("注册失败");
                    return objectMapper.writeValueAsString(infoBean);
                }
            }
            else {
                InfoBean infoBean = new InfoBean();
                infoBean.setStatus_code(1);
                infoBean.setInfo("重复注册");
                return objectMapper.writeValueAsString(infoBean);
            }
        }
        else {
            TeacherBean user=new TeacherBean();
            user.setTeacher_no(username);
            user.setTeacher_password(PwdSecurity.encode(password));
            user.setTeacher_name(nickname);
            HashMap<String, Object> columnMap = new HashMap<>();
            columnMap.put("teacher_no",username);
            if (teacherService.listByMap(columnMap).size()==0) {
                if (teacherService.save(user)) {
                    InfoBean infoBean = new InfoBean();
                    infoBean.setStatus_code(0);
                    infoBean.setInfo("注册成功");
                    return objectMapper.writeValueAsString(infoBean);
                }
                else {
                    InfoBean infoBean = new InfoBean();
                    infoBean.setStatus_code(1);
                    infoBean.setInfo("注册失败");
                    return objectMapper.writeValueAsString(infoBean);
                }
            }
            else {
                InfoBean infoBean = new InfoBean();
                infoBean.setStatus_code(1);
                infoBean.setInfo("重复注册");
                return objectMapper.writeValueAsString(infoBean);
            }
        }

    }
}
