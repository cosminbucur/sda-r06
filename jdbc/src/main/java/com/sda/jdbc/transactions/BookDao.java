package com.sda.jdbc.transactions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookDao {

    public static final String URL = "jdbc:mysql://localhost:3306/jdbc_tutorial?serverTimezone=UTC";
    public static final String USER = "root";
    public static final String PASSWORD = "root";

    public void create() {
        // get connection
        String sql = "INSERT INTO book(title, author) VALUES(?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // go to manual mode
            connection.setAutoCommit(false);
            int recordCount = 0;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            // input data from console
            while (true) {

                // read title
                System.out.println("Enter title: ");
                String title = br.readLine();

                // read author
                System.out.println("Enter author: ");
                String author = br.readLine();

                // insert
                statement.setString(1, title);
                statement.setString(2, author);
                statement.executeUpdate();

                // commit / rollback
                System.out.println("commit - c or rollback - r ?");
                String transactionOperation = br.readLine();

                if (transactionOperation.equals("c")) {
                    connection.commit();
                    recordCount++;
                }

                if (transactionOperation.equals("r")) {
                    connection.rollback();
                }

                // add more books
                System.out.println("want to add more records? y / n");
                String answer = br.readLine();
                if (answer.equals("n")) {
                    break;
                }
            }

            // before closing connection the final commit is performed
            connection.commit();
            System.out.println("Successfully saved " + recordCount + " record(s) ");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
