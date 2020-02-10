package com.sxnx.uam.sever.controller;


import com.google.common.collect.Lists;
import com.sxnx.uam.api.enums.StatusCode;
import com.sxnx.uam.api.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 公司：普元金融—F2E
 * 文件名：BaseController
 * 作者：duanwenhong(段文红)
 * 邮件：duanwh@primeton.com
 * 时间：2020-2-10 10:48
 * 描述：
 */
@RestController
@RequestMapping("base")
public class BaseController extends AbstractController {

    @RequestMapping("info")
    public BaseResponse info() {
        log.info("--请求响应数据列表--");

        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            String strOne = "hello,测试1";
            String strTwo = "hello,jsons测试2";
            String strThree = "hello,jsons测试3";

            List<String> list = Lists.newLinkedList();
            list.add(strOne);
            list.add(strTwo);
            list.add(strThree);

            response.setData(list);
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("redis/info")
    public BaseResponse redisInfo() {
        log.info("--请求响应redis数据列表--");

        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            String key = "SpringBootUserAuthKey";
            String value = "段文红测试";
            stringRedisTemplate.opsForValue().set(key, value);


            response.setData(stringRedisTemplate.opsForValue().get(key));
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }

}