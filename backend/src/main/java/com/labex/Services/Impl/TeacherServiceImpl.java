package com.labex.Services.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.labex.Beans.TeacherBean;
import com.labex.DAO.Mapper.TeacherMapper;
import com.labex.Services.TeacherService;
import org.springframework.stereotype.Service;

@Service("teacherService")
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, TeacherBean> implements TeacherService {
}
