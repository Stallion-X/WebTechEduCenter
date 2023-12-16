package com.labex.Services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.labex.Beans.ComposeBean;

public interface ComposeService extends IService<ComposeBean> {
    Integer getMaxNo();
}
