package generics;

public class Person {
    String first_name;
    String last_name;

    Person(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }
    public String toString() {
        return first_name+" "+last_name;
    }
}