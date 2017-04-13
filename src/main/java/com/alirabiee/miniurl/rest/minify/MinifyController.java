package com.alirabiee.miniurl.rest.minify;

import com.alirabiee.miniurl.service.miniurl.MiniUrlService;
import com.alirabiee.sys.constant.SystemErrorCode;
import com.alirabiee.sys.service.exception.ValidationException;
import com.alirabiee.sys.utility.captcha.CaptchaAPI;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;

/**
 * Created by ali on 4/12/17.
 *
 * This controller is responsible to handle URL shortening requests.
 */
@Controller
@RequestMapping( "/minify" )
@Log
public class MinifyController {

    @Autowired
    private CaptchaAPI captchaAPI;

    @Autowired
    private MiniUrlService miniUrlService;

    @RequestMapping( method = RequestMethod.POST )
    public ModelAndView minify(@RequestParam final String url,
                                           @RequestParam( "g-recaptcha-response" ) final String captcha,
                                           final HttpServletResponse response) throws IOException {
        if ( log.isLoggable( Level.FINE ) ) log.fine( "url = " + url );
        if ( log.isLoggable( Level.FINE ) ) log.fine( "captcha = " + captcha );

        // Verify the user's captcha
        final boolean captchaSuccess = captchaAPI.verify( captcha );

        if ( log.isLoggable( Level.FINE ) ) log.fine( "captchaSuccess = " + captchaSuccess );

        response.addHeader( "Cache-Control", "no-cache, no-store, must-revalidate" );
        response.addHeader( "Pragma", "no-cache" );
        response.addHeader( "Expires", "0" );

        if ( captchaSuccess ) {
            try {
                final String minified = miniUrlService.minify( url );

                if ( log.isLoggable( Level.FINE ) ) log.fine( "minified = " + minified );

                return new ModelAndView( "success", "minified", minified );
            }
            catch ( ValidationException ve ) {
                response.sendRedirect( "/?error=" + SystemErrorCode.VALIDATION_ERROR );
                return new ModelAndView( "home" );
            }
        }
        else {
            response.sendRedirect( "/?error=" + SystemErrorCode.CAPTCHA_ERROR );
            return new ModelAndView( "home" );
        }
    }
}
