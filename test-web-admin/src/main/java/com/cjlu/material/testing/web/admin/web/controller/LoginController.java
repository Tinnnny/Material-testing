package com.cjlu.material.testing.web.admin.web.controller;

import com.cjlu.material.testing.commons.constant.ConstantUtils;
import com.cjlu.material.testing.domain.TbUser;
import com.cjlu.material.testing.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private TbUserService tbuserService;

    @RequestMapping(value = {"", "login"}, method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    /**
     * 登录逻辑
     *
     * @param email
     * @param password
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String email, @RequestParam(required = true) String password, HttpServletRequest httpServletRequest, Model model) {
        TbUser tbUser = tbuserService.login(email, password);


        if (tbUser == null) {
            //登录失败
            model.addAttribute("message", "用户名或密码错误，请重新输入！");
            return login();
        }
        //登录成功
        else {
            //记录登录信息
            httpServletRequest.getSession().setAttribute(ConstantUtils.SESSION_USER, tbUser);
            return "redirect:/main";
        }
    }

    /**
     * 注销
     *
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest httpServletRequest) {
        TbUser tbuser = (TbUser) httpServletRequest.getSession().getAttribute(ConstantUtils.SESSION_USER);
        if (tbuser != null) {
            httpServletRequest.getSession().invalidate();
        }
        return login();
    }


}