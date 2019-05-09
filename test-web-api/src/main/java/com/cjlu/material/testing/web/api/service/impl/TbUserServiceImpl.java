package com.cjlu.material.testing.web.api.service.impl;

import com.cjlu.material.testing.domain.TbUser;
import com.cjlu.material.testing.web.api.dao.TbUserDao;
import com.cjlu.material.testing.web.api.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class TbUserServiceImpl implements TbUserService {
    @Autowired
    private TbUserDao tbUserDao;

    @Override
    public TbUser login(TbUser tbUser) {
        TbUser user = tbUserDao.login(tbUser);

        if (user !=null){
            //将明文密码加密
            String password = DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes());
            if (password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}
