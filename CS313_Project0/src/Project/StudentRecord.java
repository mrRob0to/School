package Project;

import java.util.ArrayList;

public class StudentRecord extends Student {

    ArrayList<String> courses = new ArrayList<>();

    //Constructors
    StudentRecord() {
        super();
        this.courses = courses;
    }

    StudentRecord(String fName, String lName, String ID, ArrayList<String> courses) {
        super(fName, lName, ID);
        this.courses = courses;
    }

    StudentRecord(String fName, String lName, String ID) {
        super(fName, lName, ID);
        this.courses = courses;
    }

    //Functions
    void addCourse(String newCourse) {
        courses.add(newCourse);
    }

    void removeCourse(String oldCourse) {
        courses.remove(oldCourse);
    }

    void showCourses() {
        for (String x : courses) {
            System.out.println(x);
        }
    }

    ArrayList getCourses() {
        return courses;
    }


    @Override
    public String toString() {
        String str = "\nFirst Name: " + getFirstName() + "\nLast Name: " + getLastName() + "\nID: " + getIDNo() + "\nCourses: " + courses;
        return str;
    }
}
