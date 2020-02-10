package com.sxnx.uam.sever.utils;

import com.sxnx.uam.api.enums.StatusCode;
import com.sxnx.uam.api.response.BaseResponse;
import com.sxnx.uam.sever.enums.Constant;
import io.jsonwebtoken.*;
import org.bouncycastle.util.encoders.Base64;
import org.joda.time.DateTime;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * 公司：普元金融—F2E
 * 文件名：JwtUtil
 * 作者：duanwenhong(段文红)
 * 邮件：duanwh@primeton.com
 * 时间：2020-2-10 11:37
 * 描述：jwt通用工具类
 */
public class JwtUtil {

    //生成密钥
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.decode(Constant.JWT_SECRET);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    //创建token
    public static String createJWT(final String id, final String subject, final Long expireMills) {
        //定义生成签名的算法
        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
        //定义生成签名的密钥
        SecretKey key = generalKey();

        Date now = DateTime.now().toDate();
        //借助于第三方依赖组件jwt的api来实现
        JwtBuilder builder = Jwts.builder()
                //用户id
                .setId(id)
                //主体
                .setSubject(subject)
                //签发者
                .setIssuer(Constant.TOKEN_ISSUER)
                //签发时间
                .setIssuedAt(now)
                //签发时指定 加密算法、密钥
                .signWith(algorithm, key);

        //设定过期时间
        if (expireMills >= 0) {
            Long realExpire = System.currentTimeMillis() + expireMills;
            builder.setExpiration(new Date(realExpire));
        }

        //生成access token
        return builder.compact();
    }

    //验证解析token
    public static BaseResponse validateJWT(final String accessToken) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        Claims claims = null;
        try {
            claims = parseJWT(accessToken);
            response.setData(claims);
        } catch (ExpiredJwtException e) {
            response = new BaseResponse(StatusCode.TokenValidateExpireToken);
        } catch (SignatureException e) {
            response = new BaseResponse(StatusCode.TokenValidateCheckFail);
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.TokenValidateCheckFail);
        }
        return response;
    }


    //解析token
    public static Claims parseJWT(final String accessToken) throws Exception {
        SecretKey key = generalKey();
        return Jwts.parser().setSigningKey(key).parseClaimsJws(accessToken).getBody();
    }
}










































