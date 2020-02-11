package com.sxnx.uam.sever.shiro;

import com.sxnx.uam.model.entity.User;
import com.sxnx.uam.model.mapper.UserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 公司：普元金融—F2E
 * 文件名：UserRealm
 * 作者：duanwenhong(段文红)
 * 邮件：duanwh@primeton.com
 * 时间：2020-2-10 11:35
 * 描述：自定义用户的认证、授权realm
 */
@Component
public class UserRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private UserMapper userMapper;

    //资源-权限分配、授权用
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //用户身份认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        final String userName = token.getUsername();
        final String password = String.valueOf(token.getPassword());
        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, password, getName());
        return authenticationInfo;
    }
}



































