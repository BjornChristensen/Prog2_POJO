package threads.w3s;

public class Main_Synchronized extends Thread {
    public static int amount = 0;
//    synchronized
    static void increment() {amount=amount+1;}

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main thread running");
        Main_Synchronized[] thread = new Main_Synchronized[10];
        for (int i=0; i<thread.length; i++){
            thread[i]=new Main_Synchronized();
            thread[i].start();
        }
        System.out.println(amount);
//        amount++;
        amount=amount+1;
//        increment();
        for (int i=0; i<thread.length; i++) thread[i].join();
        System.out.println("Main thread ended. amount="+amount);
    }

    public void run() {
        System.out.println("Aux thread running");
//        amount++;
        amount=amount+1;
//        increment();
        System.out.println("Aux thread ended");
    }
}
