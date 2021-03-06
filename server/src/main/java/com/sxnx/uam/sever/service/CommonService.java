package com.sxnx.uam.sever.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sxnx.uam.model.entity.Log;
import com.sxnx.uam.model.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 公司：普元金融—F2E
 * 文件名：CommonService
 * 作者：duanwenhong(段文红)
 * 邮件：duanwh@primeton.com
 * 时间：2020-2-10 11:29
 * 描述：
 */
@Service
@EnableAsync
public class CommonService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LogMapper logMapper;

    public void print(HttpServletResponse response, Object message) {
        try {
            response.setStatus(HttpStatus.OK.value());
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setHeader("Cache-Control", "no-cache, must-revalidate");
            PrintWriter writer = response.getWriter();
            writer.write(objectMapper.writeValueAsString(message));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Async
    public void insertLog(Log log) {
        try {
            logMapper.insertSelective(log);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //将自定义的响应信息返回给到前端
    public void writeJsonResponse(Object message, HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            //跨域设置
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
            response.setHeader("Access-Control-Allow-Credentials", "true");

            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            out = response.getWriter();
            out.write(objectMapper.writeValueAsString(message));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}




















