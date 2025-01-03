package tech.jhipster.service.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.diboot.core.binding.query.BindQuery;
import tech.jhipster.service.Criteria;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
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
}
