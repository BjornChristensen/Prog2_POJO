package collections;

import java.util.HashMap;
import java.util.Map;

public class StudentGrades {
    public static void main(String[] args) {
        // Create a HashMap to store student names and their grades
        HashMap<String, Integer> grades = new HashMap<>();

        // Add some student grades
        grades.put("Alice", 85);
        grades.put("Bob", 92);
        grades.put("Charlie", 78);
        grades.put("Diana", 90);

        // Display all student grades
        System.out.println("Student Grades:");
        for (Map.Entry<String, Integer> entry : grades.entrySet()) {
            System.out.println(entry.getKey()+": "+entry.getValue());
        }
        System.out.println();

        // Update a student's grade with put.
        // NB: only one entry for Alice
        grades.put("Alice", 88);
        System.out.println("Updated Alice to: " + grades.get("Alice"));

        // Remove a student's grade
        grades.remove("Charlie");
        System.out.println("Removed Charlie's grade.");

        // Check if a student is in the HashMap
        if (grades.containsKey("Bob")) {
            System.out.println("Bob's grade: " + grades.get("Bob"));
        }

        // Display all student grades after updates
        System.out.println(grades);
    }
}
