
package com.begcode.report.console.cache;

import com.begcode.report.console.RequestHolder;
import com.begcode.report.core.cache.ReportCache;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Jacky.gao
 * @since 2017年3月8日
 */
@Component
public class HttpSessionReportCache implements ReportCache {
    private Map<String, ObjectMap> sessionReportMap = new HashMap<String, ObjectMap>();

    @Override
    public Object getObject(String file) {
        HttpServletRequest req = RequestHolder.getRequest();
        if (req == null) {
            return null;
        }
        ObjectMap objMap = getObjectMap(req);
        return objMap.get(file);
    }

    @Override
    public void storeObject(String file, Object object) {
        HttpServletRequest req = RequestHolder.getRequest();
        if (req == null) {
            return;
        }
        ObjectMap map = getObjectMap(req);
        map.put(file, object);
    }

    @Override
    public boolean disabled() {
        return !CacheProperties.isEnableRedis();
    }

    private ObjectMap getObjectMap(HttpServletRequest req) {
        List<String> expiredList = new ArrayList<String>();
        for (String key : sessionReportMap.keySet()) {
            ObjectMap reportObj = sessionReportMap.get(key);
            if (reportObj.isExpired()) {
                expiredList.add(key);
            }
        }
        for (String key : expiredList) {
            sessionReportMap.remove(key);
        }
        String sessionId = req.getSession().getId();
        ObjectMap obj = sessionReportMap.get(sessionId);
        if (obj != null) {
            return obj;
        } else {
            ObjectMap objMap = new ObjectMap();
            sessionReportMap.put(sessionId, objMap);
            return objMap;
        }
    }
}
