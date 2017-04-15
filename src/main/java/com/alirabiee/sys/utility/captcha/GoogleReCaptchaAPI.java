package com.alirabiee.sys.utility.captcha;

import com.alirabiee.sys.config.SystemFlagsConfiguration;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.logging.Level;

/**
 * Created by ali.
 *
 * This class proxies the reCAPTCHA API endpoints.
 */
@Component
@Log
public class GoogleReCaptchaAPI implements CaptchaAPI {

    @Autowired
    private SystemFlagsConfiguration flagsConfiguration;

    @Autowired
    private Environment environment;

    @Override
    public boolean verify(final String captcha) {
        if ( flagsConfiguration.isTestMode() ) {
            return true;
        }

        if ( captcha == null || captcha.trim().isEmpty() ) {
            return false;
        }

        try {
            final JsonNode body = Unirest.post( "https://www.google.com/recaptcha/api/siteverify" )
                                         .field( "secret", environment.getProperty( "recaptcha.secret-key" ) )
                                         .field( "response", captcha )
                                         .asJson()
                                         .getBody();

            if ( log.isLoggable( Level.FINE ) ) log.fine( "captcha response body = " + body );

            return body.getObject().optBoolean( "success" );
        }
        catch ( UnirestException e ) {
            log.severe( e.toString() );
        }

        return false;
    }
}
