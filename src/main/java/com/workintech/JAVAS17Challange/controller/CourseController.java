package com.workintech.JAVAS17Challange.controller;

import com.workintech.JAVAS17Challange.entity.*;
import com.workintech.JAVAS17Challange.exceptions.CourseException;
import com.workintech.JAVAS17Challange.validation.CourseValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/courses")
@CrossOrigin("*")
public class CourseController {

    private List<Course> courses;

    private final CourseGpa lowCourseGpa;
    private final CourseGpa mediumCourseGpa;
    private final CourseGpa highCourseGpa;

    public CourseController(@Qualifier("lowCourseGpa") CourseGpa lowCourseGpa,@Qualifier("mediumCourseGpa") CourseGpa mediumCourseGpa,@Qualifier("highCourseGpa") CourseGpa highCourseGpa) {
        this.lowCourseGpa = lowCourseGpa;
        this.mediumCourseGpa = mediumCourseGpa;
        this.highCourseGpa = highCourseGpa;
    }

    @PostConstruct
    public void init(){
        courses = new ArrayList<>();

    }



    @GetMapping("/")
    public List<Course> findAll (){
        return this.courses;
    }

    @GetMapping("/{name}")
    public Course findByName (@PathVariable String name){

        CourseValidation.checkName(name);
            return courses.stream()
                    .filter(course-> course.getName().equalsIgnoreCase(name))
                    .findFirst()
                    .orElseThrow(()->new CourseException(CourseValidation.notExistName(name), HttpStatus.NOT_FOUND));

    }
    @PostMapping("/")
    public ResponseEntity<ApiResponse>  save (@RequestBody Course course){
        CourseValidation.checkName(course.getName());
        CourseValidation.checkCredit(course.getCredit());
        courses.add(course);
        Integer totalGpa = getTotalGpa(course);
        ApiResponse apiResponse = new ApiResponse(course,totalGpa);
        return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
    }

    private Integer getTotalGpa(Course course) {

        if (course.getCredit()<=2){
            return course.getGrade().getCoefficient() * course.getCredit() * lowCourseGpa.getGpa();
        }
        else if (course.getCredit() == 3){
            return course.getGrade().getCoefficient() * course.getCredit() * mediumCourseGpa.getGpa();
        }
        else if (course.getCredit() == 4){
            return course.getGrade().getCoefficient() * course.getCredit() * highCourseGpa.getGpa();
        }
        else return null;


    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update (@PathVariable Integer id ,@RequestBody Course course){

        CourseValidation.checkCredit(course.getCredit());
        CourseValidation.checkName(course.getName());
        CourseValidation.checkId(id);

        Course existingCourse= courses.stream()
                .filter(c-> c.getId().equals(id))
                .findFirst()
                .orElseThrow(()->new CourseException(CourseValidation.notExistId(id),HttpStatus.NOT_FOUND));

        int IndexOfExistingCourse =  courses.indexOf(existingCourse);
        course.setId(id);
        courses.set(IndexOfExistingCourse,course);
        Integer totalGpa =  getTotalGpa(course);
        ApiResponse apiResponse = new ApiResponse(courses.get(IndexOfExistingCourse),totalGpa);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public Course delete (@PathVariable Integer id){
        CourseValidation.checkId(id);

        Course existingCourse = courses.stream()
                .filter(c-> c.getId().equals(id))
                .findFirst()
                .orElseThrow(()->new CourseException(CourseValidation.notExistId(id),HttpStatus.NOT_FOUND));
        courses.remove(existingCourse);
        return existingCourse;


    }


}
