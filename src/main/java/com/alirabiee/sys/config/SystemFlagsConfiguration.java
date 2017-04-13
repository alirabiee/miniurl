package com.alirabiee.sys.config;

import org.springframework.stereotype.Component;

/**
 * Created by ali.
 *
 * This component maintains global system operation flags which are deduced from various settings.
 */
@Component
public class SystemFlagsConfiguration {
    private Boolean isTestMode = null;

    /**
     * Checks whether the application is running in test mode. Useful for deactivating certain features while
     * load-testing.
     *
     * @return true if in production mode
     */
    public boolean isProductionMode() {
        return !isTestMode();
    }

    /**
     * Checks whether the application is running in test mode. Useful for deactivating certain features while
     * load-testing.
     *
     * @return true if in test mode
     */
    public boolean isTestMode() {
        if ( isTestMode == null ) {
            final String operationMode = System.getProperty( "operationMode" );

            isTestMode = operationMode != null && "test".equalsIgnoreCase( operationMode );
        }

        return isTestMode;
    }
}
