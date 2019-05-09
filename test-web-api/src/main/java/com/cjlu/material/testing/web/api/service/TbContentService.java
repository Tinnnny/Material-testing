package com.cjlu.material.testing.web.api.service;

import com.cjlu.material.testing.domain.TbContent;

import java.util.List;

public interface TbContentService {

    /**
     * 根据类别id查询内容列表
     * @param categoryId
     * @return
     */
    List<TbContent> selectByCategoryId(Long categoryId);

}
