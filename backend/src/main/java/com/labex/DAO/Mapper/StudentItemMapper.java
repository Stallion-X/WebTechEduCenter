package com.labex.DAO.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.labex.Beans.StudentItemBean;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentItemMapper extends BaseMapper<StudentItemBean> {
    Integer getMaxId();
}
