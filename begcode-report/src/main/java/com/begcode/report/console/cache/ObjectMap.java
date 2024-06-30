
package com.begcode.report.console.cache;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ObjectMap {
    private final int MAX_ITEM = 3;
    private static final int MILLISECOND = 300000;//default expired time is 5 minutes.
    private Map<String, Object> map = new LinkedHashMap<String, Object>();
    private long start;

    public ObjectMap() {
        this.start = System.currentTimeMillis();
    }

    public void put(String key, Object obj) {
        this.start = System.currentTimeMillis();
        if (map.containsKey(key)) {
            map.remove(key);
        } else {
            if (map.size() > MAX_ITEM) {
                String lastFile = null;
                for (Iterator<Entry<String, Object>> it = map.entrySet().iterator(); it.hasNext(); ) {
                    Entry<String, Object> entry = it.next();
                    lastFile = entry.getKey();
                }
                map.remove(lastFile);
            }
        }
        map.put(key, obj);
    }

    public Object get(String key) {
        this.start = System.currentTimeMillis();
        return this.map.get(key);
    }

    public void remove(String key) {
        this.map.remove(key);
    }

    public boolean isExpired() {
        long end = System.currentTimeMillis();
        long value = end - start;
        if (value >= MILLISECOND) {
            return true;
        }
        return false;
    }
}
