package com.eoem.cache;

import lombok.AllArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;

import java.util.Set;

public class HowToGetKeysTest {
    @AllArgsConstructor
    class Test {
        private CacheManager cacheManager;
        
        Set<Object> keys(String cacheName) {
            CaffeineCache caffeineCache = (CaffeineCache) cacheManager.getCache(cacheName);
            com.github.benmanes.caffeine.cache.Cache<Object, Object> nativeCache = caffeineCache.getNativeCache();
            return nativeCache.asMap().keySet();
        }
        
    }
}
