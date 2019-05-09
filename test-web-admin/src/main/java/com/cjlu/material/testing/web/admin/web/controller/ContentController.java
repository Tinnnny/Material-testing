package com.cjlu.material.testing.web.admin.web.controller;


import com.cjlu.material.testing.commons.dto.BaseResult;
import com.cjlu.material.testing.domain.TbContent;
import com.cjlu.material.testing.web.admin.abstracts.AbstractBaseController;
import com.cjlu.material.testing.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * 内容管理
 */
@Controller
@RequestMapping(value = "content")
public class ContentController extends AbstractBaseController<TbContent, TbContentService> {


    @ModelAttribute
    public TbContent getTbContent(Long id) {
        TbContent tbContent = null;
        //id不为空
        if (id != null) {
            tbContent = service.getById(id);

        } else {
            tbContent = new TbContent();
        }

        return tbContent;
    }


    /**
     * 跳转到列表页
     *
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list() {
        return "content_list";
    }


    /**
     * 跳转到表单页
     *
     * @return
     */
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form() {
        return "content_form";
    }


    /**
     * 保存信息
     *
     * @param tbContent
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbContent tbContent, Model model, RedirectAttributes redirectAttributes) {
        BaseResult baseResult = service.save(tbContent);
        //保存成功
        if (baseResult.getStatus() == 200) {

            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/content/list";
        }
        //保存失败
        else {
            model.addAttribute("baseResult", baseResult);
            return "content_form";
        }

    }

    /**
     * 删除信息
     *
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public BaseResult delete(String ids) {
        BaseResult baseResult = null;

        if (StringUtils.isNotBlank(ids)) {
            String[] idArray = ids.split(",");
            service.deleteMulti(idArray);
            baseResult = BaseResult.success("删除内容成功");
        } else {
            baseResult = BaseResult.fail("删除内容失败");
        }

        return baseResult;
    }

    /**
     * 显示详情
     *
     * @param tbContent
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(TbContent tbContent) {
        return "content_detail";
    }


}
