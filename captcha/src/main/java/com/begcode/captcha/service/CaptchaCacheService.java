package com.begcode.captcha.service;

/**
 * 验证码缓存接口
 * @author Raod
 * @date 2018-08-21
 */
public interface CaptchaCacheService {

	void set(String key, String value, long expiresInSeconds);

	boolean exists(String key);

	void delete(String key);

	String get(String key);

	/**
	 * 缓存类型-local/redis/memcache/..
	 * 通过java SPI机制，接入方可自定义实现类
	 * @return
	 */
	String type();

	/***
	 *
	 * @param key
	 * @param val
	 * @return
	 */
	default Long increment(String key, long val){
		return 0L;
	};

}
