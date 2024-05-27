
package com.begcode.report.console.cache;

import com.begcode.report.console.RequestHolder;
import com.begcode.report.core.exception.ReportException;
import com.begcode.report.core.utils.SpringContextUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * @author Jacky.gao
 * @since 2017年9月6日
 */
public class TempObjectCache {

    private static Logger logger = LoggerFactory.getLogger(TempObjectCache.class);

    private static TempObjectCache tempObjectCache = new TempObjectCache();
    private Map<String, ObjectMap> sessionMap = new HashMap<String, ObjectMap>();

    private static boolean enableRedis = CacheProperties.isEnableRedis();

    private static int cacheExpire = CacheProperties.getCacheExpire();

    private RedisTemplate redisTemplate = SpringContextUtils.getBean(RedisTemplate.class, "redisTemplate");

    public static Object getObject(String key) {
        if (enableRedis) {
            try {
                return tempObjectCache.getRedisData(key);
            } catch (Exception e) {
                throw new ReportException("redis错误，请检查：" + e.getMessage());
            }
        }
        return tempObjectCache.get(key);
    }

    public static void putObject(String key, Object obj) {
        if (enableRedis) {
            try {
                tempObjectCache.storeRedis(key, obj);
            } catch (Exception e) {
                throw new ReportException("redis错误，请检查：" + e.getMessage());
            }

            return;
        }
        tempObjectCache.store(key, obj);
    }

    public static void removeObject(String key) {
        tempObjectCache.remove(key);
    }

    public void remove(String key) {
        HttpServletRequest req = RequestHolder.getRequest();
        if (req == null) {
            return;
        }
        ObjectMap mapObject = getReportMap(req);
        if (mapObject != null) {
            mapObject.remove(key);
        }
    }

    public Object get(String key) {
        HttpServletRequest req = RequestHolder.getRequest();
        if (req == null) {
            return null;
        }
        ObjectMap mapObject = getReportMap(req);
        return mapObject.get(key);
    }

    public void store(String key, Object obj) {
        HttpServletRequest req = RequestHolder.getRequest();
        if (req == null) {
            return;
        }
        ObjectMap mapObject = getReportMap(req);
        mapObject.put(key, obj);
    }

    private ObjectMap getReportMap(HttpServletRequest req) {
        List<String> expiredList = new ArrayList<>();
        logger.info(sessionMap.keySet().toString());
        for (String key : sessionMap.keySet()) {
            ObjectMap reportObj = sessionMap.get(key);
            if (reportObj.isExpired()) {
                expiredList.add(key);
            }
        }
        for (String key : expiredList) {
            sessionMap.remove(key);
        }
        String sessionId = req.getSession().getId();
        ObjectMap obj = sessionMap.get(sessionId);
        if (obj != null) {
            return obj;
        } else {
            ObjectMap mapObject = new ObjectMap();
            sessionMap.put(sessionId, mapObject);
            return mapObject;
        }
    }

    private void storeRedis(String key, Object obj) {
        redisTemplate.opsForValue().set(key, obj, cacheExpire, TimeUnit.SECONDS);
    }

    private Object getRedisData(String key) {
        return redisTemplate.opsForValue().get(key);
    }

}
