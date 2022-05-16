package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.util.List;

import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() { //создать таблицу пользователей+++
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String str = "create table if not exists users (\n" +
                "    id BIGINT NOT NULL AUTO_INCREMENT,\n" +
                "    name varchar(20) NOT NULL,\n" +
                "    lastName varchar(20) NOT NULL,\n" +
                "    age int (3) NOT NULL,\n" +
                "    PRIMARY KEY (id)\n" +
                ");";
        session.createSQLQuery(str).executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {  //удалить таблицу пользователей+++
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "drop table if exists users";
        session.createSQLQuery(sql).executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {  //сохранить пользователя+++
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        transaction.commit();
        System.out.println("User с именем – " + name + " добавлен в базу данных");
        session.close();
    }

    @Override
    public void removeUserById(long id) { //удалить пользователя по идентификатору+++
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.get(User.class, id));
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() { //получить всех пользователей+++
        Session session = Util.getSessionFactory().openSession();
        String str = "FROM User";
        List<User> users = session.createQuery(str).list();
        System.out.println(users);
        return users;
    }

    @Override
    public void cleanUsersTable() { //очистить таблицу пользователей+++
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "truncate table users";
        session.createNativeQuery(sql).executeUpdate();
        transaction.commit();
        session.close();
    }
}
