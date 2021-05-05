package jm.task.core.jdbc.util;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util implements Closeable {
    private final String URL_NAME = "jdbc:mysql://localhost:3306/tablet";
    private final String USER_NAME = "user";
    private final String PSWD_NAME = "1234";
    private Connection connection;
    public Util(){
        try{
            connection = DriverManager.getConnection(URL_NAME, USER_NAME, PSWD_NAME);

        }catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Что то пошло не так");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() throws IOException{
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
