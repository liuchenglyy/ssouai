package com.sxnx.uam.sever.controller;/**
 * Created by Administrator on 2019/9/9.
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* 公司：普元金融—F2E
* 文件名：PageController
* 作者：duanwenhong(段文红)
* 邮件：duanwh@primeton.com
* 时间：2020-2-10 11:12
* 描述：
*/
@Controller
@RequestMapping("page")
public class PageController extends AbstractController {

    @RequestMapping("info")
    public String info(ModelMap modelMap){
        log.info("--请求响应跳转页面--");

        modelMap.put("code","http://www.fightjava.com");
        return "page";
    }

}