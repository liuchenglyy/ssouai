/**
 * 公司：普元金融—F2E
 * 文件名：SpringSessionInterceptor
 * 作者：lenovo
 * 时间：2020-2-10 16:13
 * 描述：
 */
package com.sxnx.uam.sever.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sxnx.uam.api.enums.StatusCode;
import com.sxnx.uam.api.response.BaseResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

//拦截器
@Configuration
public class SpringSessionInterceptor implements HandlerInterceptor {

    //拦截受保护的资源/链接url，进行用户身份的认证
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        System.out.println("---spring session当前登录用户的sessionId：" + sessionId);

        if (session.getAttribute(sessionId) != null) {
            return true;
        }

        //没登录-故而不会有session
        try {
            response.setStatus(HttpStatus.OK.value());
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setHeader("Cache-Control", "no-cache, must-revalidate");
            PrintWriter writer = response.getWriter();
            writer.write(new ObjectMapper().writeValueAsString(new BaseResponse(StatusCode.AccessSessionNotExist)));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        if (response.getStatus() == 500) {
            modelAndView.setViewName("/error/500");
        } else if (response.getStatus() == 404) {
            modelAndView.setViewName("/error/404");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}



