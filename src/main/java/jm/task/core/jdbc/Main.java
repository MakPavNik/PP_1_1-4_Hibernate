package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserService us = new UserServiceImpl();
        us.createUsersTable();
        us.saveUser("Tom", "Hardi", (byte) 20);
        us.saveUser("Roma", "Ivanov", (byte) 53);
        us.saveUser("Dima", "Petrov", (byte) 33);
        us.saveUser("Oleg", "Kozlov", (byte) 43);
        us.removeUserById(1L);
        us.getAllUsers();
        us.cleanUsersTable();
        us.dropUsersTable();
    }
}