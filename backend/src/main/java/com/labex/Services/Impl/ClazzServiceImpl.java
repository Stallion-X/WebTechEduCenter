package com.labex.Services.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.labex.Beans.ClazzBean;
import com.labex.DAO.Mapper.ClazzMapper;
import com.labex.Services.ClazzService;
import org.springframework.stereotype.Service;

@Service("clazzService")
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, ClazzBean> implements ClazzService {
}
