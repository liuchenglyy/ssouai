package com.sxnx.uam.sever.service;

import com.sxnx.uam.model.entity.Blacklist;
import com.sxnx.uam.model.mapper.BlacklistMapper;
import com.sxnx.uam.sever.service.iservice.IBlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlacklistService implements IBlacklistService {
    @Autowired
    private BlacklistMapper blacklistMapper;

    @Override
    public Blacklist saveBlacklist(Blacklist blacklist) {
        int id =blacklistMapper.insert(blacklist);
        return blacklist;
    }
}
