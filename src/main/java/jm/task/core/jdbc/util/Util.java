package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.DIALECT;

public class Util {
    // реализуйте настройку соединения с БД
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/users";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    // реализуйте настройку соеденения с БД
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/users",
                "root",
                "root"
        );
    }


    public static SessionFactory getSessionFactory() {
        StandardServiceRegistryBuilder registryBuilder =
                new StandardServiceRegistryBuilder();

        Map<String, String> settings = new HashMap<>();
        settings.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        settings.put("hibernate.connection.password", "root");
        settings.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/schema1");
        settings.put("hibernate.connection.username", "root");
        settings.put("hibernate.show_sql", "true");
        settings.put("hibernate.current_session_context_class", "thread");


        registryBuilder.applySettings(settings);
        MetadataSources sources = new MetadataSources(registryBuilder.build())
                .addAnnotatedClass(User.class);
        return sources.buildMetadata().buildSessionFactory();
    }
}




