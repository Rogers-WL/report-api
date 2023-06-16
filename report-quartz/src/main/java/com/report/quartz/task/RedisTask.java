package com.report.quartz.task;

import com.report.common.core.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RedisTask {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 保持redis存活
     *
     * @author wl
     * @date 2023/5/28
     */
    @Scheduled(fixedDelay = 5000, initialDelay = 10 * 1000)
    private  void keepRedisAlive() {
        redisUtil.hasKey("alive");
    }
}
