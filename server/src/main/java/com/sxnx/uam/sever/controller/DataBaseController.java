package com.sxnx.uam.sever.controller;

import com.sxnx.uam.api.enums.StatusCode;
import com.sxnx.uam.api.response.BaseResponse;
import com.sxnx.uam.model.entity.User;
import com.sxnx.uam.sever.dto.UpdatePsdDto;
import com.sxnx.uam.sever.service.DataBaseService;
import com.sxnx.uam.sever.utils.ValidatorUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 公司：普元金融—F2E
 * 文件名：DataBaseController
 * 作者：duanwenhong(段文红)
 * 邮件：duanwh@primeton.com
 * 时间：2020-2-10 10:50
 * 描述：token+数据库 认证模式
 */
@RestController
@RequestMapping("database")
public class DataBaseController extends AbstractController {

    @Autowired
    private DataBaseService dataBaseService;

    //用户登录
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public BaseResponse login(@RequestParam String userName, @RequestParam String password) {
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            return new BaseResponse(StatusCode.UserNamePasswordNotBlank);
        }
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            response.setData(dataBaseService.authAndCreateToken(userName, password));

        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }

    //访问需要被授权的资源
    @RequestMapping(value = "token/auth", method = RequestMethod.GET)
    public BaseResponse tokenAuth() {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            String info = "数据库+token~成功访问需要被拦截的链接/资源";
            response.setData(info);

        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }

    //访问不需要被授权的资源
    @RequestMapping(value = "token/unauth", method = RequestMethod.GET)
    public BaseResponse tokenUnauth() {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            String info = "数据库+token~成功访问不需要被拦截的链接/资源";
            response.setData(info);

        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }

    //修改密码
    @RequestMapping(value = "token/password/update", method = RequestMethod.POST)
    public BaseResponse updatePassword(@RequestHeader String accessToken, @RequestBody @Validated UpdatePsdDto dto, BindingResult bindingResult) {
        log.info("--token+数据库~修改密码--");

        if (StringUtils.isBlank(accessToken)) {
            return new BaseResponse(StatusCode.InvalidParams);
        }
        String res = ValidatorUtil.checkResult(bindingResult);
        if (StringUtils.isNotBlank(res)) {
            return new BaseResponse(StatusCode.InvalidParams.getCode(), res);
        }
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            dataBaseService.updatePassword(accessToken, dto);

        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }

    //退出注销登录~前端需要清除token并重新进行登录
    @RequestMapping(value = "token/logout", method = RequestMethod.GET)
    public BaseResponse logout(@RequestHeader String accessToken) {
        if (StringUtils.isBlank(accessToken)) {
            return new BaseResponse(StatusCode.InvalidParams);
        }
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            dataBaseService.invalidateByAccessToken(accessToken);

        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }

    //实际的业务模块操作-新增用户
    @RequestMapping(value = "token/user/save", method = RequestMethod.POST)
    public BaseResponse saveUser(@RequestHeader String accessToken, @RequestBody @Validated User user, BindingResult bindingResult) {
        if (StringUtils.isBlank(accessToken)) {
            return new BaseResponse(StatusCode.InvalidParams);
        }
        String res = ValidatorUtil.checkResult(bindingResult);
        if (StringUtils.isNotBlank(res)) {
            return new BaseResponse(StatusCode.InvalidParams.getCode(), res);
        }
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            dataBaseService.saveUser(accessToken, user);

        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }
}














































