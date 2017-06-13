package com.virjar.sipsoup.exception;

/**
 * Created by virjar on 17/6/13.
 */
public class ObjectCreateException extends RuntimeException {

    public ObjectCreateException(Throwable cause) {
        super(cause);
    }

    public ObjectCreateException(String message) {
        super(message);
    }

    public ObjectCreateException(String message, Throwable cause) {
        super(message, cause);
    }

    protected ObjectCreateException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
