package net.thumbtack.school.practice.daoimpl;

import com.google.gson.JsonSyntaxException;
import net.thumbtack.school.practice.dao.SessionDao;
import net.thumbtack.school.practice.exeption.ServerErrorCode;
import net.thumbtack.school.practice.exeption.ServerException;
import net.thumbtack.school.practice.model.Session;
import net.thumbtack.school.practice.model.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;

public class SessionDaoImpl extends BaseDaoImpl implements SessionDao {

    @Override
    public void addToken(Session session1) throws ServerException {
        try (org.hibernate.Session session = getSession()) {
            session.beginTransaction();
            session.save(session1);
            session.getTransaction().commit();
        }catch (Exception ex){
            throw new ServerException(ServerErrorCode.VALIDATION_ERROR);
        }
    }

    @Override
    public User getUserByToken(String token) throws ServerException {
        try (org.hibernate.Session session = getSession()) {
            String str = "from Session where token=:param";
            Query<Session> query = session.createQuery(str, Session.class);
            query.setParameter("param", token);
            Session session1 = query.getSingleResult();
            if (session1 == null) {
                throw new ServerException(ServerErrorCode.WRONG_TOKEN);
            }
            return session.get(User.class,session1.getUser().getId());
        }catch (NoResultException ex){
            throw new ServerException(ServerErrorCode.WRONG_TOKEN);
        }
    }

    @Override
    public void deleteAll() {
        try (org.hibernate.Session session = getSession()) {
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query<User> deleteUsers = session.createQuery("DELETE FROM Session");
            deleteUsers.executeUpdate();
            session.getTransaction().commit();
        }
    }
}
