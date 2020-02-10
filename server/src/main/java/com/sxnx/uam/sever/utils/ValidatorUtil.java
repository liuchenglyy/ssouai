package com.sxnx.uam.sever.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * 公司：普元金融—F2E
 * 文件名：ValidatorUtil
 * 作者：duanwenhong(段文红)
 * 邮件：duanwh@primeton.com
 * 时间：2020-2-10 11:38
 * 描述：统一的验参工具
 */
public class ValidatorUtil {

    //统一处理加注解后校验的结果
    public static String checkResult(BindingResult result) {
        StringBuilder sb = new StringBuilder("");

        if (result != null && result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            for (ObjectError error : errors) {
                sb.append(error.getDefaultMessage()).append("\n");
            }

            //java8 stream的写法
            // errors.forEach(error -> sb.append(error.getDefaultMessage()).append("\n"));
        }
        return sb.toString();
    }


}