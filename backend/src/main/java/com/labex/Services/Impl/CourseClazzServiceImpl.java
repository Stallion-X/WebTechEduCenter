package com.labex.Services.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.labex.Beans.CourseClazzBean;
import com.labex.DAO.Mapper.CourseClazzMapper;
import com.labex.Services.CourseClazzService;
import org.springframework.stereotype.Service;

@Service("courseClazzService")
public class CourseClazzServiceImpl extends ServiceImpl<CourseClazzMapper, CourseClazzBean> implements CourseClazzService {
}
