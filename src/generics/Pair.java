package generics;

import java.util.Random;

// Start med Pair ude <>
class SimplePair {
    static Random rand = new Random();
    Person a,b;
    Person winner=null;

    public SimplePair(Person a, Person b) {
        this.a = a;
        this.b = b;
    }

    Person getWinner(){
        if(winner != null) return winner;
        if (rand.nextInt(2)==0)
            winner = a;
        else
            winner = b;
        return winner;
    }
}

class Pair<T> {
    static Random rand = new Random();
    T a,b;
    T winner=null;

    public Pair(T a, T b) {
        this.a = a;
        this.b = b;
    }

    T getWinner(){
        if(winner != null) return winner;
        if (rand.nextInt(2)==0)
            winner = a;
        else
            winner = b;
        return winner;
    }
}