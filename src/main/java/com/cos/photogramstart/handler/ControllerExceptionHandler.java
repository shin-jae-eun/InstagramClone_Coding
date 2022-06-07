package com.cos.photogramstart.handler;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomValidationException;

@RestController
@ControllerAdvice //모든 exception을 낚아챈다. 
public class ControllerExceptionHandler {
    
    //runtime 익셉션이 발동하는 모든 익셉션을 가로챈다. 
    @ExceptionHandler(CustomValidationException.class)
    public Map<String,String> validationException(CustomValidationException e) {
        return e.getErrorMap();
    }
}
