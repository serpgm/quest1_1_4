package jm.task.core.jdbc;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tablet","user", "1234");
            System.out.println(connection.isClosed() ? "Нет подключения" : "Соединение установлено!");
            Statement statement = connection.createStatement();
           // statement.execute("insert into animal(name,age) value('ggggg', 10)");
           // statement.execute("CREATE TABLE `tablet`.`use_table` (`id` INT ZEROFILL NOT NULL AUTO_INCREMENT, `name` VARCHAR(45) NULL, `last` VARCHAR(45) NULL, `age` INT NULL, PRIMARY KEY (`id`), UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);");
            //statement.execute("drop table new_table");
//            PreparedStatement prstat = connection.prepareStatement("insert into users(name, lastName, age) value(?, ?, ?)");
//            prstat.setString(1, "Harry4");
//            prstat.setString(2, "Potter4");
//            prstat.setInt(3, 14);
//            prstat.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Что то пошло не так");
        }
    }
}
