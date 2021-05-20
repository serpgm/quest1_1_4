package jm.task.core.jdbc.dao;

import com.mysql.cj.xdevapi.SessionFactory;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
                "(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                "age INT NOT NULL)");
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery("DROP TABLE IF EXISTS users");
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "INSERT INTO users (name, lastname, age) VALUES(?,?,?)";
        Query query = session.createSQLQuery(sql)
                .setString(1, name)
                .setString(2, lastName)
                .setInteger(3, age);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "DELETE FROM users WHERE id = ?";
        Query query = session.createSQLQuery(sql)
                .setLong(1, id);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new LinkedList<>();
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery("select * from users");
        List<Object[]> rows = query.list();
        for(Object[] row : rows) {
            User user = new User();
            int a = (int) row[0];
            user.setId((long)a);
            user.setName(row[1].toString());
            user.setLastName(row[2].toString());
            a = (int) row[3];
            user.setAge((byte) a);
            users.add(user);
        }
        //ResultSet set = util.getStatement().executeQuery("select * from users");
        transaction.commit();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "TRUNCATE TABLE users";
        Query query = session.createSQLQuery(sql);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }
}
