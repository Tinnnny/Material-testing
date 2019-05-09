package com.cjlu.material.testing.domain;

import com.cjlu.material.testing.commons.persistence.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 内容管理
 */
@Data
public class TbContent extends BaseEntity {
    @Length(min = 1, max = 20 , message = "标题的长度必须介于1-20位之间")
    private String title;
    @Length(min = 1, max = 20 , message = "子标题的长度必须介于1-20位之间")
    private String subTitle;
    @Length(min = 6, max = 50 , message = "标题描述的长度必须介于1-50位之间")
    private String titleDesc;

    private String url;
    private String pic;
    private String pic2;
    @Length(min = 1,message = "内容不可为空")
    private String content;
    @NotNull(message = "父级类目不能为空，请重新输入！")
    private TbContentCategory tbContentCategory;

}
