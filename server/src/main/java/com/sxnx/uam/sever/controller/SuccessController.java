/**
 * 公司：普元金融—F2E
 * 文件名：SuccessController
 * 作者：lenovo
 * 时间：2020-2-12 16:34
 * 描述：
 */
package com.sxnx.uam.sever.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 公司：普元金融—F2E
 * 文件名：SuccessController
 * 作者：duanwenhong(段文红)
 * 邮件：duanwh@primeton.com
 * 时间：2020-2-12 16:35
 * 描述：响应成功公共处理控制器
 */
@Controller
public class SuccessController extends AbstractController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginhtml() {
        return "login";
    }

    @RequestMapping(value = "/success/index", method = RequestMethod.GET)
    public String indexhtml() {
        return "index";
    }
}
