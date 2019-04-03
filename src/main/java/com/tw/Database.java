package com.tw;

import java.util.ArrayList;

/**
 * Created by QinShuqian on 2019/4/2.
 * 模拟数据库
 */
public class Database {
    private ArrayList<Student> students = new ArrayList<>();

//    获取所有学生
    public ArrayList<Student> getStudents(){
        return students;
    }

//    新增学生
    public void addStudent(Student student){
        students.add(student);
    }


}
