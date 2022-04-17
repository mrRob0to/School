package Project;

import java.util.ArrayList;

public class LinkedStudentList {

    private Node head;

    //Constructors
    public LinkedStudentList(){
        head = new Node();
    }


    //Getters & Setters
    Node getHead(){ return this.head;}
    void setHead(Node n){head = n;}


    //Functions
    boolean isEmpty(){
        return head.next == null;
    }

    void printList(){
        Node curr = head;

        while (curr != null){
            System.out.println(curr.data + " ");
            curr = curr.next;
        }
    }

    void insertSort(Student n){
        Node curr = head.next;
        Node prev = head;

        while((curr!=null)&&(curr.data.compareTo(n) < 0)){
            prev = curr;
            curr = curr.next;
        }
        prev.next = new Node(n, curr);
    }

    void delete(String n){
        Node curr = head.next;
        Node prev = head;

        while((curr!=null)&&(!curr.data.getIDNo().equals(n))){
            prev = curr;
            curr = curr.next;
            System.out.println("HITHITHIT");
        }

        if(curr.data.getIDNo().equals(n)){
            prev.next = curr.next;
        }

        else System.out.println("Not Found");


    }


    @Override
    public String toString(){
        String str = "";
        this.printList();

        return str;
    }


    static class Node{
        Student data;
        Node next;

        //Constructors
        public Node(){
            data = null;
            next = null;
        }

        public Node(Student data){
            this.data = data;
            this.next = null;
        }

        public Node(Student data, Node next){
            this.data = data;
            this.next = next;
        }

        //Setters and Getters
        void setData(Student data){this.data = data;}
        void setNext(Node next){this.next = next;}
        Student getData(){return this.data;}
        Node getNext(){return this.next;}
    }

}


