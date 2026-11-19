
package com.week10.ah;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String URL =
            "jdbc:mysql://localhost:3306/wk10_employment_db";

    private static final String USER = "root";

    private static final String PASSWORD =
            System.getenv("DB_PASSWORD");

    public static Connection getConnection() throws SQLException {

        if (PASSWORD == null || PASSWORD.isEmpty()) {
            throw new SQLException(
                    "DB_PASSWORD environment variable is not configured."
            );
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
