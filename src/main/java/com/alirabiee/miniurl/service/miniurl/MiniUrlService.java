package com.alirabiee.miniurl.service.miniurl;

import com.alirabiee.sys.service.exception.NotFoundException;
import com.alirabiee.sys.service.exception.ValidationException;

/**
 * Created by ali on 4/12/17.
 */
public interface MiniUrlService {
    String minify(String url) throws ValidationException;

    String findUrl(String encodedId) throws NotFoundException;
}
