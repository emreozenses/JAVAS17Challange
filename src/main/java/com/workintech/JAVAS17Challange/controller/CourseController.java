package com.workintech.JAVAS17Challange.controller;

import com.workintech.JAVAS17Challange.entity.*;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courses")
public class CourseController {

    List<Course> courses;

    @PostConstruct
    public void init(){
        courses = new ArrayList<>();
        courses.add(new Course("Java",5,new Grade(10,"lakjsbd")));
    }

    public CourseController(List<Course> courses, @RequestBody LowCourseGpa lowCourseGpa, @RequestBody MediumCourseGpa mediumCourseGpa, @RequestBody HighCourseGpa highCourseGpa) {
        this.courses = courses;
    }

    @GetMapping("/")
    public List<Course> findAll (){
        return courses;
    }

    @GetMapping("/{name}")
    public Course findByName (@PathVariable String name){

      //TODO [Emre] check name
            return courses.get(courses.indexOf(name));

    }


}
