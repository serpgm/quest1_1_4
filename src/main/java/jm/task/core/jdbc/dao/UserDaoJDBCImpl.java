package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try(Util util = new Util()){
            util.getStatement().execute("CREATE TABLE `tablet`.`users` (`id` INT NOT NULL AUTO_INCREMENT,`name` VARCHAR(45) NOT NULL,`lastname` VARCHAR(45) NOT NULL,`age` INT NOT NULL, PRIMARY KEY (`id`), UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)");
        }catch(IOException | SQLException e){
            //e.printStackTrace();
            System.out.println("Работаем");
        }

    }

    public void dropUsersTable() {
        try(Util util = new Util()){
            util.getStatement().execute("DROP TABLE users");
        }catch(IOException | SQLException e){
            //e.printStackTrace();
            System.out.println("Работаем");
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement = null;
        try (Util util = new Util()) {
            preparedStatement = util.getConnection()
                    .prepareStatement("insert into users (name, lastname, age) value(?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setByte(3,age);
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {

        PreparedStatement preparedStatement = null;
        try (Util util = new Util()) {
            preparedStatement = util.getConnection()
                    .prepareStatement("delete from users where id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public List<User> getAllUsers() {
        List<User> users = new LinkedList<>();
        try (Util util = new Util()) {
            ResultSet set = util.getStatement().executeQuery("select * from users");
            while (set.next()) {
                User user = new User();
                user.setId(set.getLong("id"));
                user.setName(set.getString("name"));
                user.setLastName(set.getString("lastname"));
                user.setAge(set.getByte("age"));
                users.add(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try(Util util = new Util()){
            util.getStatement().execute("TRUNCATE TABLE users");
        }catch(IOException | SQLException e){
            //e.printStackTrace();
            System.out.println("Работаем");
        }

    }
}
