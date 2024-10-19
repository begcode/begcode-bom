package com.begcode.captcha.service.impl;

import com.begcode.captcha.model.common.Const;
import com.begcode.captcha.service.CaptchaCacheService;
import com.begcode.captcha.service.CaptchaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ServiceLoader;

/**
 * Created by raodeming on 2020/5/26.
 */
public class CaptchaServiceFactory {

    private static Logger logger = LoggerFactory.getLogger(CaptchaServiceFactory.class);

    public static CaptchaService getInstance(Properties config) {
        //先把所有CaptchaService初始化，通过init方法，实例字体等，add by Devli
        /*try{
            for(CaptchaService item: instances.values()){
                item.init(config);
            }
        }catch (Exception e){
            logger.warn("init captchaService fail:{}", e);
        }*/

        String captchaType = config.getProperty(Const.CAPTCHA_TYPE, "default");
        CaptchaService ret = instances.get(captchaType);
        if (ret == null) {
            throw new RuntimeException("unsupported-[captcha.type]=" + captchaType);
        }
        ret.init(config);
        return ret;
    }

    public static CaptchaCacheService getCache(String cacheType) {
        return cacheService.get(cacheType);
    }

    public volatile static Map<String, CaptchaService> instances = new HashMap();
    public volatile static Map<String, CaptchaCacheService> cacheService = new HashMap();

    static {
        ServiceLoader<CaptchaCacheService> cacheServices = ServiceLoader.load(CaptchaCacheService.class);
        for (CaptchaCacheService item : cacheServices) {
            cacheService.put(item.type(), item);
        }
        logger.info("supported-captchaCache-service:{}", cacheService.keySet().toString());
        ServiceLoader<CaptchaService> services = ServiceLoader.load(CaptchaService.class);
        for (CaptchaService item : services) {
            instances.put(item.captchaType(), item);
        }
        ;
        logger.info("supported-captchaTypes-service:{}", instances.keySet().toString());
    }
}
