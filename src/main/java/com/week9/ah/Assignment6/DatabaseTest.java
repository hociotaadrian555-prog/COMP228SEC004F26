
package com.week9.ah.Assignment6;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseTest {

    public static void main(String[] args) {

        try (Connection connection =
                 DatabaseConnection.getConnection()) {

            System.out.println("================================");
            System.out.println("MySQL Connection Successful!");
            System.out.println("Hospital Database Connected!");
            System.out.println("================================");

        } catch (SQLException e) {

            System.out.println("MySQL Connection Failed!");
            e.printStackTrace();

        }
    }
}
