package threads.w3s;

public class Main_Join extends Thread {
    public static int amount = 0;

    public static void main(String[] args) throws InterruptedException {
        Main_Join thread = new Main_Join();
        thread.start();
        System.out.println(amount);
        amount++;
        thread.join();
        System.out.println(amount);
    }

    public void run() {
        amount++;
    }
}
