package com.labex.Services.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.labex.Beans.StudentComposeBean;
import com.labex.DAO.Mapper.StudentComposeMapper;
import com.labex.Services.StudentComposeService;
import org.springframework.stereotype.Service;

@Service("studentComposeService")
public class StudentComposeServiceImpl extends ServiceImpl<StudentComposeMapper, StudentComposeBean> implements StudentComposeService {
}
