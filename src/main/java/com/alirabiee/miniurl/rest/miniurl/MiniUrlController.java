package com.alirabiee.miniurl.rest.miniurl;

import com.alirabiee.miniurl.service.miniurl.MiniUrlService;
import com.alirabiee.sys.service.exception.NotFoundException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by ali.
 *
 * This controller is responsible to handle the home screen and shortened URLs.
 */
@Controller
@RequestMapping
@Log
public class MiniUrlController {

    @Autowired
    private MiniUrlService miniUrlService;

    @RequestMapping
    public String home() {
        return "home";
    }

    @RequestMapping( "/{encodedId:.*}" )
    public String rest(@PathVariable final String encodedId, final HttpServletResponse response) {
        try {
            final String url = miniUrlService.findUrl( encodedId );

            response.setStatus( 301 );
            response.addHeader( "Location", url );

            return "redirect";
        }
        catch ( NotFoundException e ) {
            response.setStatus( 404 );

            return "home";
        }
    }
}
