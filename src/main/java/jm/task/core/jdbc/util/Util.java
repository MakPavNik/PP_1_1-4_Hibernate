package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    public static Connection connect() {
        String url = "jdbc:mysql://localhost:3306/schema_01";
        String userName = "root";
        String password = "12345";


        try {
            return DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}