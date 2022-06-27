package com.hapm.util;

import java.sql.*;

public class DbUtil {

    private static final String DB_URL = "jdbc:mariadb://localhost:3306/test";
    private static final String DB_USERNAME = "user";
    private static final String DB_PASSWORD = "1234";
    private static final DbUtil instance = new DbUtil();
    private Connection connection;

    private DbUtil() {
    }

    public static DbUtil getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void initDatabaseConnection() throws SQLException {
        System.out.println("------------------------------------");
        System.out.println("Connecting to the database...");
        connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        connection.setAutoCommit(false);
        if (connection.isValid(5)) {
            System.out.println("Connected to database.");
        }
        System.out.print("------------------------------------\n\n");
    }

    public void closeDatabaseConnection() throws SQLException {
        System.out.print("\n\n------------------------------------\n");
        System.out.println("Closing database connection...");
        connection.close();
        if (!connection.isValid(5)) {
            System.out.println("Database disconnected.");
        }
        System.out.println("------------------------------------");
    }

}
