package com.labex.Services.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.labex.Beans.TermBean;
import com.labex.DAO.Mapper.TermMapper;
import com.labex.Services.TermService;
import org.springframework.stereotype.Service;

@Service("termService")
public class TermServiceImpl extends ServiceImpl<TermMapper, TermBean> implements TermService {
}
