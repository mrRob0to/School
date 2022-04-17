//Robert Antenorcruz
//Prof. Anne Smith-Thompson
//CS 313

package Project;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.io.File;

import static Project.Utilities.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        boolean checkIfSave = false;
        boolean mainMenu = true;
        //Globally declared - used as a counter when importing txt files in the multiple projects.
        int insertCountSIZE = 0;
        int importCountSIZE = 0;


        /**
         * NOTE: I did not comment out the previous methods/functions when starting a next project.
         * I created an additional submenu for each project.
         */

        //Main Menu
        while (mainMenu) {
            System.out.println("\nMAIN MENU:");
            System.out.println("1) CREATE NEW LIST - Project1");
            System.out.println("2) IMPORT LIST - Project1");
            System.out.println("3) IMPORT DEMO LIST - Project1");
            System.out.println("4) IMPORT ROSTER FILE - Project2");
            System.out.println("5) IMPORT ROSTER FILE - Project3");
            System.out.println("6) IMPORT ROSTER FILE - Project4");
            System.out.println("7) EXIT");
            int mainMenuInput = scan.nextInt();
            scan.nextLine();

            /**
             * PROJECT 1 - Creates a new linked list with the ability to add and remove students.
             * Adds student by adding node to linked list. Stops when 10 students are in the linked list.
             * Removes student by removing node in linked list.
             * Prints list by traversing nodes in linked list
             * Saves linkedList into array and export it to txt file project1Roster.txt
             * Binary search on array - will not execute unless linkedList is saved as array
             */
            if (mainMenuInput == 1) {
                System.out.println("Creating new List:");
                LinkedStudentList linkedList = new LinkedStudentList();
                ArrayList<Student> arrList = new ArrayList<Student>();
                ArrayList<String> tempCourses = new ArrayList<>();

                boolean subMenu1 = true;

                while (subMenu1) {

                    System.out.println("\n1) Add Student");
                    System.out.println("2) Remove Student");
                    System.out.println("3) Print List");
                    System.out.println("4) Save List");
                    System.out.println("5) Search List");
                    System.out.println("6) Exit");

                    int subMenu1Input = scan.nextInt();
                    scan.nextLine();

                    //Adds students to the linkedList till the count is 10
                    if (subMenu1Input == 1) {
                        boolean subMenu1_1 = true;
                        tempCourses.clear();
                        System.out.println("Enter First name: ");
                        String fname = scan.nextLine();
                        System.out.println("Enter Last name: ");
                        String lname = scan.nextLine();
                        System.out.println("Enter ID: ");
                        String ID = scan.nextLine();

                        while (subMenu1_1) {
                            System.out.println("Enter Course Number -- Type quit to stop ");
                            String course = scan.nextLine();
                            if (course.equals("quit")) {
                                subMenu1_1 = false;
                            } else tempCourses.add(course);

                            System.out.println("Course added");
                        }
                        insertCountSIZE++;

                        if (insertCountSIZE < 10) {
                            linkedList.insertSort(new StudentRecord(fname, lname, ID, new ArrayList<String>(tempCourses)));
                        } else System.out.print("Did not add Student Roster Full - MAX SIZE 10");
                    }
                    //Removes students by searching string ID
                    else if (subMenu1Input == 2) {
                        System.out.println("Enter Student ID: ");
                        String ID = scan.nextLine();
                        linkedList.delete(ID);
                        System.out.println(ID + " Deleted");
                    }
                    //Prints linkedList
                    else if (subMenu1Input == 3) {
                        linkedList.printList();
                    }
                    //Saves linkedList into array and export it to txt file project1Roster.txt
                    else if (subMenu1Input == 4) {
                        System.out.println("Saving and exporting list...");
                        arrList = save(linkedList);
                        System.out.println("List saved");
                        checkIfSave = true;

                        FileWriter writer = new FileWriter("B:\\IdeaProjects\\WarmUpProject\\src\\Project\\project1Roster.txt");

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
                    //Binary search on array - will not execute unless linkedList is saved as array
                    else if (subMenu1Input == 5) {
                        if (checkIfSave == false) {
                            System.out.println("WARNING - LIST HAS NOT BEEN SAVED");
                        } else {
                            System.out.println("Enter First Name");
                            String fname = scan.nextLine();
                            System.out.println("Enter Last name: ");
                            String lname = scan.nextLine();
                            System.out.println("Enter ID Number: ");
                            String ID = scan.nextLine();

                            binarySearch(arrList, fname, lname, ID);
                        }
                    }
                    //Exit
                    else if (subMenu1Input == 6) {
                        subMenu1 = false;
                        break;
                    }

                }
            }

            /**
             * PROJECT 1 - Imports data from a txt file into a linked list.
             * Adds student by adding node to linked list.
             * Removes student by removing node in linked list.
             * Prints list by traversing nodes in linked list
             * Saves linkedList into array and export it to txt file project1Roster.txt
             * Binary search on array - will not execute unless linkedList is saved as array
             */
            //PROJECT 1 - Import data from txt file then create new linkedList
            else if (mainMenuInput == 2) {
                LinkedStudentList importLinkedList = new LinkedStudentList();
                ArrayList<Student> importArrList = new ArrayList<Student>();
                Scanner scanner = new Scanner(new File("B:\\IdeaProjects\\WarmUpProject\\src\\Project\\project1Roster.txt"));
                Scanner valueScanner = null;
                boolean subMenu2 = true;
                int index = 0;

                //Inputs data from .txt file
                while (scanner.hasNextLine()) {
                    valueScanner = new Scanner(scanner.nextLine());
                    valueScanner.useDelimiter(",");
                    StudentRecord iSR = new StudentRecord();

                    while (valueScanner.hasNext()) {
                        String data = valueScanner.next();
                        if (index == 0) {
                            iSR.setFirstName(data);
                        } else if (index == 1) {
                            iSR.setLastName(data);
                        } else if (index == 2) {
                            iSR.setIDNo(data);
                        } else {
                            iSR.addCourse(data);
                        }
                        index++;
                    }
                    index = 0;
                    importCountSIZE++;
                    if (importCountSIZE <= 10) {
                        importLinkedList.insertSort(iSR);
                    } else
                        System.out.println("ROSTER FULL - MAX SIZE 10");

                }
                scanner.close();
                System.out.println(importLinkedList);
                importArrList = save(importLinkedList);

                while (subMenu2) {
                    System.out.println("1)Binary Search ");
                    System.out.println("2)Exit");
                    int subMenu2_1 = scan.nextInt();
                    scan.nextLine();

                    //Binary Search
                    if (subMenu2_1 == 1) {
                        System.out.println("Enter First Name");
                        String fname = scan.nextLine();
                        System.out.println("Enter Last name: ");
                        String lname = scan.nextLine();
                        System.out.println("Enter ID Number: ");
                        String ID = scan.nextLine();
                        binarySearch(importArrList, fname, lname, ID);
                    }
                    //Exit
                    if (subMenu2_1 == 2) {
                        subMenu2 = false;
                        break;
                    }
                }
            }


            /**
             * PROJECT 1 - Demo list - used for debugging
             * Adds student by adding node to linked list.
             * Removes student by removing node in linked list.
             * Prints list by traversing nodes in linked list
             * Saves linkedList into array and export it to txt file project1Roster.txt
             * Binary search on array - will not execute unless linkedList is saved as array
             */
//          PROJECT 1 - Demo list - used for debugging
            else if (mainMenuInput == 3) {
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
                binarySearch(testArrList, "John", "Smith", "124");

            }

            /**
             * PROJECT 2 - Adds a Queue into the project
             * Adds student by adding node to linked list - but if roster is full, add it to Queue.
             * Removes student by removing node in linked list - if there's space in the roster, move into from Queue.
             * Prints list by traversing nodes in linked list
             */
            //PROJECT2 - Adding Queue to project
            else if (mainMenuInput == 4) {
                Queue<Student> q = new LinkedList<>();
                LinkedStudentList importLinkedList = new LinkedStudentList();
                ArrayList<Student> importArrList = new ArrayList<Student>();
                System.out.println("IMPORTING DATA FROM PROJECT 1...");
                Scanner scanner = new Scanner(new File("B:\\IdeaProjects\\WarmUpProject\\src\\Project\\project1Roster.txt"));
                Scanner valueScanner = null;
                int index = 0;
                boolean subMenu4 = true;

                //Imports data from txt file and adds linkedList roster -- adds to queue if roster is full
                while (scanner.hasNextLine()) {
                    valueScanner = new Scanner(scanner.nextLine());
                    valueScanner.useDelimiter(",");
                    StudentRecord iSR = new StudentRecord();

                    while (valueScanner.hasNext()) {
                        String data = valueScanner.next();
                        if (index == 0) {
                            iSR.setFirstName(data);
                        } else if (index == 1) {
                            iSR.setLastName(data);
                        } else if (index == 2) {
                            iSR.setIDNo(data);
                        } else {
                            iSR.addCourse(data);
                        }
                        index++;
                    }
                    index = 0;
                    importCountSIZE++;
                    if (importCountSIZE <= 10) {
                        importLinkedList.insertSort(iSR);
                    } else {
                        System.out.println("ROSTER FULL - MAX SIZE 10...adding to waiting list");
                        q.add(iSR);
                    }

                }
                scanner.close();
                importArrList = save(importLinkedList);

                System.out.println(importArrList.size());
                System.out.println(importArrList);


                while (subMenu4) {
                    System.out.println("1)Check Roster");
                    System.out.println("2)Check Waiting List");
                    System.out.println("3)Add Student");
                    System.out.println("4)Remove Student");
                    System.out.println("5)Exit");

                    int subMenu4_1 = scan.nextInt();
                    scan.nextLine();

                    if (subMenu4_1 == 1) {
                        System.out.println(importArrList);
                    } else if (subMenu4_1 == 2) {
                        System.out.println("WAITING LIST:");
                        System.out.println(q);
                    } else if (subMenu4_1 == 3) {
                        if (importArrList.size() > 9) {
                            System.out.println("Class is full");
                            System.out.println("Adding to waiting list...");
                            System.out.println("Enter First Name");
                            String fname = scan.nextLine();
                            System.out.println("Enter Last name: ");
                            String lname = scan.nextLine();
                            System.out.println("Enter ID Number: ");
                            String ID = scan.nextLine();

                            StudentRecord iSR = new StudentRecord(fname, lname, ID, null);
                            q.add(iSR);
                            System.out.println("WAITING LIST:");
                            System.out.println(q);
                        } else if (importArrList.size() < 9) {
                            System.out.println("Enter First Name");
                            String fname = scan.nextLine();
                            System.out.println("Enter Last name: ");
                            String lname = scan.nextLine();
                            System.out.println("Enter ID Number: ");
                            String ID = scan.nextLine();

                            StudentRecord iSR = new StudentRecord(fname, lname, ID);
                            importArrList.add(iSR);
                        }

                    } else if (subMenu4_1 == 4) {
                        if (importArrList.size() > 9) {
                            System.out.println("Enter ID Number of Student to Delete:");
                            String ID = scan.nextLine();
                            for (int j = 0; j < importArrList.size(); j++) {
                                Student temp = importArrList.get(j);
                                if (ID.equals(temp.getIDNo())) {
                                    importArrList.remove(j);
                                    if (!q.peek().equals(null)) {
                                        Student temp2;
                                        temp2 = q.remove();
                                        importArrList.add(temp2);
                                    }
                                }
                            }
                            System.out.println("QUEUE IS: " + q);
                            System.out.println("ROSTER IS: " + importArrList);
                        }
                    } else if (subMenu4_1 == 5) {
                        System.out.println("Exiting...");
                        break;
                    }

                }

            }
            //Main Menu Exit
            else if (mainMenuInput == 7) {
                System.out.println("Exiting...");
                System.exit(1);
            }


            /**
             * PROJECT 3 - Implements BST
             * Adds student by adding node to BST - but if roster is full, add it to Queue.
             * Removes student by removing node in BST - if there's space in the roster, move into from Queue.
             * Prints list by inOrder method.
             * Note: When BST is "not saved" - adding, removing, printing is done by BST methods.
             * Once the BST saves it converts it into an Arraylist. Add, remove, print is done by Arraylist methods.
             */
            //PROJECT3 - Implementing BST
            else if (mainMenuInput == 5) {
                BinarySearchTree BST = new BinarySearchTree();
                Queue<Student> q = new LinkedList<>();
                ArrayList<StudentRecord> importArrList = new ArrayList<StudentRecord>();
                System.out.println("IMPORTING DATA FROM input.csv...");
                Scanner scanner = new Scanner(new File("B:\\IdeaProjects\\WarmUpProject\\src\\Project\\input.csv"));
                Scanner valueScanner = null;
                int index = 0;
                boolean subMenu5 = true;
                boolean subMenu5_1 = true;


                //Imports data from txt file and adds to BST -- adds to queue if roster is full
                while (scanner.hasNextLine()) {
                    valueScanner = new Scanner(scanner.nextLine());
                    valueScanner.useDelimiter(",");
                    StudentRecord iSR = new StudentRecord();

                    while (valueScanner.hasNext()) {
                        String data = valueScanner.next();
                        if (index == 0) {
                            iSR.setFirstName(data);
                        } else if (index == 1) {
                            iSR.setLastName(data);
                        } else if (index == 2) {
                            iSR.setIDNo(data);
                        } else {
                            iSR.addCourse(data);
                        }
                        index++;
                    }
                    index = 0;
                    importCountSIZE++;
                    if (importCountSIZE <= 20) {
                        BST.insert(iSR);
                    } else {
                        System.out.println("ROSTER FULL - MAX SIZE 20 - ADDING TO QUEUE");
                        q.add(iSR);
                    }
                }
                scanner.close();

                //
                while (subMenu5) {
                    System.out.println("\nROSTER DRAFT - CHANGES NOT FINAL");
                    System.out.println("1)Print Roster");
                    System.out.println("2)Check Waiting List");
                    System.out.println("3)Add Student");
                    System.out.println("4)Remove Student");
                    System.out.println("5)Save Changes");

                    int subMenu5Input = scan.nextInt();
                    scan.nextLine();

                    //Print in-order
                    if (subMenu5Input == 1) {
                        BST.inOrder();
                    }
                    //Prints Queue
                    else if (subMenu5Input == 2) {
                        System.out.println("WAITING LIST:");
                        System.out.println(q);
                    }
                    //Adds student to BST - if full adds to Queue
                    else if (subMenu5Input == 3) {
                        boolean checkIDFlag = true;
                        if (importArrList.size() > 20) {
                            System.out.println("Class is full");
                            System.out.println("Adding to waiting list...");
                            System.out.println("Enter First Name");
                            String fname = scan.nextLine();
                            System.out.println("Enter Last name: ");
                            String lname = scan.nextLine();
                            System.out.println("Enter ID Number: ");
                            String ID = "";
                            while (checkIDFlag) {
                                System.out.println("Enter ID Number: ");
                                ID = scan.nextLine();
                                if (checkID(ID) == true) {
                                    checkIDFlag = false;
                                }
                            }

                            StudentRecord iSR = new StudentRecord(fname, lname, ID, null);
                            q.add(iSR);
                            System.out.println("WAITING LIST:");
                            System.out.println(q);
                        } else if (importArrList.size() < 20) {
                            System.out.println("Enter First Name");
                            String fname = scan.nextLine();
                            System.out.println("Enter Last name: ");
                            String lname = scan.nextLine();
                            String ID = "";
                            while (checkIDFlag) {
                                System.out.println("Enter ID Number: ");
                                ID = scan.nextLine();
                                if (checkID(ID) == true) {
                                    checkIDFlag = false;
                                }
                            }

                            StudentRecord iSR = new StudentRecord(fname, lname, ID);
                            BST.insert(iSR);
                        }

                    }
                    //Removes student from BST - if space in roster add from Queue
                    else if (subMenu5Input == 4) {
                        System.out.println("Enter First Name:");
                        String fname = scan.nextLine();
                        System.out.println("Enter Last Name: ");
                        String lname = scan.nextLine();
                        System.out.println("Enter ID Number: ");
                        String ID = scan.nextLine();
                        Student temp = new Student(fname, lname, ID);
                        BST.delete(temp);

                        if (q.peek() != null) {
                            Student temp2;
                            temp2 = q.remove();
                            BST.insert(temp2);
                        }

                    }
                    //Saves BST into an Arraylist in-order and exports into FinalRoster.txt
                    else if (subMenu5Input == 5) {
                        BST.saveInOrder(importArrList);
                        System.out.println("\n\nSaving and exporting list...");
                        System.out.println("List saved");
                        ArrayList<String> tempSt = new ArrayList<>();

                        FileWriter writer = new FileWriter("B:\\IdeaProjects\\WarmUpProject\\src\\Project\\FinalRoster.txt");

                        for (int j = 0; j < importArrList.size(); j++) {
                            StudentRecord temp = importArrList.get(j);
                            writer.append(temp.getFirstName());
                            writer.append(",");
                            writer.append(temp.getLastName());
                            writer.append(",");
                            writer.append((temp.getIDNo()));
                            writer.append(",");
                            tempSt = temp.getCourses();
                            for (String x : tempSt) {
                                writer.append(x);
                                writer.append(",");
                            }
                            writer.append("\n");
                        }
                        writer.close();
                        subMenu5 = false;
                    }
                }
                //Roster is Arraylist - adds and removes students by arraylist methods
                while (subMenu5_1) {
                    System.out.println("\nROSTER FINAL");
                    System.out.println("1)Check Roster");
                    System.out.println("2)Check Waiting List");
                    System.out.println("3)Add Student");
                    System.out.println("4)Remove Student");
                    System.out.println("5)Exit");

                    int subMenu5Input = scan.nextInt();
                    scan.nextLine();

                    //Prints Roster - ArrayList
                    if (subMenu5Input == 1) {
                        System.out.println(importArrList);
                    }
                    //Prints Queue
                    else if (subMenu5Input == 2) {
                        System.out.println("WAITING LIST:");
                        System.out.println(q);
                    }
                    //Adds student - Arraylist
                    else if (subMenu5Input == 3) {
                        boolean checkIDFlag2 = true;
                        if (importArrList.size() > 20) {
                            System.out.println("Class is full");
                            System.out.println("Adding to waiting list...");
                            System.out.println("Enter First Name");
                            String fname = scan.nextLine();
                            System.out.println("Enter Last name: ");
                            String lname = scan.nextLine();
                            System.out.println("Enter ID Number: ");
                            String ID = "";
                            while (checkIDFlag2) {
                                System.out.println("Enter ID Number: ");
                                ID = scan.nextLine();
                                if (checkID(ID) == true) {
                                    checkIDFlag2 = false;
                                }
                            }

                            StudentRecord iSR = new StudentRecord(fname, lname, ID, null);
                            q.add(iSR);
                            System.out.println("WAITING LIST:");
                            System.out.println(q);
                        } else if (importArrList.size() < 20) {
                            System.out.println("Enter First Name");
                            String fname = scan.nextLine();
                            System.out.println("Enter Last name: ");
                            String lname = scan.nextLine();
                            System.out.println("Enter ID Number: ");
                            String ID = "";
                            while (checkIDFlag2) {
                                System.out.println("Enter ID Number: ");
                                ID = scan.nextLine();
                                if (checkID(ID) == true) {
                                    checkIDFlag2 = false;
                                }
                            }

                            StudentRecord iSR = new StudentRecord(fname, lname, ID);
                            importArrList.add(iSR);
                        }

                    }
                    //Removes student - Arraylist
                    else if (subMenu5Input == 4) {
                        System.out.println("Enter ID Number of Student to Delete:");
                        String ID = scan.nextLine();
                        for (int k = 0; k < importArrList.size(); k++) {
                            Student temp = importArrList.get(k);
                            if (ID.equals(temp.getIDNo())) {
                                importArrList.remove(k);
                                if (q.peek() != null) {
                                    StudentRecord temp2;
                                    temp2 = (StudentRecord) q.remove();
                                    importArrList.add(temp2);
                                }
                            }
                        }
                        System.out.println("QUEUE IS: " + q);
                        System.out.println("ROSTER IS: " + importArrList);

                    }
                    //Exits
                    else if (subMenu5Input == 5) {
                        System.out.println("Exiting...");
                        System.exit(1);
                    }
                }
            }
            /**
             * PROJECT 4 - Implements a Hashtable
             * Adds student by adding node to BST - but if roster is full, add it to Queue.
             * Also adds student to hashtable + chaining. It takes the last two digits of the StudentID and places it the relevant bucket.
             * Removes student by removing node in BST - if there's space in the roster, move into from Queue.
             * Prints list by inOrder method.
             * Note: When BST is "not saved" - adding, removing, printing is done by BST methods.
             * Once the BST saves it converts it into an Arraylist. Add, remove, print is done by Arraylist methods.
             * Implemented checkID - ID has to have 7 characters with the first character must be a letter.
             */
            //Project 4 Adds a HashTable into the project
            else if (mainMenuInput == 6) {
                BinarySearchTree BST = new BinarySearchTree();
                Queue<Student> q = new LinkedList<>();
                ArrayList<StudentRecord> importArrList = new ArrayList<StudentRecord>();

                /**
                 * Creates a 2D Arraylist of StudentRecords.
                 * The for loop is to initialize the rows of the 2D Arraylist.
                 */
                ArrayList<ArrayList<StudentRecord>> StudentHashTable = new ArrayList<ArrayList<StudentRecord>>(100);
                ArrayList<StudentRecord> row;
                for (int i = 0; i < 100; ++i) {
                    row = new ArrayList<StudentRecord>();
                    StudentHashTable.add(row);
                }
                /**
                 * Inputs data from input.csv. Creates new student from data then adds it to the BST + Hashtable with chaining.
                 * If Roster is full add to Queue.
                 * The key is the last two digits of the Student ID.
                 * Runtime of Inserting Student into Hashtable - θ(1)
                 */
                String idCheck;
                Integer key = 0;
                System.out.println("IMPORTING DATA FROM input.csv...");
                Scanner scanner = new Scanner(new File("B:\\IdeaProjects\\WarmUpProject\\src\\Project\\input.csv"));
                Scanner valueScanner = null;
                int index = 0;
                boolean subMenu6 = true;
                boolean subMenu6_1 = true;

                while (scanner.hasNextLine()) {
                    valueScanner = new Scanner(scanner.nextLine());
                    valueScanner.useDelimiter(",");
                    StudentRecord iSR = new StudentRecord();

                    while (valueScanner.hasNext()) {
                        String data = valueScanner.next();
                        if (index == 0) {
                            iSR.setFirstName(data);
                        } else if (index == 1) {
                            iSR.setLastName(data);
                        } else if (index == 2) {
                            iSR.setIDNo(data);
                            idCheck = data.substring(data.length() - 2);
                            key = Integer.valueOf(idCheck);

                        } else {
                            iSR.addCourse(data);
                        }

                        index++;
                    }
                    index = 0;
                    importCountSIZE++;
                    if (importCountSIZE <= 20) {
                        BST.insert(iSR);
                    } else {
                        System.out.println("ROSTER FULL - MAX SIZE 20 - ADDING TO QUEUE");
                        q.add(iSR);
                    }
                    //Add to Student Hash table
                    StudentHashTable.get(key).add(iSR);

                }
                scanner.close();


                while (subMenu6) {
                    System.out.println("\nROSTER DRAFT - CHANGES NOT FINAL");
                    System.out.println("1)Print Roster");
                    System.out.println("2)Check Waiting List");
                    System.out.println("3)Add Student");
                    System.out.println("4)Remove Student");
                    System.out.println("5)Save Changes");
                    System.out.println("6)Search by ID");

                    int subMenu6Input = scan.nextInt();
                    scan.nextLine();

                    //Prints BST inOrder
                    if (subMenu6Input == 1) {
                        BST.inOrder();
                    }
                    //Prints Queue
                    else if (subMenu6Input == 2) {
                        System.out.println("WAITING LIST:");
                        System.out.println(q);
                    }
                    //Adds Student to BST + into Hashtable with chaining
                    else if (subMenu6Input == 3) {
                        boolean checkIDFlag = true;
                        if (importArrList.size() > 20) {
                            System.out.println("Class is full");
                            System.out.println("Adding to waiting list...");
                            System.out.println("Enter First Name");
                            String fname = scan.nextLine();
                            System.out.println("Enter Last name: ");
                            String lname = scan.nextLine();
                            System.out.println("Enter ID Number: ");
                            String ID = "";
                            while (checkIDFlag) {
                                System.out.println("Enter ID Number: ");
                                ID = scan.nextLine();
                                if (checkID(ID) == true) {
                                    checkIDFlag = false;
                                }
                            }
                            idCheck = ID.substring(ID.length() - 2);
                            key = Integer.valueOf(idCheck);

                            StudentRecord iSR = new StudentRecord(fname, lname, ID, null);
                            q.add(iSR);

                            //Adds student into Hash table
                            StudentHashTable.get(key).add(iSR);


                            System.out.println("WAITING LIST:");
                            System.out.println(q);
                        } else if (importArrList.size() < 20) {
                            System.out.println("Enter First Name");
                            String fname = scan.nextLine();
                            System.out.println("Enter Last name: ");
                            String lname = scan.nextLine();
                            String ID = "";
                            while (checkIDFlag) {
                                System.out.println("Enter ID Number: ");
                                ID = scan.nextLine();
                                if (checkID(ID) == true) {
                                    checkIDFlag = false;
                                }
                            }
                            idCheck = ID.substring(ID.length() - 2);
                            key = Integer.valueOf(idCheck);

                            StudentRecord iSR = new StudentRecord(fname, lname, ID);
                            BST.insert(iSR);

                            StudentHashTable.get(key).add(iSR);
                        }

                    }
                    /**
                     * Removes Student from BST and Hashtable.
                     * The remove for loop runtime - θ(1)
                     */
                    else if (subMenu6Input == 4) {
                        System.out.println("Enter First Name:");
                        String fname = scan.nextLine();
                        System.out.println("Enter Last Name: ");
                        String lname = scan.nextLine();
                        System.out.println("Enter ID Number: ");
                        String ID = scan.nextLine();
                        Student temp = new Student(fname, lname, ID);
                        BST.delete(temp);

                        if (q.peek() != null) {
                            Student temp2;
                            temp2 = q.remove();
                            BST.insert(temp2);
                        }

                        idCheck = ID.substring(ID.length() - 2);
                        key = Integer.valueOf(idCheck);

                        //Searches the hash table for the same ID string
                        ArrayList<StudentRecord> bucket = StudentHashTable.get(key);
                        for (int i = 0; i < bucket.size(); i++) {
                            if (bucket.get(i).getIDNo().equals(ID)) {
                                bucket.remove(i);
                            }
                        }
                    }


                    //Saves BST as an arraylist and exports to FinalRoster.txt
                    else if (subMenu6Input == 5) {
                        BST.saveInOrder(importArrList);
                        System.out.println("\n\nSaving and exporting list...");
                        System.out.println("List saved");
                        ArrayList<String> tempSt = new ArrayList<>();

                        FileWriter writer = new FileWriter("B:\\IdeaProjects\\WarmUpProject\\src\\Project\\FinalRoster.txt");

                        for (int j = 0; j < importArrList.size(); j++) {
                            StudentRecord temp = importArrList.get(j);
                            writer.append(temp.getFirstName());
                            writer.append(",");
                            writer.append(temp.getLastName());
                            writer.append(",");
                            writer.append((temp.getIDNo()));
                            writer.append(",");
                            tempSt = temp.getCourses();
                            for (String x : tempSt) {
                                writer.append(x);
                                writer.append(",");
                            }
                            writer.append("\n");
                        }
                        writer.close();
                        subMenu6 = false;
                    }

                    /**
                     * Searches the Hahstable by Student ID
                     * runtime - θ(1)
                     */
                    else if (subMenu6Input == 6) {
                        String ID = " ";
                        boolean checkIDFlag = true;
                        while (checkIDFlag) {
                            System.out.println("Enter ID Number: ");
                            ID = scan.nextLine();
                            if (checkID(ID) == true) {
                                checkIDFlag = false;
                            }
                            idCheck = ID.substring(ID.length() - 2);
                            key = Integer.valueOf(idCheck);
                        }

                        ArrayList<StudentRecord> bucket = StudentHashTable.get(key);
                        for (int i = 0; i < bucket.size(); i++) {
                            if (bucket.get(i).getIDNo().equals(ID)) {
                                System.out.println("Student found in position: " + key + " " + i);
                                System.out.println(bucket.get(i));
                            }


                        }
                    }

                    //Roster is Arraylist
                    while (subMenu6_1) {
                        System.out.println("\nROSTER FINAL");
                        System.out.println("1)Check Roster");
                        System.out.println("2)Check Waiting List");
                        System.out.println("3)Add Student");
                        System.out.println("4)Remove Student");
                        System.out.println("5)Exit");

                        int subMenuInput6_1 = scan.nextInt();
                        scan.nextLine();

                        //Print Roster
                        if (subMenuInput6_1 == 1) {
                            System.out.println(importArrList);
                        }
                        //Print Queue
                        else if (subMenuInput6_1 == 2) {
                            System.out.println("WAITING LIST:");
                            System.out.println(q);
                        }
                        //Add student to Arraylist
                        else if (subMenuInput6_1 == 3) {
                            boolean checkIDFlag2 = true;
                            if (importArrList.size() > 20) {
                                System.out.println("Class is full");
                                System.out.println("Adding to waiting list...");
                                System.out.println("Enter First Name");
                                String fname = scan.nextLine();
                                System.out.println("Enter Last name: ");
                                String lname = scan.nextLine();
                                System.out.println("Enter ID Number: ");
                                String ID = "";
                                while (checkIDFlag2) {
                                    System.out.println("Enter ID Number: ");
                                    ID = scan.nextLine();
                                    if (checkID(ID) == true) {
                                        checkIDFlag2 = false;
                                    }
                                }

                                StudentRecord iSR = new StudentRecord(fname, lname, ID, null);
                                q.add(iSR);
                                System.out.println("WAITING LIST:");
                                System.out.println(q);
                            } else if (importArrList.size() < 20) {
                                System.out.println("Enter First Name");
                                String fname = scan.nextLine();
                                System.out.println("Enter Last name: ");
                                String lname = scan.nextLine();
                                System.out.println("Enter ID Number: ");
                                String ID = "";
                                while (checkIDFlag2) {
                                    System.out.println("Enter ID Number: ");
                                    ID = scan.nextLine();
                                    if (checkID(ID) == true) {
                                        checkIDFlag2 = false;
                                    }
                                }

                                StudentRecord iSR = new StudentRecord(fname, lname, ID);
                                importArrList.add(iSR);
                            }

                        }
                        //Remove student from Arraylist
                        else if (subMenuInput6_1 == 4) {
                            System.out.println("Enter ID Number of Student to Delete:");
                            String ID = scan.nextLine();
                            for (int k = 0; k < importArrList.size(); k++) {
                                Student temp = importArrList.get(k);
                                if (ID.equals(temp.getIDNo())) {
                                    importArrList.remove(k);
                                    if (q.peek() != null) {
                                        StudentRecord temp2;
                                        temp2 = (StudentRecord) q.remove();
                                        importArrList.add(temp2);
                                    }
                                }
                            }
                            System.out.println("QUEUE IS: " + q);
                            System.out.println("ROSTER IS: " + importArrList);

                        }
                        //Exit
                        else if (subMenuInput6_1 == 5) {
                            System.out.println("Exiting...");
                            System.exit(1);
                        }
                    }
                }
            }
        }
    }
}
