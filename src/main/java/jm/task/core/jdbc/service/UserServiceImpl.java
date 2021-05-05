package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    public void createUsersTable() {
        UserDao userdao = new UserDaoJDBCImpl();
        userdao.createUsersTable();

    }

    public void dropUsersTable() {
        UserDao userdao = new UserDaoJDBCImpl();
        userdao.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) {
        UserDao userdao = new UserDaoJDBCImpl();
        userdao.saveUser(name, lastName, age);

    }

    public void removeUserById(long id) {
        UserDao userdao = new UserDaoJDBCImpl();
        userdao.removeUserById(id);

    }

    public List<User> getAllUsers() {
        UserDao userdao = new UserDaoJDBCImpl();
        return userdao.getAllUsers();
    }

    public void cleanUsersTable() {
        UserDao userdao = new UserDaoJDBCImpl();
        userdao.cleanUsersTable();
    }
}
