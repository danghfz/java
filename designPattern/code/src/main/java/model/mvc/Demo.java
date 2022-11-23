package model.mvc;

import lombok.Data;

/**
 * @author danghf
 * @version 1.0
 * @data 2022/11/23 11:44
 * @since 1.8
 * MVC 模式
 */
public class Demo {
    public static void main(String[] args) {

        //从数据库获取学生记录
        Student model  = retrieveStudentFromDatabase();

        //创建一个视图：把学生详细信息输出到控制台
        StudentView view = new StudentView();

        StudentController controller = new StudentController(model, view);

        controller.updateView();

        //更新模型数据
        controller.setStudentName("John");

        controller.updateView();
    }

    private static Student retrieveStudentFromDatabase(){
        Student student = new Student();
        student.setName("Robert");
        student.setRollNo("10");
        return student;
    }
}

/**
 * Model
 */
@Data
class Student {
    private String rollNo;
    private String name;
}
/**
 * View
 */
class StudentView{
    public void printStudentDetails(Student s){
        System.out.println("Student: ");
        System.out.println("Name: " + s.getName());
        System.out.println("Roll No: " + s.getRollNo());
    }
}
/**
 * Controller
 * 绑定 model 和 view
 * 修改 model 然后修改 view
 */
class StudentController{
    private Student model;
    private StudentView view;

    public StudentController(Student model, StudentView view){
        this.model = model;
        this.view = view;
    }

    public void setStudentName(String name){
        model.setName(name);
    }

    public String getStudentName(){
        return model.getName();
    }

    public void setStudentRollNo(String rollNo){
        model.setRollNo(rollNo);
    }

    public String getStudentRollNo(){
        return model.getRollNo();
    }

    public void updateView(){
        view.printStudentDetails(model);
    }
}