package com.alirabiee.sys.service;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Created by ali on 2/19/17.
 */
public abstract class BaseService {
    private final ThreadPoolTaskExecutor executor;

    protected BaseService() {
        executor = new ThreadPoolTaskExecutor();

        final int corePoolSize = Runtime.getRuntime().availableProcessors();

        executor.setCorePoolSize( corePoolSize );
        executor.setMaxPoolSize( corePoolSize );
        executor.setQueueCapacity( corePoolSize * 100 );
        executor.setThreadNamePrefix( "ServicePool-" + this.getClass().getName() + "-" );

        executor.initialize();
    }

    protected < T > Future< T > runTask(final Callable< T > task) {
        return executor.submit( task );
    }
}
