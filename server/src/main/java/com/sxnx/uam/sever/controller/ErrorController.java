package com.sxnx.uam.sever.controller;


import com.sxnx.uam.api.enums.StatusCode;
import com.sxnx.uam.api.response.BaseResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 公司：普元金融—F2E
 * 文件名：ErrorController
 * 作者：duanwenhong(段文红)
 * 邮件：duanwh@primeton.com
 * 时间：2020-2-10 10:52
 * 描述：错误信息封装
 */
@Controller
public class ErrorController {

    @RequestMapping(value = "/error/500", method = RequestMethod.GET)
    public String error500() {
        return "500";
    }

    @RequestMapping(value = "/error/404", method = RequestMethod.GET)
    public String error404() {
        return "404";
    }
    @RequestMapping(value = "/error/403", method = RequestMethod.GET)
    public String error403() {
        return "403";
    }

    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String unauth() {
        return "login";
    }
}