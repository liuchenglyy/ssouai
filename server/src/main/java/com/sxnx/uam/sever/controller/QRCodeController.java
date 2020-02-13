package com.sxnx.uam.sever.controller;

import com.sxnx.uam.api.enums.StatusCode;
import com.sxnx.uam.api.response.BaseResponse;
import com.sxnx.uam.sever.utils.qrcode.QRCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ 公司  :  普元金融-F2E
 * @ 作者  :  刘诚
 * @ 时间 :  2020/2/12 14:42
 * @ 邮件 :  liucheng@primeton.com
 * @ 描述    :
 */
@Controller
@RequestMapping("qrcode")
public class QRCodeController extends AbstractController {

    @RequestMapping(value="encode",method = RequestMethod.GET)
    public ModelAndView getQRCode() {
        ModelAndView mv = new ModelAndView();
        BaseResponse response = new BaseResponse(StatusCode.Success);
        String destPath=null;
        // 存放在二维码中的内容
        String qrcodeMsg = "Hello World 我叫刘诚!";
        // 嵌入二维码的图片路经
        String imgPath = "F:/qrcode/sxnxlogo.jpg";
        // 生成的二维码的路经及名称
         destPath = "F:/qrcode/qrcode.jpg";
        //生成二维码
        try {
            QRCodeUtil.encode(qrcodeMsg,imgPath,destPath,true);
            // 解析二维码
            String str = QRCodeUtil.decode(destPath);
            // 打印出解析出的内容
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setData(destPath);
        mv.setViewName("/login");
        mv.addObject("response",response);
        return mv;
    }
}