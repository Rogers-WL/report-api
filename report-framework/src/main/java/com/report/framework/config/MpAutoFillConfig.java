package com.report.framework.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.boot.SpringBootConfiguration;

import java.time.LocalDateTime;

/**
 * @author: rogers
 */
@Slf4j
@SpringBootConfiguration
public class MpAutoFillConfig implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.getValue("createBy") == null) {

            this.strictInsertFill(metaObject, "createBy", Long.class, 0L);
            this.strictInsertFill(metaObject, "createByName", String.class, "rogers");
        }
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.getValue("updateBy") == null ) {
            this.strictUpdateFill(metaObject, "updateBy", Long.class, 0L);
            this.strictUpdateFill(metaObject, "updateByName", String.class, "rogers");
        }
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
    }
}
