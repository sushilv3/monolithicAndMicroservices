package com.sushil.ecom_order_service.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;

public class MyCustomRuntimeException extends RuntimeException{
    private final HttpStatusCode statusCode;
    private final HttpHeaders headers;

    public MyCustomRuntimeException(HttpStatusCode statusCode, HttpHeaders headers) {
        this.statusCode = statusCode;
        this.headers = headers;
    }
}
