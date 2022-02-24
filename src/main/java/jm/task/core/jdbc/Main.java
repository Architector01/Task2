package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
//        userService.saveUser("Arthur", "Valiev", (byte) 23);
//        userService.saveUser("Vladimir", "Petrov", (byte) 30);
//        userService.saveUser("Alex", "Jonson", (byte) 40);
//        userService.saveUser("Leo", "Di Caprio", (byte) 37);
//        System.out.println(userService.getAllUsers());
//        userService.removeUserById(3);
//        userService.cleanUsersTable();
//        userService.dropUsersTable();
    }
}
