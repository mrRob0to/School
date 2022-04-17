//Robert Antenorcruz
//Prof. Anne Smith-Thompson
//CS 313
//TODO: Remove Duplicates


package Project;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.io.File;

import static Project.Utilities.binarySearch;
import static Project.Utilities.save;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);
        boolean checkIfSave = false;
        boolean flag = true;
        int insertCountSIZE = 0;
        int importCountSIZE = 0;


        while(flag){
            System.out.println("\n\nMAIN MENU:");
            System.out.println("1) CREATE NEW LIST");
            System.out.println("2) IMPORT LIST");
            System.out.println("3) IMPORT DEMO LIST");
            System.out.println("4) EXIT");
            int n = scan.nextInt();
            scan.nextLine();

            if (n == 1) {
                System.out.println("Creating new List:");
                LinkedStudentList linkedList = new LinkedStudentList();
                ArrayList<Student> arrList = new ArrayList<Student>();
                ArrayList<String> tempCourses = new ArrayList<>();
                boolean flag2 = true;

                while(flag2){

                    System.out.println("\n1) Add Student");
                    System.out.println("2) Remove Student");
                    System.out.println("3) Print List");
                    System.out.println("4) Save List");
                    System.out.println("5) Search List");
                    System.out.println("6) Exit");

                    int v = scan.nextInt();
                    scan.nextLine();

                    if(v == 1){
                        boolean flag3 = true;
                        tempCourses.clear();
                        System.out.println("Enter First name: ");
                        String fname = scan.nextLine();
                        System.out.println("Enter Last name: ");
                        String lname = scan.nextLine();
                        System.out.println("Enter ID: ");
                        String ID = scan.nextLine();

                        while(flag3) {
                            System.out.println("Enter Course Number -- Type quit to stop ");
                            String course = scan.nextLine();
                            if (course.equals("quit")){
                                flag3 = false;
                            }
                            else tempCourses.add(course);

                            System.out.println("Course added");
                        }
                        insertCountSIZE++;
                        if(insertCountSIZE < 10) {
                            linkedList.insertSort(new StudentRecord(fname, lname, ID, new ArrayList<String>(tempCourses)));
                        }
                        else System.out.print("Did not add Student Roster Full - MAX SIZE 10");
                    }

                    else if(v == 2){
                        System.out.println("Enter Student ID: ");
                        String ID = scan.nextLine();
                        linkedList.delete(ID);
                        System.out.println(ID + " Deleted");
                    }

                    else if(v == 3){
                        linkedList.printList();
                    }

                    else if(v == 4){
                        System.out.println("Saving and exporting list...");
                        arrList = save(linkedList);
                        System.out.println("List saved");
                        checkIfSave = true;

                        FileWriter writer = new FileWriter("B:\\IdeaProjects\\WarmUpProject\\src\\Project\\output.csv");

                        for (int j = 0; j < arrList.size(); j++) {
                            Student temp = arrList.get(j);
                            writer.append(temp.getFirstName());
                            writer.append(",");
                            writer.append(temp.getLastName());
                            writer.append(",");
                            writer.append((temp.getIDNo()));
                            writer.append("\n");
                        }
                        writer.close();
                    }

                    else if( v == 5){

                        if(checkIfSave == false){
                            System.out.println("WARNING - LIST HAS NOT BEEN SAVED");
                        }
                        else {
                            System.out.println("Enter First Name");
                            String fname = scan.nextLine();
                            System.out.println("Enter Last name: ");
                            String lname = scan.nextLine();
                            System.out.println("Enter ID Number: ");
                            String ID = scan.nextLine();

                            binarySearch(arrList, fname, lname, ID);
                        }
                    }

                    else if(v == 6){
                        flag2 = false;
                        break;
                    }

                }
            }

            else if(n == 2){
                LinkedStudentList importLinkedList = new LinkedStudentList();
                ArrayList<Student> importArrList = new ArrayList<Student>();
                Scanner scanner = new Scanner(new File("B:\\IdeaProjects\\WarmUpProject\\src\\Project\\data.txt"));
                Scanner valueScanner = null;
                int index = 0;
                boolean flag4 = true;


                while(scanner.hasNextLine()){
                    valueScanner = new Scanner(scanner.nextLine());
                    valueScanner.useDelimiter(",");
                    StudentRecord iSR = new StudentRecord();

                    while(valueScanner.hasNext()){
                        String data = valueScanner.next();
                        if(index == 0){
                            iSR.setFirstName(data);
                        }
                        else if(index == 1){
                            iSR.setLastName(data);
                        }
                        else if(index == 2){
                            iSR.setIDNo(data);
                        }
                        else{
                            iSR.addCourse(data);
                        }
                        index++;
                    }
                    index = 0;
                    importCountSIZE++;
                    if(importCountSIZE <= 10){
                        importLinkedList.insertSort(iSR);
                    }
                    else
                        System.out.println("ROSTER FULL - MAX SIZE 10");

                }
                scanner.close();
                System.out.println(importLinkedList);
                importArrList = save(importLinkedList);

                while(flag4){
                    System.out.println("1)Binary Search ");
                    System.out.println("2)Exit");
                    int i = scan.nextInt();
                    scan.nextLine();

                    if( i == 1) {
                        System.out.println("Enter First Name");
                        String fname = scan.nextLine();
                        System.out.println("Enter Last name: ");
                        String lname = scan.nextLine();
                        System.out.println("Enter ID Number: ");
                        String ID = scan.nextLine();
                        binarySearch(importArrList, fname, lname, ID);
                    }
                    if(i==2){
                        flag4 = false;
                        break;
                    }
                }


            }

            else if(n == 3){
                LinkedStudentList testLinkedList = new LinkedStudentList();
                ArrayList<String> testCourses = new ArrayList<>();
                ArrayList<Student> testArrList = new ArrayList<>();

                testCourses.add("CS313");
                testCourses.add("CS220");
                testCourses.add("Mus201");
                System.out.println("Creating student records...");
                StudentRecord sr1 = new StudentRecord("Mary", "Doe", "123", null);
                StudentRecord sr2 = new StudentRecord("John", "Smith", "124", null);
                StudentRecord sr3 = new StudentRecord("John", "Smith", "134", null);
                StudentRecord sr4 = new StudentRecord("Mary", "Smith", "145", testCourses);
                System.out.println("Student records created");

                System.out.println("Inserting into LinkedList...");
                testLinkedList.insertSort(sr1);
                testLinkedList.insertSort(sr2);
                testLinkedList.insertSort(sr3);
                testLinkedList.insertSort(sr4);
                System.out.println("Complete");
                testLinkedList.printList();

                System.out.println("\nTransferring data into Array...");
                testArrList = save(testLinkedList);
                System.out.println("Complete");

                System.out.println("\nBinary search for John Smith 124");
                binarySearch(testArrList,"John","Smith", "124");



                System.out.println("QUEUE TEST");
                QueueList q = new QueueList();

                q.enqueue(sr1);
                q.enqueue(sr2);
                q.dequeue();
                q.printQ();


            }

            else if(n == 4){
                System.out.println("Exiting...");
                System.exit(1);
            }

        }

    }

}
