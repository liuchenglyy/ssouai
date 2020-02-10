package com.sxnx.uam.sever.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 公司：普元金融—F2E
 * 文件名：AccessTokenDto
 * 作者：duanwenhong(段文红)
 * 邮件：duanwh@primeton.com
 * 时间：2020-2-10 11:24
 * 描述：accessToken的内部字段信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessTokenDto implements Serializable {

    private Integer userId;

    private String userName;

    private Long timestamp;

    private String randomStr;

    private Long expire;

}




















