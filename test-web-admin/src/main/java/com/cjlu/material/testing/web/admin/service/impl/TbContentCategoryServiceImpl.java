package com.cjlu.material.testing.web.admin.service.impl;

import com.cjlu.material.testing.commons.dto.BaseResult;
import com.cjlu.material.testing.commons.validator.BeanValidator;
import com.cjlu.material.testing.domain.TbContentCategory;
import com.cjlu.material.testing.web.admin.abstracts.AbstractBaseTreeServiceImpl;
import com.cjlu.material.testing.web.admin.dao.TbContentCategoryDao;
import com.cjlu.material.testing.web.admin.service.TbContentCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


//第二个实现是为了注入，因为在ContentCategoryController里的service只有接口没有实现，所以这里要实现一下
@Service
@Transactional(readOnly = true)
public class TbContentCategoryServiceImpl extends AbstractBaseTreeServiceImpl<TbContentCategory, TbContentCategoryDao> implements TbContentCategoryService {

        //意思就是这个方法有事务，不是只读
    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbContentCategory entity) {
        String validator = BeanValidator.validator(entity);
        //验证不通过
        if (validator != null) {
            return BaseResult.fail(validator);
        }
        //验证通过
        else {
            TbContentCategory parent = entity.getParent();
            if (parent ==null || parent.getId() ==null){
                //代表根目录
                parent.setId(0L);

            }
            entity.setUpdated(new Date());
            if (entity.getId()==null){
                entity.setCreated(new Date());
                entity.setIsParent(false);
                if (parent.getId()!=0L){
                    //判断当前新增的节点有没有父节点
                    TbContentCategory currentCategoryParent = getById(parent.getId());
                    if (currentCategoryParent!=null){
                        //为父级节点设置isparent
                        currentCategoryParent.setIsParent(true);
                        update(currentCategoryParent);
                    }
                }
//                父级节点为0，表示为根目录
                else {
                    //根目录一定是父级目录
                    entity.setIsParent(true);
                }


                dao.insert(entity);
            }
            //修改
            else {
                update(entity);
            }

            return BaseResult.success("保存信息成功！");
        }
    }
}
