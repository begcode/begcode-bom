package com.begcode.report.console.cache;

import com.begcode.report.core.cache.ReportCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisReportCache implements ReportCache {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Object getObject(String file) {
        return redisTemplate.opsForValue().get(file);
    }

    @Override
    public void storeObject(String file, Object obj) {
        redisTemplate.opsForValue().set(file, obj, CacheProperties.getCacheExpire(), TimeUnit.MINUTES);

    }

    @Override
    public boolean disabled() {
        return CacheProperties.isEnableRedis();
    }
}
