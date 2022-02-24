package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;



public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS users" +
                    "( id INTEGER (1) NOT NULL AUTO_INCREMENT," +
                    "  name VARCHAR(255)," +
                    "  lastName VARCHAR(255)," +
                    "  age SMALLINT ," +
                    "  CONSTRAINT pk PRIMARY KEY (id)" +
                    ");");
            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try
                (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createSQLQuery("DROP TABLE IF EXISTS users");
            query.executeUpdate();
        } catch (HibernateException e) {
            e.printStackTrace();

            System.out.println("Таблицу удалить не удалось" + e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("Не удалось сохранить нового пользователя" + e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            User user = (User) session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("Не удалось удалить данного пользователя" + e);
        }
    }


    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        String sql = "SELECT * FROM users";
        Query query = session.createNativeQuery(sql).addEntity(User.class);

        List <User> userList = query.list();



        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            String sql = "TRUNCATE TABLE users";
            Query query = session.createNativeQuery(sql).addEntity(User.class);
            query.executeUpdate();
            session.getTransaction().commit();

        } catch (HibernateException e){
            e.printStackTrace();
            System.out.println("Не удалось очистить таблицу" + e);
        }
    }
}
