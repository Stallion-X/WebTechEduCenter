package com.labex.Services.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.labex.Beans.ComposeBean;
import com.labex.DAO.Mapper.ComposeMapper;
import com.labex.Services.ComposeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("composeService")
public class ComposeServiceImpl extends ServiceImpl<ComposeMapper, ComposeBean> implements ComposeService {
    @Autowired
    ComposeMapper composeMapper;
    public Integer getMaxNo() {
        return composeMapper.getMaxNo();
    }
}
