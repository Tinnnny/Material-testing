package com.cjlu.material.testing.web.admin.dao;

import com.cjlu.material.testing.commons.persistence.BaseDao;
import com.cjlu.material.testing.domain.TbUser;
import org.springframework.stereotype.Repository;


@Repository
public interface TbUserDao extends BaseDao<TbUser> {
    /**
     * 根据邮箱查询用户
     *
     * @param email
     * @return
     */
    TbUser getByEmail(String email);
}
