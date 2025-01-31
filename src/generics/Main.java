package generics;

public class Main {
    public static void main(String[] args) {
        Person p1=new Person("Peter", "Pan");
        Person p2=new Person("Mickey", "Mouse");
        System.out.println(p1);
        System.out.println(p2);
        SimplePair pair=new SimplePair(p1, p2);
        System.out.println("Winner: "+pair.getWinner());
        System.out.println();

        Pair<Person> pair1=new Pair<>(p1, p2);
        System.out.println("Winner: "+pair1.getWinner());

        System.out.println();
        Pair<Country> pair2=new Pair<>(new Country("Denmark"), new Country("Norway"));
        System.out.println(pair2.getWinner());
    }
}