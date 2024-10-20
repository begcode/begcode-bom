package com.begcode.captcha.service;

import com.begcode.captcha.model.common.ResponseModel;
import com.begcode.captcha.model.vo.CaptchaVO;

import java.util.Properties;

/**
 * 验证码服务接口
 */
public interface CaptchaService {
    /**
     * 配置初始化
     */
    void init(Properties config);

    /**
     * 获取验证码
     * @param captchaVO CaptchaVO
     */
    ResponseModel get(CaptchaVO captchaVO);

    /**
     * 核对验证码(前端)
     * @param captchaVO captchaVO
     */
    ResponseModel check(CaptchaVO captchaVO);

    /**
     * 二次校验验证码(后端)
     * @param captchaVO captchaVO
     */
    ResponseModel verification(CaptchaVO captchaVO);

    /***
     * 验证码类型
     * 通过java SPI机制，接入方可自定义实现类，实现新的验证类型
     */
    String captchaType();

	/**
	 * 历史资源清除(过期的图片文件，生成的临时图片...)
	 * @param config 配置项 控制资源清理的粒度
	 */
	void destroy(Properties config);

}
