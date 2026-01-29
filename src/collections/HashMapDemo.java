package collections;
import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {
  public static void main(String[] args) {
    HashMap<String, Integer> height=new HashMap<>();
    height.put("Alice", 167);
    height.put("Bob", 187);
    height.put("Charlie", 187);
    height.put("David", 178);

    for (String key: height.keySet()){
      // Bemærk: Rækkefølger af udskrift er udefineret og kan variere
      // de entries opbevares i en hashtabel som er et array
      System.out.println(key+": "+height.get(key));
    }

    int h=height.get("Alice");
    System.out.println(h);

    height.put("Charlie", 188); // OBS: ingen dubletter
    height.remove("David");
    System.out.println(height.containsKey("David"));
    System.out.println(height);

    // Bedre performance da vi kun traverserer heights én gang
    for (Map.Entry<String, Integer> e: height.entrySet()){
      System.out.println(e);
    }
  }
}