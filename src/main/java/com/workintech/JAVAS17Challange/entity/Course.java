package com.workintech.JAVAS17Challange.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Course {

    private Integer id;
    private String name;
    private Integer credit;
    private Grade grade;

}
