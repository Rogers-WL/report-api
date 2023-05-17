package com.report.monitor;

import com.report.common.core.domain.R;
import com.report.framework.web.domain.Server;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务器监控
 * 
 * @author rogers
 */
@RestController
@RequestMapping("/monitor/server")
public class ServerController
{
    @PreAuthorize("@ss.hasPermi('monitor:server:list')")
    @GetMapping()
    public R getInfo() throws Exception
    {
        Server server = new Server();
        server.copyTo();
        return R.success(server);
    }
}
