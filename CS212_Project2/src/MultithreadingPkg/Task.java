package MultithreadingPkg;

public class Task {

    private int workload;

    Task(){
        workload = 0;
    }

    Task(int n){
        workload = n;
    }

    void setWorkload(int n){workload = n;}
    int getWorkload(){return workload;}



}
