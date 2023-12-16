package com.labex.Services.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.labex.Beans.CourseBean;
import com.labex.DAO.Mapper.CourseMapper;
import com.labex.Services.CourseService;
import org.springframework.stereotype.Service;

@Service("courseService")
public class CourseServiceImpl extends ServiceImpl<CourseMapper, CourseBean> implements CourseService {
}
