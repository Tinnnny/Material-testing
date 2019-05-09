package com.cjlu.material.testing.commons.persistence;

import com.cjlu.material.testing.commons.dto.BaseResult;

import java.util.List;

public interface BaseTreeService <T extends BaseEntity>{
    /**
     * 查询全部数据
     *
     * @return
     */
    List<T> selectAll();

    /**
     * 新增
     *
     * @param entity
     */
    BaseResult save(T entity);

    /**
     * 删除
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 根据id查询信息
     *
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * 更新
     *
     * @param entity
     */
    void update(T entity);

    /**
     * 根据父级节点查询所有子节点
     * @param pid
     * @return
     */
    List<T> selectByPid(Long pid);
}
