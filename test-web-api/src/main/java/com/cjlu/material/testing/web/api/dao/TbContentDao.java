package com.cjlu.material.testing.web.api.dao;

import com.cjlu.material.testing.domain.TbContent;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TbContentDao {
    /**
     * 根据类别id查询内容列表
     * @param tbContent
     * @return
     */
    List<TbContent> selectByCategoryId(TbContent tbContent);
}
