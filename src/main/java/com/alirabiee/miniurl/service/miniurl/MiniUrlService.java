package com.alirabiee.miniurl.service.miniurl;

import com.alirabiee.sys.service.exception.NotFoundException;
import com.alirabiee.sys.service.exception.ValidationException;

/**
 * Created by ali on 4/12/17.
 */
public interface MiniUrlService {
    /**
     * Minifies the specified URL to a suffix or throws a {@link ValidationException} if it is invalid.
     *
     * @param url
     * @return The minified URL suffix
     * @throws ValidationException
     */
    String minify(String url) throws ValidationException;

    /**
     * Looks up the shortened URL and returns the original URL.
     * @param encodedId The shortened suffix
     * @return The original URL
     * @throws NotFoundException
     */
    String findUrl(String encodedId) throws NotFoundException;
}
