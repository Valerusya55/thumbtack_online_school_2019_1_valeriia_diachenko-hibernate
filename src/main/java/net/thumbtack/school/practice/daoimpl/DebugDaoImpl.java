package net.thumbtack.school.practice.daoimpl;


import net.thumbtack.school.practice.dao.DebugDao;
import net.thumbtack.school.practice.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class DebugDaoImpl extends BaseDaoImpl implements DebugDao {
    @Override
    public void clearDataBase() {
        try (Session session = getSession()) {
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query<User> deleteUsers = session.createQuery("DELETE FROM User");
            deleteUsers.executeUpdate();
            session.getTransaction().commit();
        }
    }
}
