package com.cjlu.material.testing.commons.persistence;

import com.cjlu.material.testing.commons.dto.BaseResult;
import com.cjlu.material.testing.commons.dto.PageInfo;

import java.util.List;

/**
 * 所有业务逻辑层的基类
 */
public interface BaseService <T extends BaseEntity>{

    /**
     * 查询全部用户信息
     *
     * @return
     */
    List<T> selectAll();

    /**
     * 保存用户信息
     * @param entity
     * @return
     */
    BaseResult save(T entity);

    /**
     * 删除用户信息
     * @param id
     */
    void delete(Long id);

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * 更新用户信息
     * @param entity
     */
    void update(T entity);



    /**
     * 批量删除
     *
     * @param ids
     */
    void deleteMulti(String ids[]);

    /**
     * 分页查询
     *
     * @param start
     * @param length
     * @return
     */
    PageInfo<T> page(int start, int length, int draw, T entity);

    /**
     * 查询总记录数
     *
     * @return
     */
    int count(T entity);


}
