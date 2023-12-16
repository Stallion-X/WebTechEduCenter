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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    StudentServiceImpl studentService;
    @Autowired
    TeacherServiceImpl teacherService;
    @Autowired
    BCryptPasswordEncoder PwdSecurity;
    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, @RequestParam int type) throws JsonProcessingException {
        HashMap<String, Object> columnMap = new HashMap<>();
        if (type == 0) {
            columnMap.put("student_no",username);
            List<StudentBean> login = studentService.listByMap(columnMap);
            if(login.size()>0) {
                if (PwdSecurity.matches(password,login.get(0).getStudent_password())) {
                    StudentBean user =login.get(0);
                    return objectMapper.writeValueAsString(user);
                }
                else {
                    InfoBean infoBean = new InfoBean();
                    infoBean.setStatus_code(1);
                    infoBean.setInfo("密码或验证码错误");
                    return objectMapper.writeValueAsString(infoBean);
                }
            }
            else {
                InfoBean infoBean = new InfoBean();
                infoBean.setStatus_code(1);
                infoBean.setInfo("找不到账号");
                return objectMapper.writeValueAsString(infoBean);
            }
        }
        else {
            columnMap.put("teacher_no",username);
            List<TeacherBean> login = teacherService.listByMap(columnMap);
            if(login.size()>0) {
                if (PwdSecurity.matches(password,login.get(0).getTeacher_password())) {
                    TeacherBean user =login.get(0);
                    return objectMapper.writeValueAsString(user);
                }
                else {
                    InfoBean infoBean = new InfoBean();
                    infoBean.setStatus_code(1);
                    infoBean.setInfo("密码或验证码错误");
                    return objectMapper.writeValueAsString(infoBean);
                }
            }
            else {
                InfoBean infoBean = new InfoBean();
                infoBean.setStatus_code(1);
                infoBean.setInfo("找不到账号");
                return objectMapper.writeValueAsString(infoBean);
            }
        }

    }
}
