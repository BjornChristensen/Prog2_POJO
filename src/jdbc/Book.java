package jdbc;

public class Book {
  String title;
  int year;

  Book(String title, int year){
    this.title=title;
    this.year=year;
  }

  public String toString(){
    return "Title="+title+" "+"year="+year;
  }
}