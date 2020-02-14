package com.sxnx.uam.model.mapper;

import com.sxnx.uam.model.entity.Blacklist;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlacklistMapper {
    /**
     * 创建黑名单
     */
    int insert(Blacklist blacklist);
    /**
     * 根据主键查询黑名单
     */
    Blacklist selectByPrimaryKey(int id);
    /**
     * 删除黑名单
     */
    int  deleteByPrimaryKey(int id);
    /**
     * 修改黑名单
     */
    int updateByPrimaryKey(Blacklist blacklist);
    

}
