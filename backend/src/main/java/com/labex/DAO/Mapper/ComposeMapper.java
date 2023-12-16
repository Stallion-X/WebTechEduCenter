package com.labex.DAO.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.labex.Beans.ComposeBean;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ComposeMapper extends BaseMapper<ComposeBean> {
    Integer getMaxNo();
}
