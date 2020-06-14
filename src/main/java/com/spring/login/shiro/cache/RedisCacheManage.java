package com.spring.login.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 *  shiro框架使用redis缓存
 */
public class RedisCacheManage implements CacheManager {

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {

        return new RedisCache<K, V>();
    }
}
