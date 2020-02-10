package com.sxnx.uam.sever.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 公司：普元金融—F2E
 * 文件名：UpdatePsdDto
 * 作者：duanwenhong(段文红)
 * 邮件：duanwh@primeton.com
 * 时间：2020-2-10 11:24
 * 描述：
 */
@Data
public class UpdatePsdDto implements Serializable {

    @NotBlank(message = "旧密码不能为空！")
    private String oldPassword;

    @NotBlank(message = "新密码不能为空！")
    private String newPassword;

}