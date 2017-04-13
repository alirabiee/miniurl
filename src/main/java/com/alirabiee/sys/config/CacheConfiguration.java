package com.alirabiee.sys.config;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Created by ali on 2/19/17.
 */
@EnableCaching
@Configuration
public class CacheConfiguration extends CachingConfigurerSupport {
    public final static String DATA_CACHE = "dataCache";
    public final static String KEY_GENERATOR = "keyGenerator";

    @Override
    public CacheManager cacheManager() {
        return new GuavaCacheManager();
    }

    @Bean
    public Cache cacheOne() {
        return new GuavaCache(
            DATA_CACHE,
            CacheBuilder.newBuilder()
                        .expireAfterWrite( 1, TimeUnit.HOURS )
                        .build()
        );
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return (o, method, params) -> {
            final StringBuilder sb = new StringBuilder();

            sb.append( o.getClass().getName() );
            sb.append( method.getName() );

            for ( Object param : params ) {
                sb.append( param.toString() );
            }

            return sb.toString();
        };
    }
}
