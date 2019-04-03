package com.tw;

import java.util.ArrayList;
import java.util.List;

import static com.tw.Util.replaceZero;

/**
 * Created by QinShuqian on 2019/4/2.
 * 学生类
 */
public class Student {
    private int id;

    private String name;

    private ArrayList<Course> courses;

    public Student(int id, String name, ArrayList<Course> courses) {
        this.id = id;
        this.name = name;
        this.courses = courses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public double getGradeSum(){
        return courses.stream().mapToDouble(Course::getCourseGrade).sum();
    }

    public double getGeradeAverage(){
        return getGradeSum()/4.0;
    }

    public String printGrade(){
        double math = 0;
        double chinese = 0;
        double english = 0;
        double program = 0;
        for (Course course:courses) {
            if(course.getCourseName().equals(Constant.COURSE_LlST[0])){
                math = course.getCourseGrade();
            }else if(course.getCourseName().equals(Constant.COURSE_LlST[1])){
                chinese = course.getCourseGrade();
            }else if(course.getCourseName().equals(Constant.COURSE_LlST[2])){
                english = course.getCourseGrade();
            }else if(course.getCourseName().equals(Constant.COURSE_LlST[3])){
                program = course.getCourseGrade();
            }
        }
        return name + "|" + replaceZero(math) + "|" + replaceZero(chinese) + "|" + replaceZero(english) + "|" + replaceZero(program) + "|" + replaceZero(getGeradeAverage())
                + "|" + replaceZero(getGradeSum());
    }
}
