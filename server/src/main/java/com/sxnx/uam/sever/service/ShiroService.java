package com.sxnx.uam.sever.service;
import com.sxnx.uam.model.entity.User;
import com.sxnx.uam.model.mapper.UserMapper;
import com.sxnx.uam.sever.dto.UpdatePsdDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* 公司：普元金融—F2E
* 文件名：ShiroService
* 作者：duanwenhong(段文红)
* 邮件：duanwh@primeton.com
* 时间：2020-2-10 11:33
* 描述：shiro~service
*/
@Service
public class ShiroService {

    private static final Logger log= LoggerFactory.getLogger(ShiroService.class);

    @Autowired
    private UserMapper userMapper;


    //修改密码
    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(final UpdatePsdDto dto)throws Exception{
        Subject subject= SecurityUtils.getSubject();

        if (subject!=null && subject.getPrincipal()!=null){
            User currUser= (User) subject.getPrincipal();

            //核心业务逻辑：修改密码
            User user=userMapper.selectByUserName(currUser.getUserName());
            if (user==null){
                throw new RuntimeException("当前用户不存在，修改密码失败！");
            }
            if (!user.getPassword().equals(dto.getOldPassword())){
                throw new RuntimeException("旧密码不匹配！");
            }
            //修改密码
            int res=userMapper.updatePassword(currUser.getUserName(),dto.getOldPassword(),dto.getNewPassword());
            if (res<=0){
                throw new RuntimeException("修改密码失败~请重新尝试或者联系管理员！");
            }

            //this.invalidateSession();
        }
    }

    //失效session
    public void invalidateSession() throws Exception{
        Subject subject= SecurityUtils.getSubject();
        if (subject!=null){
            subject.logout();
        }
    }

}
































