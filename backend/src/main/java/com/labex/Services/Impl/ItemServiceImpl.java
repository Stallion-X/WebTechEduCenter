package com.labex.Services.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.labex.Beans.ItemBean;
import com.labex.DAO.Mapper.ItemMapper;
import com.labex.Services.ItemService;
import org.springframework.stereotype.Service;

@Service("itemService")
public class ItemServiceImpl extends ServiceImpl<ItemMapper, ItemBean> implements ItemService {
}
