package peaksoft.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import peaksoft.model.User;
import peaksoft.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try {
            String sql = "create table if not exists users ( " +
                    " id serial primary key, " +
                    " name varchar not null, " +
                    " lastName varchar not null, " +
                    " age integer not null );";
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }

    @Override
    public void dropUsersTable() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.createSQLQuery("drop table users ").executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println("DELETED");
        } catch (Exception e) {
            System.out.println("error" + e.getMessage());
        }


    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.persist(new User(name, lastName, age));
            session.getTransaction().commit();
            session.close();
            System.out.println(name + " " + lastName + "  added");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.createSQLQuery(String.format("delete from users where id = %d;", id)).executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println("DELETED");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            users = session.createQuery("FROM User").getResultList();
            session.getTransaction().commit();
            session.close();
            System.out.println("Found " + users.size() + " users");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.createSQLQuery(String.format("truncate table users")).executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println("CLEANED");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
