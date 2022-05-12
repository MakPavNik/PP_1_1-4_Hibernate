package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() { //создать таблицу пользователей
        try (Connection connection = Util.connect();
             Statement statement = connection.createStatement()) {
            String str = "create table users (\n" +
                    "    id BIGINT AUTO_INCREMENT,\n" +
                    "    name varchar(20) NOT NULL,\n" +
                    "    lastName varchar(20) NOT NULL,\n" +
                    "    age int (3) NOT NULL,\n" +
                    "    PRIMARY KEY (id)\n" +
                    ");";
            statement.execute(str);
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public void dropUsersTable() { //удалить таблицу пользователей
        try (Connection connection = Util.connect();
             Statement statement = connection.createStatement()) {
            String sql = "drop table users";
            statement.execute(sql);
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) { //сохранить пользователя
        try (Connection connection = Util.connect();
             PreparedStatement ps =
                     connection.prepareStatement("insert into users(name, lastName, age) values(?,?,?)")) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setInt(3, age);
            System.out.println("User с именем – " + name + " добавлен в базу данных");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public void removeUserById(long id) { //удалить пользователя по идентификатору
        try (Connection connection = Util.connect();
             PreparedStatement ps =
                     connection.prepareStatement("DELETE FROM users WHERE id=?")) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public List<User> getAllUsers() { //получить всех пользователей
        List<User> people = new ArrayList<>();

        try (Connection connection = Util.connect();
             Statement statement =
                     connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                people.add(user);
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        System.out.println(people);
        return people;
    }

    public void cleanUsersTable() { //очистить таблицу пользователей
        try (Connection connection = Util.connect();
             Statement statement = connection.createStatement()) {
            String sql = "truncate users";
            statement.execute(sql);
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
