package com.alirabiee.sys.config;

import lombok.extern.java.Log;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Created by ali on 2/19/17.
 */
@Configuration
@EnableAsync
@Log
public class AsyncConfiguration extends AsyncConfigurerSupport {

    @Override
    public Executor getAsyncExecutor() {
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        final int corePoolSize = Runtime.getRuntime().availableProcessors();

        executor.setCorePoolSize( corePoolSize );
        executor.setMaxPoolSize( corePoolSize * 4 );
        executor.setQueueCapacity( corePoolSize * 100 );
        executor.setThreadNamePrefix( "AyncMethod-" );

        log.info( "Async executor core pool size = " + executor.getCorePoolSize() );
        log.info( "Async executor max pool size = " + executor.getMaxPoolSize() );
        log.info( "Async executor queue capacity = " + corePoolSize * 100 );

        executor.initialize();

        return executor;
    }
}
