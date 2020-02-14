package com.sxnx.uam.sever.controller;

import com.sxnx.uam.model.entity.Blacklist;
import com.sxnx.uam.sever.service.BlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("blacklist")
public class BlacklistController extends AbstractController {
    @Autowired
    private BlacklistService blacklistService;

    /**
     * 创建黑名单
     * @param blacklist
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST )
    public Blacklist insert(@RequestBody  Blacklist blacklist) {
         blacklist=blacklistService.saveBlacklist(blacklist);
        System.out.println(blacklist.toString());
        return blacklist;
    }
}
