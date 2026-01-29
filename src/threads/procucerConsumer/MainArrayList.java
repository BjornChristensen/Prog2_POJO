package threads.procucerConsumer;
// Producer/Consumer problem.


import java.util.ArrayList;
import java.util.Vector;

public class MainArrayList {
    static ArrayList<Integer> list = new ArrayList<>();
//    static Vector<Integer> list = new Vector<>();
    public static void main(String[] args) throws InterruptedException {
        int N=2;
        System.out.println("MainArrayList Producer started");
        ConsumerArrayList[] users = new ConsumerArrayList[N];
        for (int i = 0; i < N; i++) {
            users[i]=new ConsumerArrayList();
            users[i].start();
        }

        for (int i=0; i<5; i++) {
            list.add(i);
            System.out.println("P "+i);
 //           Thread.sleep(100);
        }
    }
}

class ConsumerArrayList extends Thread {
    public void run() {
        System.out.println("Consumer " + this.threadId() + " started");
        while (true) {
            if (!MainArrayList.list.isEmpty())
            System.out.println(this.threadId()+" "+MainArrayList.list.remove(0));
        }
    }
}
