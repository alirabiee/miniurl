package com.alirabiee.sys.config;

import org.springframework.stereotype.Component;

/**
 * Created by A on 2017-04-13.
 */
@Component
public class SystemFlagsConfiguration {
    private Boolean isTestMode = null;

    public boolean isProductionMode() {
        return !isTestMode();
    }

    public boolean isTestMode() {
        if ( isTestMode == null ) {
            final String operationMode = System.getProperty( "operationMode" );

            isTestMode = operationMode != null && "test".equalsIgnoreCase( operationMode );
        }

        return isTestMode;
    }
}
