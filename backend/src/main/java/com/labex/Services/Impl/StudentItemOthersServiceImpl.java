package com.labex.Services.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.labex.Beans.StudentItemOthersBean;
import com.labex.DAO.Mapper.StudentItemOthersMapper;
import com.labex.Services.StudentItemOthersService;
import org.springframework.stereotype.Service;

@Service("studentItemOthersService")
public class StudentItemOthersServiceImpl extends ServiceImpl<StudentItemOthersMapper, StudentItemOthersBean> implements StudentItemOthersService {
}
