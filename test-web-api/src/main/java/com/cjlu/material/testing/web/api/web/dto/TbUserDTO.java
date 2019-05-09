package com.cjlu.material.testing.web.api.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
@Data
public class TbUserDTO implements Serializable {

    private Long id;
    private String username;

    @JsonIgnore
    private String password;
    private String phone;
    private String email;
}
