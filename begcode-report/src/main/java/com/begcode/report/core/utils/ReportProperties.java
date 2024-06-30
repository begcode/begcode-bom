package com.begcode.report.core.utils;

import com.begcode.report.core.utils.oss.OssProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "ureport")
@Component
public class ReportProperties {

    private boolean disableHttpSessionReportCache;

    private boolean enableFileProvider;

    private String fileStoreDir;

    private boolean debug;

    /**
     * 开启redis缓存
     */
    private boolean enableRedis;

    /**
     * redis缓存过期时间 (单位：分钟)
     */
    private int cacheExpire;

    /**
     * 云储存启用
     */
    private boolean ossEnable;

    /**
     * 云储存配置
     */
    private OssProperties oss;

    public boolean isOssEnable() {
        return ossEnable;
    }

    public void setOssEnable(boolean ossEnable) {
        this.ossEnable = ossEnable;
    }

    public OssProperties getOss() {
        return oss;
    }

    public void setOss(OssProperties oss) {
        this.oss = oss;
    }

    public boolean isDisableHttpSessionReportCache() {
        return disableHttpSessionReportCache;
    }

    public void setDisableHttpSessionReportCache(boolean disableHttpSessionReportCache) {
        this.disableHttpSessionReportCache = disableHttpSessionReportCache;
    }

    public boolean isEnableFileProvider() {
        return enableFileProvider;
    }

    public void setEnableFileProvider(boolean enableFileProvider) {
        this.enableFileProvider = enableFileProvider;
    }

    public String getFileStoreDir() {
        return fileStoreDir;
    }

    public void setFileStoreDir(String fileStoreDir) {
        this.fileStoreDir = fileStoreDir;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public boolean isEnableRedis() {
        return enableRedis;
    }

    public void setEnableRedis(boolean enableRedis) {
        this.enableRedis = enableRedis;
    }

    public int getCacheExpire() {
        return cacheExpire;
    }

    public void setCacheExpire(int cacheExpire) {
        this.cacheExpire = cacheExpire;
    }
}
