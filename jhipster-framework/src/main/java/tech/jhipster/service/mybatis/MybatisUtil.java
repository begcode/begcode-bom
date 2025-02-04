package tech.jhipster.service.mybatis;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.support.LambdaMeta;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.toolkit.JdbcUtils;
import com.diboot.core.exception.BusinessException;
import com.diboot.core.util.ContextHolder;

import java.util.Optional;
import java.util.function.Function;

import static java.lang.String.format;

public class MybatisUtil {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MybatisUtil.class);
    public static DbType getDatabaseTypeEnum() {
        String jdbcUrl = ContextHolder.getJdbcUrl();
        DbType dbType = JdbcUtils.getDbType(jdbcUrl);
        if (dbType == null) {
            throw new BusinessException("无法识别的数据库类型: " + jdbcUrl);
        }
        return dbType;
    }

    public static <T> String columnToString(SFunction<T, ?> func) {
        LambdaMeta lambdaMeta = LambdaUtils.extract(func);
        return lambdaMeta.getImplMethodName();
    }

    public static <T, R> R tryCatch(T t, Function<T, R> function) {
        TimeInterval timer = DateUtil.timer();
        R r = null;
        try {
            r = function.apply(t);
        } catch (Exception e) {
            log.error(format("内部方法Function调用异常,错误信息:%s",e.getMessage()), e);
        }
        log.info("内部方法Function调用,耗时:{}ms", timer.interval());
        return r;
    }

    public static <T> String columnToUnderline(SFunction<T, ?> func) {
        String fieldName = tryCatch(func, MybatisUtil::columnToString);
        return Optional.ofNullable(fieldName).map(CharSequenceUtil::toUnderlineCase).orElse(CharSequenceUtil.EMPTY);
    }
}

