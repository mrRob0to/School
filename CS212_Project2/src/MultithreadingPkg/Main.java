//Robert Antenorcruz
//CS212 Prof. Fried
package MultithreadingPkg;

public class Main {


    public static void main(String args[]) throws InterruptedException{
        Task task=new Task(5);
        Worker[] worker =new Worker[3];

        //Initializes worker objects and sets performance for each worker
        for (int i = 0; i < worker.length; i++) {
            worker[i]=new Worker("Worker#" + (i+1),task);
            worker[i].randomPerformance();
        }

        //Multithreading
        for (int i = 0; i < worker.length; i++) {
            worker[i].start();
        }

    }

}
