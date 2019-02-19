package net.realme.mall.basics.cache;

import net.realme.framework.cache.redis.RedisCache;
import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.mall.basics.dto.SiteDto;
import net.realme.mall.basics.facade.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SiteCache {
    @Autowired
    private RedisCache cache;

    @Autowired
    private SiteService siteService;

    @PostConstruct
    private void init(){
        ResultT<ResultList<SiteDto>> siteList =  siteService.listAll(0, 2);
        System.out.println(siteList);
    }


}
