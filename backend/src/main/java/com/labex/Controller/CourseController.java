package com.labex.Controller;

import com.labex.Beans.*;
import com.labex.Services.Impl.CourseClazzServiceImpl;
import com.labex.Services.Impl.CourseServiceImpl;
import com.labex.Services.Impl.TermServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
public class CourseController {
    @Autowired
    CourseServiceImpl courseService;
    @Autowired
    TermServiceImpl termService;
    @Autowired
    CourseClazzServiceImpl courseClazzService;
    @GetMapping("/getTerms")
    public List<TermBean> getTerms() {
        return termService.list();
    }
    @GetMapping("getTermById")
    public TermBean getTermById(@RequestParam Integer term_id) {
        return termService.getById(term_id);
    }
    @GetMapping("/getCourse")
    public List<CourseBean> getCourse(@RequestParam Integer id) {
        HashMap<String, Object> columnMap = new HashMap<>();
        columnMap.put("teacher_id",id);
        return courseService.listByMap(columnMap);
    }
    @PostMapping("/createCourse")
    public InfoBean createCourse(@RequestParam String name,@RequestParam Integer tid,@RequestParam Integer term_id) {
        CourseBean courseBean = new CourseBean();
        courseBean.setCourse_name(name);
        courseBean.setTeacher_id(tid);
        courseBean.setTerm_id(term_id);
        if (courseService.save(courseBean)) {
            InfoBean infoBean = new InfoBean();
            infoBean.setStatus_code(0);
            infoBean.setInfo("创建成功");
            return infoBean;
        }
        else {
            InfoBean infoBean = new InfoBean();
            infoBean.setStatus_code(1);
            infoBean.setInfo("创建失败");
            return infoBean;
        }
    }

    @PostMapping("/startCourse")
    public InfoBean startCourse(@RequestParam Integer course_id, @RequestParam String clazz_no) {
        CourseClazzBean courseClazzBean = new CourseClazzBean();
        courseClazzBean.setCourse_id(course_id);
        courseClazzBean.setClazz_no(clazz_no);
        InfoBean infoBean = new InfoBean();
        if (courseClazzService.save(courseClazzBean)) {
            infoBean.setStatus_code(0);
            infoBean.setInfo("开课成功");
        }
        else {
            infoBean.setStatus_code(1);
            infoBean.setInfo("开课失败");
        }
        return infoBean;
    }
}
