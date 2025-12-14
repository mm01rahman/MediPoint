package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection con;

    public static Connection getConnection() throws SQLException {
        if (con == null || con.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException("MySQL JDBC Driver not found.", e);
            }

            String url = "jdbc:mysql://localhost:3306/db_hospital";
            String user = "root";
            String password = "";

            Connection newConnection = DriverManager.getConnection(url, user, password);
            if (newConnection == null) {
                throw new SQLException("Failed to obtain a database connection.");
            }
            con = newConnection;
        }

        if (con == null) {
            throw new SQLException("Database connection is not available.");
        }
        return con;
    }
}

