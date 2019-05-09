package com.cjlu.material.testing.web.admin.abstracts;

import com.cjlu.material.testing.commons.persistence.BaseEntity;
import com.cjlu.material.testing.commons.persistence.BaseTreeDao;
import com.cjlu.material.testing.commons.persistence.BaseTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//泛型依赖注入
public abstract class AbstractBaseTreeServiceImpl<T extends BaseEntity, D extends BaseTreeDao<T>> implements BaseTreeService<T> {

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
     *
     * @param id
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        dao.delete(new String[]{String.valueOf(id)});
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
     * 根据父级节点查询所有子节点
     * @param pid
     * @return
     */
    public List<T> selectByPid(Long pid) {
        return dao.selectByPid(pid);
    }

}
