package com.labex.Services.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.labex.Beans.StudentItemBean;
import com.labex.DAO.Mapper.StudentItemMapper;
import com.labex.Services.StudentItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("studentItemService")
public class StudentItemServiceImpl extends ServiceImpl<StudentItemMapper, StudentItemBean> implements StudentItemService {
    @Autowired
    StudentItemMapper studentItemMapper;
    public Integer getMaxId() {
        return studentItemMapper.getMaxId();
    }
}
