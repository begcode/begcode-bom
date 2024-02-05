/*******************************************************************************
 * Copyright 2017 Bstek
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.begcode.ureport.core.cache;


import com.begcode.ureport.console.cache.CacheProperties;
import com.begcode.ureport.core.definition.ReportDefinition;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Jacky.gao
 * @since 2016年12月4日
 */
@Component
public class DefaultMemoryReportDefinitionCache implements ReportDefinitionCache {
    private Map<String, ReportDefinition> reportMap = new ConcurrentHashMap<String, ReportDefinition>();

    @Override
    public ReportDefinition getReportDefinition(String file) {
        return reportMap.get(sessionId() + file);
    }

    @Override
    public void cacheReportDefinition(String file, ReportDefinition reportDefinition) {
        if (reportMap.containsKey(sessionId() + file)) {
            reportMap.remove(sessionId() + file);
        }
        reportMap.put(sessionId() + file, reportDefinition);
    }

    @Override
    public void removeReportDefinition(String file) {
        reportMap.remove(sessionId() + file);
    }

    @Override
    public boolean disabled() {
        return !CacheProperties.isEnableRedis();
    }
}
