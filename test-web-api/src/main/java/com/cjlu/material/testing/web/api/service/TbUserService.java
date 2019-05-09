package com.cjlu.material.testing.web.api.service;

import com.cjlu.material.testing.domain.TbUser;

public interface TbUserService {

    /**
     * 登录
     * @param tbUser
     * @return
     */
    TbUser login(TbUser tbUser);
}
