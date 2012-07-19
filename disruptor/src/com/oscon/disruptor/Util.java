package com.oscon.disruptor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    
    final static Pattern numeric = Pattern.compile("\\p{Digit}+");

    /**
     * Returns {@code true} if the string is non-null and non-empty,
     * {@code false} otherwise.
     *
     * @param s String, may be null
     * @return boolean
     */
    public static boolean hasLength(final String s) {                                                                               
        return s != null && !s.isEmpty();
    }
    
    /**  
     * Returns {@code true} if {@link String s} is numeric (i.e., the
     * {@code Digit} POSIX character class) characters and nothing else.
     *
     * @param s {@link String}
     * @return boolean
     */
    public static boolean isNumeric(String s) { 
        if (!hasLength(s)) {
            return false;
        }    
        
        final Matcher m = numeric.matcher(s);
        return m.matches();
    }
}
