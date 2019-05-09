package com.cjlu.material.testing.domain;

import com.cjlu.material.testing.commons.persistence.BaseEntity;
import com.cjlu.material.testing.commons.utils.RegexpUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

@Data
public class TbUser extends BaseEntity {
    @Length(min = 6, max = 20 , message = "姓名的长度必须介于6-20位之间")
    private String username;
    @JsonIgnore
    @Length(min = 6, max = 20 , message = "密码的长度必须介于6-20位之间")
    private String password;
    @Pattern(regexp = RegexpUtils.PHONE,message = "手机号码格式不正确")
    private String phone;
    @Pattern(regexp = RegexpUtils.EMAIL,message = "邮箱格式不正确")
    private String email;


}
