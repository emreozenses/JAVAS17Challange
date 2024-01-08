package com.workintech.JAVAS17Challange.validation;

import com.workintech.JAVAS17Challange.exceptions.CourseErrorResponse;
import com.workintech.JAVAS17Challange.exceptions.CourseException;
import org.springframework.http.HttpStatus;

public class CourseValidation {


    private static final String COURSE_IS_NOT_VALID = "The requested course name is not valid ";
    private static final String COURSE_IS_NOT_EXIST = "The requested course is not exist";
    private static final String CREDIT_IS_NOT_APPROPRIATE = "NA format";
    private static final String ID_IS_NOT_VALID = "Id is not valid";
    private static final String ID_IS_NOT_EXIST = "Id is not exist";
    public static void checkName(String name){
        if(name == null || name.isEmpty()){
            throw new CourseException(COURSE_IS_NOT_VALID, HttpStatus.BAD_REQUEST);
        }
    }
    public static String notExistName(String name){

            throw new CourseException(COURSE_IS_NOT_EXIST, HttpStatus.NOT_FOUND);

    }

    public static void checkCredit(Integer credit) {

        if (credit == null|| credit<0||credit>4)
            throw new CourseException(CREDIT_IS_NOT_APPROPRIATE,HttpStatus.BAD_REQUEST);
    }

    public static void checkId (Integer id){
        if ( id <0 || id==null ){
            throw new CourseException(ID_IS_NOT_VALID,HttpStatus.BAD_REQUEST);
        }
    }

    public static String notExistId(Integer id) {

            throw new CourseException(ID_IS_NOT_EXIST,HttpStatus.NOT_FOUND);

    }
}
