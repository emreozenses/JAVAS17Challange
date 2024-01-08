package com.workintech.JAVAS17Challange.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseErrorResponse {

    private int status;
    private String message;
    private long timestamp;


}
