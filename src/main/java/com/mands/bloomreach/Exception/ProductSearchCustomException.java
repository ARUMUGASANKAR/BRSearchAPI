package com.mands.bloomreach.Exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ProductSearchCustomException extends Exception{

    private static final long serialVersionUID = 1L;

    public ProductSearchCustomException(String message) {
        super(message);
    }

    public ProductSearchCustomException(String message, Exception e) {
        super(message, e);
    }
}
