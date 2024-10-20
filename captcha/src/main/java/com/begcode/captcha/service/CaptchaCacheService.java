package com.begcode.captcha.service;

/**
 * 验证码缓存接口
 * @author Raod
 */
public interface CaptchaCacheService {

	void set(String key, String value, long expiresInSeconds);

	boolean exists(String key);

	void delete(String key);

	String get(String key);

	/**
	 * 缓存类型-local/redis/memcache/..
	 * 通过java SPI机制，接入方可自定义实现类
	 */
	String type();

	/***
	 *
	 * @param key Key
	 * @param val Val
	 */
	default Long increment(String key, long val){
		return 0L;
	};

}
