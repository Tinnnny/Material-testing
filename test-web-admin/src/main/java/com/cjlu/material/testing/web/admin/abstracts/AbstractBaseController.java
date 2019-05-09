package com.cjlu.material.testing.web.admin.abstracts;

import com.cjlu.material.testing.commons.dto.BaseResult;
import com.cjlu.material.testing.commons.dto.PageInfo;
import com.cjlu.material.testing.commons.persistence.BaseEntity;
import com.cjlu.material.testing.commons.persistence.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

//因为service以s开头，所以泛型也用s
public abstract class AbstractBaseController<T extends BaseEntity,S extends BaseService<T>> {

    /**
     * 注入业务逻辑层
     */
    @Autowired
    protected S service;
    /**
     * 跳转列表页
     * @return
     */
    public abstract String list();

    /**
     * 跳转到用户表单页
     *
     * @return
     */
    public abstract String form();

    /**
     * 保存信息
     * @param entity
     * @param model
     * @param redirectAttributes
     * @return
     */
    public abstract String save(T entity, Model model, RedirectAttributes redirectAttributes);

    /**
     * 删除
     * @param ids
     * @return
     */
    public abstract BaseResult delete(String ids);


    /**
     * 详情页面
     * @param entity
     * @return
     */
    public abstract String detail(T entity);


    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public PageInfo<T> page(HttpServletRequest request, T entity) {
        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");
        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 10 : Integer.parseInt(strLength);
        //封装datatables需要的结果
        PageInfo<T> pageInfo = service.page(start, length, draw,entity);


        return pageInfo;
    }

}
