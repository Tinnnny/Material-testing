package com.cjlu.material.testing.web.admin.service.impl;

import com.cjlu.material.testing.commons.dto.BaseResult;
import com.cjlu.material.testing.commons.validator.BeanValidator;
import com.cjlu.material.testing.domain.TbContent;
import com.cjlu.material.testing.web.admin.abstracts.AbstractBaseServiceImpl;
import com.cjlu.material.testing.web.admin.dao.TbContentDao;
import com.cjlu.material.testing.web.admin.service.TbContentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class TbContentServiceImpl extends AbstractBaseServiceImpl<TbContent, TbContentDao> implements TbContentService {


    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbContent tbContent) {
        String validator = BeanValidator.validator(tbContent);
        //验证不通过
        if (validator != null) {
            return BaseResult.fail(validator);
        }

        else {
            tbContent.setUpdated(new Date());
            //新增
            if (tbContent.getId() == null) {

                tbContent.setCreated(new Date());
                dao.insert(tbContent);
            }
            //编辑用户
            else {
                update(tbContent);
            }
            return BaseResult.success("保存信息成功！");
        }
    }

}


