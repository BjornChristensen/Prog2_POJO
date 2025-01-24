// Dining Philosophers
// Bjørn Christensen, 14/1-2025
package threads.dining_philosophers;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
  static final int N=5;          // No of philosophers and chopsticks
  static ChopStick[] chopSticks=new ChopStick[N];

  public static void main(String[] args) throws InterruptedException {
    System.out.println("Main started");

    // Make some chopsticks
    for (int i=0; i<N; i++) chopSticks[i]=new ChopStick(i);

    // Make some philosophers and start them
    Philosopher[] philosophers=new Philosopher[N];
    for (int i=0; i<N; i++) {
      philosophers[i] = new Philosopher(i);
      philosophers[i].start();
    }

    // Wait for all philosophers to finish
    for (int i=0; i<N; i++) philosophers[i].join();
    System.out.println("Main finished");
  }
}

class Philosopher extends Thread {
  static final int M = 3;         // No af meatballs to start with
  int id;                         // Id of this philosopher
  int left, right;                // Index of left and right chopstick
  Random gen = new Random();      // Random number generator

  Philosopher(int id) {
    this.id = id;
    left = id;
    right = (id + 1) % Main.N;    // Wrap if index > N
  }

  public void run() {
    System.out.println("P" + id + " started");
    try {
      ChopStick first, second;      // Two chopsticks
      int m = 0;                    // No of meatballs eaten so far
      while (M > m) {
        if (gen.nextBoolean()) {
          first = Main.chopSticks[left];    // grab left first
          second = Main.chopSticks[right];  // then right
        } else {                            // or
          first = Main.chopSticks[right];   // right first
          second = Main.chopSticks[left];   // the left
        }
        first.lock();                       // grab the first stick

        // Deadlock prone code:
//      second.lock();                        // and the second
//      m++;                                  // Eat a meatball
//      System.out.println("P"+id+" eats meatball "+m);
//      Thread.sleep(gen.nextInt(1000));      // Think
//      first.unlock();
//      second.unlock();

        // To avoid deadlock use tryLock() and release after 1000 ms
        if (second.tryLock(1000, TimeUnit.MILLISECONDS)) {
          m++;                              // Eat a meatball
          System.out.println("P" + id + " eats meatball " + m);
          Thread.sleep(gen.nextInt(1000));
          second.unlock();
        } else {
          System.out.println("P" + id + " failed to grab s" + second);
        }
        first.unlock(); // Always release the first stick

      } // while
    } catch (InterruptedException ex) {}
    System.out.println("P" + id + " finished");
  }
}

class ChopStick extends ReentrantLock {
  int id;
  ChopStick(int id) { this.id=id; }
  public String toString() { return ""+id; }
}