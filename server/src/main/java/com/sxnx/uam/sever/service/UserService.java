package com.sxnx.uam.sever.service;

import com.sxnx.uam.model.entity.User;
import com.sxnx.uam.model.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 公司：普元金融—F2E
 * 文件名：UserService
 * 作者：duanwenhong(段文红)
 * 邮件：duanwh@primeton.com
 * 时间：2020-2-10 11:34
 * 描述：用户服务
 */
@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    //用户认证
    public User authUser(String userName, String password) throws Exception {
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            throw new RuntimeException("用户名或者密码不能为空！");
        }
        User user = userMapper.selectByUserName(userName);
        if (user == null) {
            throw new RuntimeException("当前用户不存在！");
        }
        if (!password.equals(user.getPassword())) {
            throw new RuntimeException("用户名密码不匹配！");
        }

        return user;
    }

}

























