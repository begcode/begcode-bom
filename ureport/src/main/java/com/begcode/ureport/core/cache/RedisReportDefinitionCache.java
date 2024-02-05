package com.begcode.ureport.core.cache;

import com.begcode.ureport.console.cache.CacheProperties;
import com.begcode.ureport.core.definition.ReportDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author: summer
 * @Date: 2022/5/23 22:00
 * @Description:
 **/
@Component
public class RedisReportDefinitionCache implements ReportDefinitionCache {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ReportDefinition getReportDefinition(String file) {
        return (ReportDefinition) redisTemplate.opsForValue().get(sessionId() + file);
    }

    @Override
    public void cacheReportDefinition(String file, ReportDefinition reportDefinition) {
        redisTemplate.opsForValue().set(sessionId() + file, reportDefinition, CacheProperties.getCacheExpire(), TimeUnit.MINUTES);
    }

    @Override
    public void removeReportDefinition(String file) {
        redisTemplate.delete(sessionId() + file);
    }

    @Override
    public boolean disabled() {
        return CacheProperties.isEnableRedis();
    }
}
