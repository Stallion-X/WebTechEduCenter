package com.labex.Controller;

import com.labex.Beans.ClazzBean;
import com.labex.Beans.StudentBean;
import com.labex.Beans.TeacherBean;
import com.labex.Services.Impl.ClazzServiceImpl;
import com.labex.Services.Impl.StudentServiceImpl;
import com.labex.Services.Impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
public class StudentController {
    @Autowired
    StudentServiceImpl studentService;
    @Autowired
    ClazzServiceImpl clazzService;
    @Autowired
    TeacherServiceImpl teacherService;

    @GetMapping("/getStudent")
    public StudentBean getStudent(@RequestParam Integer id) {
        return studentService.getById(id);
    }

    @GetMapping("/getStudentByNo")
    public StudentBean getStudentByNo(@RequestParam Integer no) {
        HashMap<String, Object> columnMap = new HashMap<>();
        columnMap.put("student_no",no);
        StudentBean result = studentService.listByMap(columnMap).get(0);
        result.setStudent_password("");
        return result;
    }

    @GetMapping("/stuGetTeacher")
    public TeacherBean stuGetTeacher(@RequestParam Integer id) {
        HashMap<String, Object> columnMap = new HashMap<>();
        columnMap.put("clazz_no",studentService.getById(id).getClazz_no());
        return teacherService.getById(clazzService.listByMap(columnMap).get(0).getTeacher_id());
    }

    @GetMapping("/getClazzMates")
    public List<StudentBean> getClazzMates(@RequestParam Integer id) {
        HashMap<String, Object> columnMap = new HashMap<>();
        columnMap.put("clazz_no",studentService.getById(id).getClazz_no());
        return studentService.listByMap(columnMap);
    }

    public List<StudentBean> getStudentsByClazz(String clazz_no) {
        HashMap<String, Object> columnMap = new HashMap<>();
        columnMap.put("clazz_no",clazz_no);
        return studentService.listByMap(columnMap);
    }
}
