
package com.begcode.report.core.cache;

import com.begcode.report.console.cache.CacheProperties;
import com.begcode.report.core.utils.SpringContextUtils;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jacky.gao
 * @since 2017年3月17日
 */
public class ResourceCache {

    private static RedisTemplate redisTemplate;

    private static Map<String, Object> map = new HashMap<String, Object>();

    static {
        redisTemplate = SpringContextUtils.getBean(RedisTemplate.class);
    }

    public static void putObject(String key, Object obj) {
        if (CacheProperties.isEnableRedis()) {
            redisTemplate.opsForValue().set(key, obj);
            return;
        }

        if (map.containsKey(key)) {
            map.remove(key);
        }
        map.put(key, obj);
    }

    public static Object getObject(String key) {
        if (CacheProperties.isEnableRedis()) {
            return redisTemplate.opsForValue().get(key);
        }

        return map.get(key);
    }
}
