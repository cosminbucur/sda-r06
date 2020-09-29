package com.sda.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// data access object
public class BookJdbcDao {

    public static final String URL = "jdbc:mysql://localhost:3306/jdbc_tutorial?serverTimezone=UTC";
    public static final String USER = "root";
    public static final String PASSWORD = "Rootpass3#";

    // create
    public void create(Book book) {

        try {
            // add book in table
            // get connection
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // create statement
            Statement statement = connection.createStatement();

            // execute
            // create sql
            String sql = "INSERT INTO book(title, author) VALUES ('" + book.getTitle() + "', '" + book.getAuthor() + "')";
            int rowsAffected = statement.executeUpdate(sql);

            if (rowsAffected == 1) {
                System.out.println("book saved");
            }
            // close
            connection.close();
        } catch (SQLException e) {
            System.out.println("book not saved");
        }

        // read


        // update

        // delete
    }

    public List<Book> findAll() {
        List<Book> result = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();

            String sql = "SELECT id, title, author FROM book";
            ResultSet resultSet = statement.executeQuery(sql);

            // iterate result set and build the list
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                Book book = new Book(id, title, author);
                result.add(book);
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println("book not saved");
        }
        return result;
    }
}
