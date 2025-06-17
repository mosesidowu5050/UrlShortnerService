package com.mosesidowu.urlShortnerService.exception;

public class UrlDoesNotExistException extends UrlRuntimeException{
    public UrlDoesNotExistException(String message) {
        super(message);
    }
}
