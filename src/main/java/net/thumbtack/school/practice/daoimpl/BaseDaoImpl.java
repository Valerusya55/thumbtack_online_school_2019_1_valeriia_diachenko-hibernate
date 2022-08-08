package net.thumbtack.school.practice.daoimpl;
import org.hibernate.Session;

public class BaseDaoImpl {

    protected Session getSession() {
        return HibernateUtils.getSessionFactory().openSession();
    }
}