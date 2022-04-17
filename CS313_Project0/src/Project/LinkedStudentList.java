package Project;

public class LinkedStudentList {

    private Node head;

    //Constructors
    public LinkedStudentList() {
        head = new Node();
    }


    //Getters & Setters
    Node getHead() {
        return this.head;
    }

    void setHead(Node n) {
        head = n;
    }


    static class Node {
        Student data;
        Node next;

        //Constructors
        public Node() {
            data = null;
            next = null;
        }

        public Node(Student data) {
            this.data = data;
            this.next = null;
        }

        public Node(Student data, Node next) {
            this.data = data;
            this.next = next;
        }

        //Getters and Setters
        void setData(Student data) {
            this.data = data;
        }

        void setNext(Node next) {
            this.next = next;
        }

        Student getData() {
            return this.data;
        }

        Node getNext() {
            return this.next;
        }
    }


    //Functions

    /**
     * Checks if head is empty
     *
     * @return if head is null
     */
    boolean isEmpty() {
        return head.next == null;
    }

    /**
     * Prints LinkedList
     * Runtime - O(n)
     */
    void printList() {
        Node curr = head;

        while (curr != null) {
            System.out.println(curr.data + " ");
            curr = curr.next;
        }
    }

    /**
     * Inserts Student node into LinkedList
     * Runtime - O(1)
     */
    void insertSort(Student n) {
        Node curr = head.next;
        Node prev = head;

        while ((curr != null) && (curr.data.compareTo(n) < 0)) {
            prev = curr;
            curr = curr.next;
        }
        prev.next = new Node(n, curr);
    }

    /**
     * Removes Student node from linked List
     * Runtime - O(1)
     */
    void delete(String n) {
        Node curr = head.next;
        Node prev = head;

        while ((curr != null) && (!curr.data.getIDNo().equals(n))) {
            prev = curr;
            curr = curr.next;
        }

        if (curr.data.getIDNo().equals(n)) {
            prev.next = curr.next;
        } else System.out.println("Not Found");
    }

    @Override
    public String toString() {
        String str = "";
        this.printList();

        return str;
    }
}


