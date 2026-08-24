package recursion;

public class Factorial {
  static int fac(int n){
    int result;
    if(n==1){
      result=1;
    } else {
      result = n*fac(n-1);
    }
    return result;
  }

  public static void main(String[] args) {
    System.out.println(fac(7)); // 5040
  }
}
