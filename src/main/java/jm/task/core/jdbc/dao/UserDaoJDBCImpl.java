package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.hql.internal.antlr.SqlTokenTypes.INTO;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() throws SQLException {

    }

    public void createUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS users" +
                    "(id BIGINT(19) NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255) NOT NULL, lastname VARCHAR(255) NOT NULL, " +
                    "age TINYINT UNSIGNED NOT NULL) DEFAULT CHARSET=utf8");
        } catch (SQLException e) {
            System.out.println("Таблицу не удалось создать" + e);
        }

    }

    public void dropUsersTable() {
        try {
            Statement statement = connection.createStatement();
            String SQL = "DROP TABLE IF EXISTS users";
            statement.execute(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.printf("Таблицу не удалось удалить" + e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {

            try {
                Statement statement = connection.createStatement();
                String saveUser = "INSERT INTO users (name, lastName, age) " +
                        "VALUES (" + "'" + name + "', '" + lastName + "'," + age + ")";
                statement.executeUpdate(saveUser);
                System.out.println("User с именем " + name + " добавлен в базу данных");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    public void removeUserById(long id) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM users WHERE ID=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Пользователь с данным id не удален" + e);
        }


    }

    public List<User> getAllUsers() {
    List <User> listUser = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                listUser.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось получить всех пользователей" + e);
        }

        return listUser;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("TRUNCATE TABLE users");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
