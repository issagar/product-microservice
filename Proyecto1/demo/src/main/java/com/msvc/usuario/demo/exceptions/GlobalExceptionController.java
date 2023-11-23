package com.msvc.usuario.demo.exceptions;

import com.msvc.usuario.demo.exceptions.ResourceNotFoundException;
import com.msvc.usuario.demo.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
        String message = resourceNotFoundException.getMessage();
        ApiResponse response = new ApiResponse();
        response.setMessage(message);
        response.setStatus(HttpStatus.NOT_FOUND);
        response.setSuccess(true);
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);

    }
}
