package com.workintech.JAVAS17Challange.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<CourseErrorResponse> handleException(CourseException courseException){
        log.error("CourseException occurs! Exception details : ", courseException.getMessage());
        CourseErrorResponse errorResponse;
        errorResponse = new CourseErrorResponse(courseException.getStatus().value(), courseException.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse,courseException.getStatus());
    }
    @ExceptionHandler
    public ResponseEntity<CourseErrorResponse> handleException(Exception exception){
        log.error("Exception occurs! Exception details : ", exception.getMessage());
        CourseErrorResponse errorResponse;
        errorResponse = new CourseErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }



}
