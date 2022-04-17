package Project;

import java.util.ArrayList;

public class Utilities {

    //Binary Search
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

    //Save function
    public static ArrayList<Student> save(LinkedStudentList n) {
        ArrayList<Student> list = new ArrayList<Student>(10);
        LinkedStudentList.Node curr = n.getHead().next;

        while (curr != null) {
            list.add(curr.getData());
            curr = curr.next;
        }

        return list;

    }

}
