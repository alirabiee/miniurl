package com.alirabiee.miniurl.service.miniurl;

import com.alirabiee.miniurl.data.miniurl.MiniUrl;
import com.alirabiee.miniurl.data.miniurl.MiniUrlRepository;
import com.alirabiee.sys.service.BaseService;
import com.alirabiee.sys.service.exception.NotFoundException;
import com.alirabiee.sys.service.exception.ValidationException;
import com.alirabiee.sys.utility.IDEncoder;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Level;

/**
 * Created by ali on 4/12/17.
 */
@Service
@Log
public class MiniUrlServiceImpl extends BaseService implements MiniUrlService {

    private static final long HASH_CODE_NORMALIZATION_DENOMINATOR = 20153;
    private static final long HASH_CODE_MAX_MAGNITUDE = 1000000;

    @Autowired
    private MiniUrlRepository repository;

    @Override
    public String minify(final String url) throws ValidationException {
        validate( url );

        MiniUrl miniUrl = repository.findFirstByOriginalUrl( url );

        if ( log.isLoggable( Level.FINE ) ) log.fine( "miniUrl = " + miniUrl );

        if ( miniUrl == null ) {
            miniUrl = MiniUrl.builder()
                             .originalUrl( url )
                             .hits( 0L )
                             .hashCode( normalizeHashCode( url.hashCode() ) )
                             .build();
            repository.save( miniUrl );
        }

        if ( log.isLoggable( Level.FINE ) ) log.fine( "miniUrl = " + miniUrl );

        return IDEncoder.encode( miniUrl.getId() * HASH_CODE_MAX_MAGNITUDE + miniUrl.getHashCode() );
    }

    private Integer normalizeHashCode(final int hashCode) {
        return Math.max( 1, Math.abs( hashCode / (int) HASH_CODE_NORMALIZATION_DENOMINATOR ) );
    }

    @Override
    public String findUrl(final String encodedId) throws NotFoundException {
        try {
            long decodedId = IDEncoder.decode( encodedId );

            final MiniUrl miniUrl = repository.findOne( decodedId / HASH_CODE_MAX_MAGNITUDE );

            if ( miniUrl == null ) {
                throw new NotFoundException();
            }

            // Validate the hash!
            if ( decodedId % HASH_CODE_MAX_MAGNITUDE != miniUrl.getHashCode() ) {
                throw new NotFoundException();
            }

            miniUrl.setHits( miniUrl.getHits() + 1 );

            return miniUrl.getOriginalUrl();
        }
        catch ( ValidationException | IndexOutOfBoundsException e ) {
            throw new NotFoundException();
        }
    }

    /**
     * Validates URL's syntax
     * @param url
     * @throws ValidationException
     */
    private void validate(final String url) throws ValidationException {
        if ( !url.matches( "https?:\\/\\/[a-zA-Z][-a-zA-Z0-9._]{1,256}" +
                           "\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&/=]*)" ) ) {
            throw new ValidationException();
        }
    }
}
