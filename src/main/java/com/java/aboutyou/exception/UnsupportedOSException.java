package com.java.aboutyou.exception;

/**
 * Class<code> UnsupportedOSException</> represent an exception that should be thrown
 * when program is executed on not suitable Operation System for current project
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 02-06-2017
 */
public class UnsupportedOSException extends RuntimeException {

    public UnsupportedOSException(String massage){
        super(massage);
    }
}
