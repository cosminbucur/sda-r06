package com.sda.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// data access object
public class BookJdbcDao {

    // connection string
    //	protocol: jdbc
    // db type: mysql
    //	address: localhost / emag.ro
    //	port: 3366
    //	db name: jdbc_tutorial

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
}
