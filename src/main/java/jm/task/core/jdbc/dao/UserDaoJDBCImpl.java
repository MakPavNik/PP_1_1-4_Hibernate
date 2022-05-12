package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() throws SQLException { //создать таблицу пользователей
        String str = "create table users (\n" +
                "    id BIGINT AUTO_INCREMENT,\n" +
                "    name varchar(20) NOT NULL,\n" +
                "    lastName varchar(20) NOT NULL,\n" +
                "    age int (3) NOT NULL,\n" +
                "    PRIMARY KEY (id)\n" +
                ");";

        util.connect().createStatement().execute(str);
    }

    public void dropUsersTable() throws SQLException { //удалить таблицу пользователей
        String sql = "drop table users";
        util.connect().createStatement().execute(sql);
    }

    public void saveUser(String name, String lastName, byte age) { //сохранить пользователя
        try {
            PreparedStatement preparedStatement = util.connect().prepareStatement("insert into users(name, lastName, age) values(?,?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            System.out.println("User с именем – " + name + " добавлен в базу данных");
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public void removeUserById(long id) { //удалить пользователя по идентификатору
        try {
            PreparedStatement preparedStatement =
                    util.connect().prepareStatement("DELETE FROM users WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public List<User> getAllUsers() { //получить всех пользователей
        List<User> people = new ArrayList<>();

        try {
            Statement statement =
                    util.connect().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()){
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
        try {
            Statement statement = util.connect().createStatement();
            String sql = "truncate users";
            statement.execute(sql);
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
