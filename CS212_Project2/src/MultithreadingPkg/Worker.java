package MultithreadingPkg;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Worker extends Thread {

    static Random r = new Random();

    private String name;
    private Task task;
    private int performance;

    Worker(String name, Task task){
        this.name = name;
        this.task = task;

    }

    void randomPerformance(){
        performance = r.nextInt(10);
    }


    public void run(){

        for(int i = 0; i < task.getWorkload(); i++){
            try {
                TimeUnit.SECONDS.sleep(performance);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " has finished task - " + (i + 1)+"/"+task.getWorkload());
        }
        System.out.println(name + " has COMPLETED ALL TASKS\n");

    }

}
