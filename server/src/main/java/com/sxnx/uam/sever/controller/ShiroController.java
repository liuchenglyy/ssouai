package com.sxnx.uam.sever.controller;

import com.google.common.collect.Maps;
import com.sxnx.uam.api.enums.StatusCode;
import com.sxnx.uam.api.response.BaseResponse;
import com.sxnx.uam.sever.dto.UpdatePsdDto;
import com.sxnx.uam.sever.service.ShiroService;
import com.sxnx.uam.sever.utils.ValidatorUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 公司：普元金融—F2E
 * 文件名：ShiroController
 * 作者：duanwenhong(段文红)
 * 邮件：duanwh@primeton.com
 * 时间：2020-2-10 11:22
 * 描述： shiro session 认证模式
 */
@RestController
@RequestMapping("uam")
public class ShiroController extends AbstractController {

    @Autowired
    private ShiroService shiroService;


    //用户登录
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public BaseResponse login(@RequestParam String userName, @RequestParam String password) {
        ModelAndView mv = new ModelAndView();
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {

        }
        BaseResponse response = new BaseResponse(StatusCode.Success);


        try {
            //交由shiro的组件/api进行实现
            Subject subject = SecurityUtils.getSubject();
            if (!subject.isAuthenticated()) {
                UsernamePasswordToken token = new UsernamePasswordToken(userName, "2322323");
                subject.login(token);
            }

            if (subject.isAuthenticated()) {
                response.setData(SecurityUtils.getSubject().getPrincipal());
            }
            mv.setViewName("redirect:/success/index");
            mv.addObject("response",response);
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
            mv.addObject("response",response);
        }
        response.setData("http://localhost:8084/sxnxsso/success/index");
        return response;
    }


    //访问需要被授权的资源
    @RequestMapping(value = "auth", method = RequestMethod.GET)
    public BaseResponse tokenAuth() {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        Map<String, Object> resMap = Maps.newHashMap();
        try {
            String info = "shiro~成功访问需要被拦截的链接/资源";
            resMap.put("info", info);
            resMap.put("currUser", SecurityUtils.getSubject().getPrincipal());

            response.setData(resMap);

        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }

    //访问不需要被授权的资源
    @RequestMapping(value = "unauth", method = RequestMethod.GET)
    public BaseResponse tokenUnauth() {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            String info = "shiro~成功访问不需要被拦截的链接/资源";
            response.setData(info);

        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }

    //退出登录注销session
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public BaseResponse logout() {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            SecurityUtils.getSubject().logout();

        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }


    //修改密码
    @RequestMapping(value = "password/update", method = RequestMethod.POST)
    public BaseResponse updatePassword(@RequestBody @Validated UpdatePsdDto dto, BindingResult bindingResult) {
        log.info("--shiro~修改密码--");

        String res = ValidatorUtil.checkResult(bindingResult);
        if (StringUtils.isNotBlank(res)) {
            return new BaseResponse(StatusCode.InvalidParams.getCode(), res);
        }
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            shiroService.updatePassword(dto);

        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }

}














































