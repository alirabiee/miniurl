package com.alirabiee.miniurl.data.miniurl;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by ali.
 */
public interface MiniUrlRepository extends CrudRepository< MiniUrl, Long > {
    MiniUrl findFirstByOriginalUrl(String originalUrl);
}
