package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try(Util util = new Util()){
            Statement statement = util.getConnection().createStatement();
            statement.execute("CREATE TABLE users (`id` INT NOT NULL AUTO_INCREMENT, `name` VARCHAR(45) NOT NULL, `lastName` VARCHAR(45) NULL, `age` INT NULL, PRIMARY KEY (`id`), UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);");
            System.out.println("Таблица успешно создана!");
        }catch (SQLException | IOException throwables) {
            //throwables.printStackTrace();
            System.out.println("Что то пошло не так");
        }

    }

    public void dropUsersTable() {
        try(Util util = new Util()){
            Statement statement = util.getConnection().createStatement();
            statement.execute("DROP TABLE users;");
            System.out.println("Таблица успешно удалена!");
        }catch (SQLException | IOException throwables) {
           // throwables.printStackTrace();
            System.out.println("Что то пошло не так");
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try(Util util = new Util()){
            PreparedStatement prstat = util.getConnection().prepareStatement("insert into users(name, lastName, age) value(?, ?, ?)");
            prstat.setString(1, name);
            prstat.setString(2, lastName);
            prstat.setInt(3, age);
            prstat.execute();
            System.out.println("Юзер добавлен в таблицу!");
        }catch (SQLException | IOException throwables) {
           // throwables.printStackTrace();
            System.out.println("Что то пошло не так");
        }

    }

    public void removeUserById(long id) {
        try(Util util = new Util()){
            Statement statement = util.getConnection().createStatement();
            statement.execute("DELETE FROM users WHERE ID =" + id + ";");
            System.out.println("Юзер с ID=" + id + " удален!");
        }catch (SQLException | IOException throwables) {
            //throwables.printStackTrace();
            System.out.println("Что то пошло не так");
        }

    }

    public List<User> getAllUsers() {
        ResultSet rs = null;
        try(Util util = new Util()){
            Statement statement = util.getConnection().createStatement();
            util.getConnection().setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            util.getConnection().setAutoCommit(false);
            rs = statement.executeQuery("SELECT * FROM users");
            LinkedList<User> result = new LinkedList<>();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String lastName = rs.getString("lastName");
                byte age = rs.getByte("age");
                User user = new User(id, name, lastName, age);
                result.add(user);
            }
            return result;
        }catch (SQLException | IOException throwables) {
           // throwables.printStackTrace();
            System.out.println("Что то пошло не так");
            return null;
        }

    }

    public void cleanUsersTable() {
        try(Util util = new Util()){
            Statement statement = util.getConnection().createStatement();
            statement.execute("TRUNCATE TABLE users");
            System.out.println("Таблица очищена");
        }catch (SQLException | IOException throwables) {
            //throwables.printStackTrace();
            System.out.println("Что то пошло не так");
        }
    }
}































