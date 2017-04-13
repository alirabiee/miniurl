package com.alirabiee.sys.utility;

import com.alirabiee.sys.service.exception.ValidationException;

public class IDEncoder {

    private static final String ALPHABET = "trI6ZQSVB-XbkTD3CwAngFmU02csWf9Pe1px5oaLhGqv8OJyN7djK_zlHEYuM4R";

    private static final int BASE = ALPHABET.length();

    public static String encode(long num) {
        final StringBuilder str = new StringBuilder();

        while ( num > 0 ) {
            str.insert( 0, ALPHABET.charAt( ( int ) ( num % BASE ) ) );
            num = num / BASE;
        }

        return str.toString();
    }

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