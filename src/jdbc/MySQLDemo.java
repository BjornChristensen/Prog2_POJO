package jdbc;// Demo of JDBC connection to MySQL database from a Java program
// Bjørn Christensen, 4/3-2025

import java.sql.*;
import java.util.ArrayList;

public class MySQLDemo {
    Statement stmt;

    void openDB(String dbName, String user, String pw) throws ClassNotFoundException, SQLException  {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver OK");

        // On my system:
        // port 3306 is MySQL server running on plain OS (Windows)
        // port 3333 is MySQL server running in Docker container
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName, user, pw);
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3333/"+dbName, user, pw);
        System.out.println("Connection OK");
        stmt=connection.createStatement();
        System.out.println("Statement OK");
    }

    ArrayList<Book> getBooks() throws SQLException {
        ArrayList<Book> list=new ArrayList<>();
        ResultSet rs=stmt.executeQuery("SELECT * FROM books");
        while (rs.next()){
            Book book=new Book(rs.getString("TITLE"), rs.getInt("YEAR"));
            list.add(book);
        }
        return list;
    }

    void addBook(Book book) throws SQLException {
        String sql="INSERT INTO books (title,year) VALUES "+
                "('"+book.title+"', '"+book.year+"')";
        System.out.println(sql);
        stmt.executeUpdate(sql);
    }

    void removeBook(String title) throws SQLException {
        String sql="DELETE FROM books WHERE title="+"'"+title+"'";
        System.out.println(sql);
        stmt.executeUpdate(sql);
    }

    public static void main(String[] args) throws Exception {
        System.out.println("TestMySQL.main()");
        MySQLDemo db=new MySQLDemo();
        db.openDB("books_db", "root", "1234");
        ArrayList<Book> list=db.getBooks();
        for (Book book:list) System.out.println(book);
        System.out.println();

        db.addBook(new Book("Junglebogen", 1948));
        list=db.getBooks();
        for (Book book:list) System.out.println(book);
        System.out.println();

        db.removeBook("Junglebogen");
        list=db.getBooks();
        for (Book book:list) System.out.println(book);
    }
}
