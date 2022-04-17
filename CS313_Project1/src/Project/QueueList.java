package Project;

import java.util.LinkedList;
import java.util.Queue;

public class QueueList {


    Queue<Project.Student> q = new LinkedList<>();

    void enqueue(Project.Student n){ q.add(n);}
    void dequeue(){ q.remove();}


    void printQ(){
        for(Project.Student i : q){
            System.out.println(i.toString());
        }
    }













}


