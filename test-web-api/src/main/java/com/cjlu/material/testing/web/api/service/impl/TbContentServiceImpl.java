package com.cjlu.material.testing.web.api.service.impl;

import com.cjlu.material.testing.domain.TbContent;
import com.cjlu.material.testing.domain.TbContentCategory;
import com.cjlu.material.testing.web.api.dao.TbContentDao;
import com.cjlu.material.testing.web.api.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class TbContentServiceImpl implements TbContentService {

    @Autowired
    private TbContentDao tbContentDao;

    @Override
    public List<TbContent> selectByCategoryId(Long categoryId) {
        TbContentCategory tbContentCategory=new TbContentCategory();
        tbContentCategory.setId(categoryId);
        TbContent tbContent=new TbContent();
        tbContent.setTbContentCategory(tbContentCategory);
        return tbContentDao.selectByCategoryId(tbContent);
    }
}
