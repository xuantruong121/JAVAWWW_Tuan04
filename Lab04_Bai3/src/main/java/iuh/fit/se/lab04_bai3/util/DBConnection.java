package iuh.fit.se.lab04_bai3.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mariadb://localhost:3306/quanlydanhmuc";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.mariadb.jdbc.Driver"); // nạp driver
        } catch (ClassNotFoundException e) {
            throw new SQLException("Không tìm thấy driver MariaDB", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
