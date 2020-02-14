package com.sxnx.uam.model.entity;
/**
 * @ 公司  :  普元金融-F2E
 * @ 作者  :  刘诚
 * @ 时间 :  2020/2/14 16:39
 * @ 邮件 :  liucheng@primeton.com
 * @ 描述    : 
 */
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
@Data
public class Blacklist  implements Serializable {
    private Integer id;
    /**
     * 黑名单类型
     */
    @NotBlank(message = "黑名单类型不能为空")
    private String blacklistType;
    /**
     * 黑名单名称
     */
    @NotBlank(message = "黑名单名称不能为空")
    private String blacklistName;
    /**
     * 黑名单创建人
     */
    private String createUserid;
    /**
     * 黑名单创建时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
}
