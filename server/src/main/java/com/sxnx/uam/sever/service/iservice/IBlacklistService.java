package com.sxnx.uam.sever.service.iservice;

import com.sxnx.uam.model.entity.Blacklist;

public interface IBlacklistService {
    /**
     * 保存黑名单
     * @param blacklist
     * @return
     */
    Blacklist saveBlacklist(Blacklist blacklist);


}
