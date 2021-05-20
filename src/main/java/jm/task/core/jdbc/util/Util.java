package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.BootstrapServiceRegistry;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import org.hibernate.service.ServiceRegistry;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Util implements Closeable {
    private final String URL = "jdbc:mysql://localhost:3306/tablet";
    private final String USERNAME = "user";
    private final String PASSWORD = "1234";
    private Connection connection;
    private Statement statement;

    //####################################################
    private static StandardServiceRegistry standardServiceRegistry;
    private static SessionFactory sessionFactory;

    static {
        // Creating StandardServiceRegistryBuilder
        StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

        // Hibernate settings which is equivalent to hibernate.cfg.xml's properties
        Map<String, String> dbSettings = new HashMap<>();
        dbSettings.put(Environment.URL, "jdbc:mysql://localhost:3306/tablet");
        dbSettings.put(Environment.USER, "user");
        dbSettings.put(Environment.PASS, "1234");
        dbSettings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
        dbSettings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");

        // Apply database settings
        registryBuilder.applySettings(dbSettings);
        // Creating registry
        standardServiceRegistry = registryBuilder.build();
        // Creating MetadataSources
        MetadataSources sources = new MetadataSources(standardServiceRegistry);
        // Creating Metadata
        Metadata metadata = sources.getMetadataBuilder().build();
        // Creating SessionFactory
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }
    //Utility method to return SessionFactory
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    //####################################################
    // private PreparedStatement preparedStatement = null;
    public Util(){
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Статуст соединения: " + !connection.isClosed());
            statement = connection.createStatement();
            System.out.println("Статус подключения команд: " + !statement.isClosed());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public Connection getConnection(){

        return connection;
    }
    public Statement getStatement(){
        return statement;
    }

    @Override
    public void close() throws IOException {
        try {
            connection.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
