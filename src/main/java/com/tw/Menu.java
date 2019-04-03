package com.tw;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.tw.Util.isNum;
import static com.tw.Util.replaceZero;

/**
 * Created by QinShuqian on 2019/4/2.
 */
public class Menu {
    Scanner scanner = new Scanner(System.in);
    Database db = new Database();

    public void init(){
        while (true){
            System.out.println(printMainMenu());

            String input = scanner.next();
            int command = 0;
            if(!isNum(input)){
                System.out.println("请输入正确的选项");
            }else {
                command = Integer.parseInt(input);
            }
            switch (command){
                case 1:
                    addStudent();
                    break;
                case 2:
                    printStudentGrade();
                    break;
                case 3:
                    exit();
                    break;
                default:
                    System.out.println("请输入正确的选项");
            }
        }
    }

    public String printMainMenu(){
       return  "1. 添加学生\n" +
                        "2. 生成成绩单\n" +
                        "3. 退出\n" +
                        "请输入你的选择（1～3）：\n";
    }

    public void addStudent(){
        System.out.println("请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：");
        String studentInfo = scanner.next();
        if(judgeAddStudentFormat(studentInfo)){
            System.out.println("学生" + db.getStudents().get(db.getStudents().size()-1).getName() + "的成绩被添加");
        }else {
            System.out.println("请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）：");
        }
    }

    public void printStudentGrade(){
        System.out.println("请输入要打印的学生的学号（格式： 学号,学号,...），按回车提交：");
        String studentIds = scanner.next();
        if(!judgePrintGradeFormat(studentIds)){
            System.out.println("请按正确的格式输入要打印的学生的学号（格式： 学号,学号,...），按回车提交：");
        }else {
            String output = "成绩单\n" +
                            "姓名|数学|语文|英语|编程|平均分|总分\n" +
                            "========================\n";
            String[] sids = studentIds.split(",");
            ArrayList<Integer> ids = new ArrayList();
            for (int i=0;i<sids.length;i++){
                ids.add(Integer.parseInt(sids[i]));
            }
            ArrayList<Student> allStudent = db.getStudents();
            List<Student> selectStudent = allStudent.stream().filter(student -> ids.contains(student.getId())).collect(Collectors.toList());
            double sum = selectStudent.stream().mapToDouble(Student::getGradeSum).sum();
            List<Double> sortedSum =  selectStudent.stream().mapToDouble(Student::getGradeSum).sorted().boxed().collect(Collectors.toList());
            double mid = sortedSum.size() % 2 == 0 ? (sortedSum.get(sortedSum.size()/2 - 1) + sortedSum.get(sortedSum.size()/21))/2 : sortedSum.get(sortedSum.size()/2);
            String gradeString = selectStudent.stream().map(Student::printGrade).collect(Collectors.joining("\n"));
            String classGradeString =  "全班总分平均数：" + replaceZero(sum) + "\n" +
                                       "全班总分中位数：" + replaceZero(mid);
            System.out.println(output + gradeString + classGradeString);
        }
    }

    public void exit(){
        System.exit(0);
    }

    public boolean judgeAddStudentFormat(String studentInfo){
        String[] infoList = studentInfo.split("，");
        if(infoList.length != 6){
            return false;
        }
        String studentId = infoList[1];
        String studentName = infoList[0];
        ArrayList<Course> studentCourses = new ArrayList();
//        判断学号
        if(!isNum(studentId)){
            return false;
        }
//        判断课程
        for (int i=2;i<infoList.length;i++){
            String[] courseInfo = infoList[i].split("：");

            if(courseInfo.length != 2){
                return false;
            }
            String courseName = courseInfo[0];
            String courseGrade = courseInfo[1];
//            判断课程名和课程分数
            if(!Arrays.asList(Constant.COURSE_LlST).contains(courseName) || !isNum(courseGrade)){
                return false;
            }
//            判断分数范围是否0-100
            if(Integer.parseInt(courseGrade) > 100 || Integer.parseInt(courseGrade) < 0){
                return false;
            }
            studentCourses.add(new Course(courseName,Integer.parseInt(courseGrade)));
        }
        db.addStudent(new Student(Integer.parseInt(studentId),studentName,studentCourses));
        return true;
    }



    public boolean judgePrintGradeFormat(String studentIds){
        String[] idList = studentIds.split(",");
        for (int i = 0;i<idList.length;i++){
            if(!isNum(idList[0])){
                return false;
            }
        }
        return true;
    }



}
