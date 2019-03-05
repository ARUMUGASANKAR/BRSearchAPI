package com.mands.bloomreach.Exception;

import com.mands.bloomreach.Model.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ProductSearchExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ Exception.class, HttpServerErrorException.class })
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ProductSearchCustomException.class, HttpClientErrorException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public final ResponseEntity<ExceptionResponse> handle404NotFound(Exception ex, WebRequest request)
    {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
