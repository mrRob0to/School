package Project;

public class Student implements Comparable<Student> {

    private String firstName;
    private String lastName;
    private String IDNo;

    //Constructors
    Student() {
        firstName = "";
        lastName = "";
        IDNo = "";
    }

    Student(String fName, String lName, String ID) {
        firstName = fName;
        lastName = lName;
        IDNo = ID;
    }

    //Getters
    String getFirstName() {
        return firstName;
    }

    String getLastName() {
        return lastName;
    }

    String getIDNo() {
        return IDNo;
    }

    //Setters
    void setFirstName(String n) {
        firstName = n;
    }

    void setLastName(String n) {
        lastName = n;
    }

    void setIDNo(String n) {
        IDNo = n;
    }

    //Functions
    @Override
    public String toString() {
        String str = "\nFirst Name: " + firstName + "\nLast Name: " + lastName + "\nID: " + IDNo;
        return str;
    }

    @Override
    public int compareTo(Student o) {
        if (!this.lastName.equals(o.lastName)) {
            return this.lastName.compareTo(o.lastName);
        }
        else if (!this.firstName.equals(o.firstName)) {
            return this.firstName.compareTo(o.firstName);
        }
        else {
            return this.getIDNo().compareTo(o.getIDNo());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Student)) {
            return false;
        }
        Student c = (Student) o;
        if (c.getIDNo().equals(this.getIDNo())) {
            return true;
        } else return false;
    }
}
