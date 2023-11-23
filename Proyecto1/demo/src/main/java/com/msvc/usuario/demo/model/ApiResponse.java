package com.msvc.usuario.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiResponse {

    private String message;
    private boolean success;
    private HttpStatus status;
}
