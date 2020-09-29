package com.sda.jdbc.statement;

import com.sda.jdbc.Book;

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

    // update
    public void update(int id, Book newBookData) {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();
            String sql = "UPDATE book SET title = '" + newBookData.getTitle() + "', " +
                "author = '" + newBookData.getAuthor() + "' WHERE id = " + id;
            int rowsAffected = statement.executeUpdate(sql);

            if (rowsAffected == 1) {
                System.out.println("book updated");
            }
        } catch (SQLException e) {
            System.out.println("book not updated");
        } catch (Exception e) {
            System.out.println("something went wrong");
        } finally {
            // close resources

            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.println("failed to close statement");
            }

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("failed to close connection");
            }
        }
    }

    // delete
    public void delete(int id) {
        // try with resources
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            String sql = "DELETE FROM book WHERE id = " + id;
            int rowsAffected = statement.executeUpdate(sql);

            if (rowsAffected == 1) {
                System.out.println("book updated");
            }
        } catch (SQLException e) {
            System.out.println("book not updated");
        } catch (Exception e) {
            System.out.println("something went wrong");
        }
    }
}
