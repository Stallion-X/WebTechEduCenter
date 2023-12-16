package com.labex.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.labex.Beans.ClazzBean;
import com.labex.Beans.InfoBean;
import com.labex.Beans.StudentBean;
import com.labex.Beans.TeacherBean;
import com.labex.Services.Impl.ClazzServiceImpl;
import com.labex.Services.Impl.CourseServiceImpl;
import com.labex.Services.Impl.StudentServiceImpl;
import com.labex.Services.Impl.TeacherServiceImpl;
import com.labex.Services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
public class TeacherController {
    @Autowired
    ClazzServiceImpl clazzService;
    @Autowired
    TeacherServiceImpl teacherService;
    @Autowired
    StudentServiceImpl studentService;

    @PostMapping("/createClazz")
    public InfoBean createClazz(@RequestParam String clazz_no, @RequestParam String teacherNo) throws JsonProcessingException {
        ClazzBean newClazz = new ClazzBean();
        newClazz.setClazz_no(clazz_no);
        HashMap<String, Object> columnMap = new HashMap<>();
        InfoBean infoBean = new InfoBean();
        try {
            columnMap.put("teacher_no",teacherNo);
            Integer teacherId = teacherService.listByMap(columnMap).get(0).getTeacher_id();
            newClazz.setTeacher_id(teacherId);
            clazzService.save(newClazz);
            infoBean.setStatus_code(0);
            infoBean.setInfo("创建班级成功");
        } catch (Exception e) {
            e.printStackTrace();
            infoBean.setStatus_code(1);
            infoBean.setInfo("创建班级失败");
        }
        return infoBean;
    }

    @GetMapping("/getTeacher")
    public TeacherBean getTeacher(@RequestParam Integer id) {
        return teacherService.getById(id);
    }

    @GetMapping("/getTeacherByNo")
    public TeacherBean getTeacherByNo(@RequestParam Integer no) {
        HashMap<String, Object> columnMap = new HashMap<>();
        columnMap.put("teacher_no",no);
        TeacherBean result = teacherService.listByMap(columnMap).get(0);
        result.setTeacher_password("");
        return result;
    }

    @GetMapping("/getClazz")
    public List<ClazzBean> getClazz(@RequestParam Integer id) {
        HashMap<String, Object> columnMap = new HashMap<>();
        columnMap.put("teacher_id",id);
        return clazzService.listByMap(columnMap);
    }

    @PostMapping("/removeClazz")
    public InfoBean removeClazz(@RequestParam String no) {
        InfoBean infoBean = new InfoBean();
        try {
            clazzService.removeById(no);
            infoBean.setStatus_code(0);
            infoBean.setInfo("删除成功");
        } catch(Exception e) {
            e.printStackTrace();
            infoBean.setStatus_code(1);
            infoBean.setInfo("删除失败");
        }
        return infoBean;
    }

    @GetMapping("/getClazzInfo")
    public List<StudentBean> getClazzInfo(@RequestParam String no) {
        HashMap<String, Object> columnMap = new HashMap<>();
        columnMap.put("clazz_no",no);
        return studentService.listByMap(columnMap);
    }

}
