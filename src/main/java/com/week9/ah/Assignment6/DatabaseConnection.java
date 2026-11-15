
package com.week9.ah.Assignment6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // MySQL database details
    private static final String URL =
        "jdbc:mysql://localhost:3306/hospital_db";

    private static final String USER = "root";

    // Replace this with your MySQL root password
    private static final String PASSWORD = "MY_PASSWORD";

    // Establish database connection
    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(
            URL,
            USER,
            PASSWORD
        );
    }
}
