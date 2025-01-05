package tech.jhipster.service.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.toolkit.JdbcUtils;
import com.diboot.core.exception.BusinessException;
import com.diboot.core.util.ContextHolder;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Objects;

import static com.diboot.core.util.ContextHolder.getBean;

public class MybatisUtil {
    public static DbType getDatabaseTypeEnum() {
        String jdbcUrl = ContextHolder.getJdbcUrl();
        DbType dbType = JdbcUtils.getDbType(jdbcUrl);
        if (dbType == null) {
            throw new BusinessException("无法识别的数据库类型: " + jdbcUrl);
        }
        return dbType;
    }
}

