package com.labex.Services.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.labex.Beans.StudentBean;
import com.labex.DAO.Mapper.StudentMapper;
import com.labex.Services.StudentService;
import org.springframework.stereotype.Service;

@Service("studentService")
public class StudentServiceImpl extends ServiceImpl<StudentMapper, StudentBean> implements StudentService {
}
