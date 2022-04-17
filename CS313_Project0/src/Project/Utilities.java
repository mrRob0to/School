package Project;

import java.util.ArrayList;

public class Utilities {

    /**
     * Utilizes Binary Search on an Array list.
     * Compares Students firstName, lastName, studentID strings by utilizing compareTo.
     * Runtime - θ(logn)
     */
    public static Student binarySearch(ArrayList<Student> arr, String f, String l, String i) {
        int left = 0, right = arr.size() - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;
            if ((arr.get(middle).getLastName().compareTo(l) == 0) && (arr.get(middle).getFirstName().compareTo(f) == 0) && (arr.get(middle).getIDNo().compareTo(i) == 0)) {
                System.out.println("FOUND IN LIST");
                return arr.get(middle);
            }

            if ((arr.get(middle).getLastName().compareTo(l) < 0) && (arr.get(middle).getFirstName().compareTo(f) < 0) && (arr.get(middle).getIDNo().compareTo(i) < 0))
                left = middle + 1;
            else
                right = middle - 1;
        }
        System.out.println("NOT FOUND");
        return null;
    }
    /**
     * Function that saves LinkedList into an Arraylist.
     * Linked list is passed as parameter then saved into a new Arraylist.
     * Uses a while loop to traverse the nodes into the linked list then uses the Arraylist .add method to add into new list
     * Runtime - O(n)
     */
    public static ArrayList<Student> save(LinkedStudentList n) {
        ArrayList<Student> list = new ArrayList<Student>(10);
        LinkedStudentList.Node curr = n.getHead().next;

        while (curr != null) {
            list.add(curr.getData());
            curr = curr.next;
        }
        return list;
    }

    /**
     * Function that checks the String ID if it is valid.
     * ID must be length 7, and must have a letter as the first character of the string.
     * Runtime - O(1)
     */
    public static boolean checkID(String t){

        if ((!Character.isDigit(t.charAt(0))) && (t.length() ==7)){
            return true;
        }
        else{
            System.out.println("INVALID ID FORMAT");
            System.out.println("X999999");
            return false;
        }
    }
}