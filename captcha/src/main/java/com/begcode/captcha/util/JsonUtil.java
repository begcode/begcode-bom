package com.begcode.captcha.util;

import com.begcode.captcha.model.vo.PointVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 替换掉fastjson，自定义实现相关方法
 * note: 该实现不具有通用性，仅用于本项目。
 *
 */
public class JsonUtil {
	private static Logger log = LoggerFactory.getLogger(JsonUtil.class);
	public static List<PointVO> parseArray(String text, Class<PointVO> clazz) {
		if (text == null) {
			return null;
		} else {
			String[] arr = text.replaceFirst("\\[","")
					.replaceFirst("\\]","").split("\\}");
			List<PointVO> ret = new ArrayList<>(arr.length);
			for (String s : arr) {
				ret.add(parseObject(s,PointVO.class));
			}
			return ret;
		}
	}

	public static PointVO parseObject(String text, Class<PointVO> clazz) {
		if(text == null) {
			return null;
		}
		/*if(!clazz.isAssignableFrom(PointVO.class)) {
			throw new UnsupportedOperationException("不支持的输入类型:"
					+ clazz.getSimpleName());
		}*/
		try {
			PointVO ret = clazz.newInstance();
			return ret.parse(text);
		}catch (Exception ex){
			log.error("json解析异常", ex);

		}
		return null;
	}

	public static String toJSONString(Object object) {
		if(object == null) {
			return "{}";
		}
		if(object instanceof PointVO){
			PointVO t = (PointVO)object;
			return t.toJsonString();
		}
		if(object instanceof List){
			List<PointVO> list = (List<PointVO>)object;
			StringBuilder buf = new StringBuilder("[");
			list.stream().forEach(t->{
				buf.append(t.toJsonString()).append(",");
			});
			return buf.deleteCharAt(buf.lastIndexOf(",")).append("]").toString();
		}
		if(object instanceof Map){
			return ((Map)object).entrySet().toString();
		}
		throw new UnsupportedOperationException("不支持的输入类型:"
				+object.getClass().getSimpleName());
	}
}
