package tech.jhipster.service.mybatis;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.diboot.core.binding.query.BindQuery;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.Filter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class CriteriaUtil {

    public static  <ENTITY>  QueryWrapper<ENTITY> build(boolean useOr, QueryWrapper<ENTITY> queryWrapper, Consumer<QueryWrapper<ENTITY>> consumer) {
        if (useOr) {
            return queryWrapper.or(true,consumer);
        } else {
            return queryWrapper.and(true,consumer);
        }
    }

    public static List<String> getNonNullBindQueryFields(Criteria criteria) {
        List<String> nonNullFields = new ArrayList<>();
        try {
            Field[] fields = criteria.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(BindQuery.class)) {
                    BindQuery bindQuery = field.getAnnotation(BindQuery.class);
                    if (bindQuery.ignore()) {
                        continue;
                    }
                    field.setAccessible(true);
                    Object value = field.get(criteria);
                    if (value != null) {
                        nonNullFields.add(field.getName());
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return nonNullFields;
    }

    public static Map<String, Map<String, Object>> getNonIgnoredAndNonNullFields(Criteria criteria) {
        // 将对象转换为 Map，包含所有字段及其值
        Map<String, Object> fieldMap = BeanUtil.beanToMap(criteria, false, true);
        Map<String, Map<String, Object>> result = new HashMap<>();
        fieldMap.forEach((key, value) -> {
            try {
                if (!(value instanceof Filter)) {
                    return;
                }
                Field field = criteria.getClass().getDeclaredField(key);
                field.setAccessible(true);

                // 检查字段是否有 @BindQuery 注解
                if (field.isAnnotationPresent(BindQuery.class)) {
                    BindQuery bindQuery = field.getAnnotation(BindQuery.class);
                    // 检查 @BindQuery 注解的 ignore 值，并且值非空
                    if (!bindQuery.ignore()) {
                        Map<String, Object> fieldTypeMap = new HashMap<>();
                        fieldTypeMap.put("value", value);
                        fieldTypeMap.put("bindQuery", bindQuery);
                        result.put(key, fieldTypeMap);
                    }
                }
            } catch (NoSuchFieldException e) {
                // 忽略异常，继续处理其他字段
            }
        });
        return result;
    }
}
