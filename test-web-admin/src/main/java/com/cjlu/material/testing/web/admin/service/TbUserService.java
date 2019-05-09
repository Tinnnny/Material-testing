package com.cjlu.material.testing.web.admin.service;

import com.cjlu.material.testing.commons.persistence.BaseService;
import com.cjlu.material.testing.domain.TbUser;



public interface TbUserService extends BaseService<TbUser> {
    /**
     * 用户登录
     *
     * @param email
     * @param password
     * @return
     */
    TbUser login(String email, String password);

}
