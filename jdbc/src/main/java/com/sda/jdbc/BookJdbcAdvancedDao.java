package com.sda.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookJdbcAdvancedDao {

    public static final String URL = "jdbc:mysql://localhost:3306/jdbc_tutorial?serverTimezone=UTC";
    public static final String USER = "root";
    public static final String PASSWORD = "Rootpass3#";

    public void create(Book book) {
        String sql = "INSERT INTO book(title, author) VALUES (?, ?)";
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 1) {
                System.out.println("book saved");
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println("book not saved");
        }
    }

    public List<Book> findAll() {
        List<Book> result = new ArrayList<>();
        String sql = "SELECT id, title, author FROM book";
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

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

    public void update(int id, Book newBookData) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String sql = "UPDATE book SET title = ?, author = ? WHERE id = ?";
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, newBookData.getTitle());
            preparedStatement.setString(2, newBookData.getAuthor());
            preparedStatement.setInt(3, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 1) {
                System.out.println("book updated");
            }
        } catch (SQLException e) {
            System.out.println("book not updated");
        } catch (Exception e) {
            System.out.println("something went wrong");
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println("failed to close preparedStatement");
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

    public void delete(int id) {
        String sql = "DELETE FROM book WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

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
