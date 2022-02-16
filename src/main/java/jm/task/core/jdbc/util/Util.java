package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соединения с БД
        private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
        private static final String DB_URL = "jdbc:mysql://localhost:3306/schema1";
        private static final String DB_USERNAME = "root";
        private static final String DB_PASSWORD = "root";

        public static Connection getConnection() throws SQLException {
            Connection connection = null;
            try {
                Class.forName(DB_DRIVER);
                System.out.println("Connection OK");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Connection ERROR");
            }
            return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        }

    }


