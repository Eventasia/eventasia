package com.github.eventasia.eventstore.aspect;

import org.aspectj.lang.Signature;

public class HandlerSignatureException extends RuntimeException {

    public HandlerSignatureException(final String message, final Class<?> returnType, final Class<?> allowedType, final Signature signature) {
        super(getMessage(message, returnType, allowedType, signature));
    }

    public HandlerSignatureException(final String message, final Class<?> returnType, final Class<?> allowedType, final Signature signature, final Throwable throwable) {
        super(getMessage(message, returnType, allowedType, signature), throwable);
    }

    private static String getMessage(final String message, final Class<?> returnType, final Class<?> allowedType, final Signature signature) {
        return "message='" + message + "', signature='" + signature + "', allowedTypes='" + allowedType + " and Array and Iterable from this type.'";
    }

}
