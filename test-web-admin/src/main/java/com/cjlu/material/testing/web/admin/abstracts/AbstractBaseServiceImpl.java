package com.cjlu.material.testing.web.admin.abstracts;

import com.cjlu.material.testing.commons.dto.PageInfo;
import com.cjlu.material.testing.commons.persistence.BaseDao;
import com.cjlu.material.testing.commons.persistence.BaseEntity;
import com.cjlu.material.testing.commons.persistence.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractBaseServiceImpl<T extends BaseEntity,D extends BaseDao<T>> implements BaseService<T> {

    @Autowired
    protected D dao;

    /**
     * 查询所有
     *
     * @return
     */
    public List<T> selectAll() {
        return dao.selectAll();
    }

    /**
     * 删除
     *谁要操作数据库就要有事务
     * @param id
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        dao.delete(id);
    }
    /**
     * 根据id查询信息
     *
     * @param id
     * @return
     */
    public T getById(Long id) {
        return dao.getById(id);
    }

    /**
     * 更新
     *
     * @param entity
     */
    @Transactional(readOnly = false)
    public void update(T entity) {
        dao.update(entity);
    }

    /**
     * 批量删除
     *
     * @param ids
     */
    @Transactional(readOnly = false)
    public void deleteMulti(String ids[]){
        dao.deleteMulti(ids);
    }

    /**
     * 查询总记录数
     *
     * @return
     */
    public int count(T entity){
        return dao.count(entity);
    }


    /**
     * 分页查询
     * @param start
     * @param length
     * @param draw
     * @param entity
     * @return
     */
    @Override
    public PageInfo<T> page(int start, int length, int draw, T entity) {
        int count=count(entity);
        Map<String, Object> params = new HashMap<>();
        params.put("start", start);
        params.put("length", length);
        params.put("pageParams",entity);
        PageInfo<T> pageInfo = new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(dao.page(params));

        return pageInfo;
    }
}
