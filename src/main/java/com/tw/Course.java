package com.tw;

/**
 * Created by QinShuqian on 2019/4/2.
 * 课程类
 */
public class Course {
    private String courseName;

    private double courseGrade;

    public Course(String courseName, int courseGrade) {
        this.courseName = courseName;
        this.courseGrade = courseGrade;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getCourseGrade() {
        return courseGrade;
    }

    public void setCourseGrade(double courseGrade) {
        this.courseGrade = courseGrade;
    }
}
