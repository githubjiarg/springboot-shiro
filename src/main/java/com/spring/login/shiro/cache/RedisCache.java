package com.spring.login.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;

/**
 *  redis 权限缓存实现
 * @param <K>
 * @param <V>
 */
public class RedisCache<K, V> implements Cache<K, V> {

    @Resource
    private static RedisTemplate<String,Object> redisTemplate;


    @Override
    public Object get(Object o) throws CacheException {

        return redisTemplate.opsForValue().get(o);
    }

    @Override
    public Object put(Object o, Object o2) throws CacheException {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.opsForValue().set(o.toString(),o2.toString());
        return null;
    }

    @Override
    public Object remove(Object o) throws CacheException {
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set keys() {
        return null;
    }

    @Override
    public Collection values() {
        return null;
    }
}
