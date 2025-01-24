// Live: Simulation af Stearinlys+Lighter leg. Demo af Deadlocks
// Bjørn Christensen, 13/1-2025
package threads.light_the_candle;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
  static Resource[] res=new Resource[2];  // candle and lighter

  public static void main(String[] args) throws InterruptedException {
    System.out.println("main started");
    res[0]=new Resource("candle");
    res[1]=new Resource("lighter");
    Student s1=new FairStudent("Pia");
    Student s2=new FairStudent("Per");
    s1.start();
    s2.start();
    s1.join();
    s2.join();
    System.out.println("main finished");
  }
}

class Student extends Thread {
  Random gen=new Random();
  String name;
  final int MAX=10000;

  Student(String name) { this.name=name; }

  public void run() {
    System.out.println(name+" started");
    try {
      int resNo=gen.nextInt(2);       // Search candle or lighter
      Thread.sleep(gen.nextInt(MAX)); // It takes some time to find
      Main.res[resNo].lock();         // Found it, and lock it
      System.out.println(name+" got the "+Main.res[resNo]);

      resNo=(resNo+1)%2;              // Look for the other resource
      Thread.sleep(gen.nextInt(MAX)); // It takes some time to find
      Main.res[resNo].lock();         // Found it, and lock it
      System.out.println(name+" got the "+Main.res[resNo]);

      System.out.println(name+" lights the candle");
      Main.res[0].unlock();
      Main.res[1].unlock();

    } catch (InterruptedException ex) {}
    System.out.println(name+" finifhed");
  }
}

class FairStudent extends Student { // Undgå deadlock vha tilbagelægning
  FairStudent(String name) { super(name); }

  public void run() {
    System.out.println(name+" started");
    try {
      boolean keepGoing=true;
      while (keepGoing){
        int resNo=gen.nextInt(2);       // Search candle or lighter
        Thread.sleep(gen.nextInt(MAX)); // It takes some time to find
        Main.res[resNo].lock();         // Found it, and lock it
        System.out.println(name+" got the "+Main.res[resNo]);

        resNo=(resNo+1)%2;              // Look for the other resource
        Thread.sleep(gen.nextInt(MAX)); // It takes some time to find
        if (Main.res[resNo].tryLock(1000, TimeUnit.MILLISECONDS)) {
          System.out.println(name+" got the "+Main.res[resNo]);
          System.out.println(name+" lights the candle");
          keepGoing=false;
        }
        if (Main.res[0].isHeldByCurrentThread()) Main.res[0].unlock();
        if (Main.res[1].isHeldByCurrentThread()) Main.res[1].unlock();
      }
    } catch (InterruptedException ex) {}
    System.out.println(name+" finifhed");
  }
}

class Resource extends ReentrantLock {
  String name;
  Resource(String name){ this.name=name; }
  public String toString(){ return name; }
}