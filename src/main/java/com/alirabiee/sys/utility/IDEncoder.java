package com.alirabiee.sys.utility;

import com.alirabiee.sys.service.exception.ValidationException;

/**
 * This class provides utilities to encode/decode IDs.
 */
public class IDEncoder {

    /**
     * The alphabet is in the set [a-zA-Z0-9_-] with the exclusion of [i]. It is randomized to prevent guessing the
     * sequence. The reason for the exclusion is to prevent accidental generation a URL suffix that matches
     * system service endpoints.
     */
    private static final String ALPHABET = "trI6ZQSVB-XbkTD3CwAngFmU02csWf9Pe1px5oaLhGqv8OJyN7djK_zlHEYuM4R";

    private static final int BASE = ALPHABET.length();

    /**
     * Encodes the number in base 10 to a number in base 63
     *
     * @param num
     * @return
     */
    public static String encode(long num) {
        final StringBuilder str = new StringBuilder();

        while ( num > 0 ) {
            str.insert( 0, ALPHABET.charAt( ( int ) ( num % BASE ) ) );
            num = num / BASE;
        }

        return str.toString();
    }

    /**
     * Decodes the number in base 63 back to a number in base 10
     *
     * @param str
     * @return
     * @throws ValidationException
     */
    public static long decode(String str) throws ValidationException {
        long num = 0;

        try {
            for ( int i = 0; i < str.length(); i++ ) {
                num = num * BASE + ALPHABET.indexOf( str.charAt( i ) );
            }
        }
        catch ( Exception e ) {
            throw new ValidationException();
        }

        return num;
    }

}