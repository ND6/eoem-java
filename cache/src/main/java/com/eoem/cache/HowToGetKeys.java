package com.eoem.cache;

import org.springframework.beans.factory.annotation.Autowired;

public class HowToGetKeys {
    @Autowired
    public CacheManager cacheManager;
    
    public static void main(String[] args) {
        
        
        String cacheName = "testCache";
        CacheManager cacheManager = new CacheManager();
        net.sf.ehcache.Cache spApiConfig = (net.sf.ehcache.Cache) cacheManager.getCache(cacheName).getNativeCache();
    }
}
