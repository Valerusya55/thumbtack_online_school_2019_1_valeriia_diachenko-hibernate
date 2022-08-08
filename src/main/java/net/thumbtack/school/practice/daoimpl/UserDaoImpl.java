package net.thumbtack.school.practice.daoimpl;
import com.google.gson.JsonSyntaxException;
import net.thumbtack.school.practice.dao.UserDao;
import net.thumbtack.school.practice.enumformodel.ConsentToNomination;
import net.thumbtack.school.practice.enumformodel.Nomination;
import net.thumbtack.school.practice.exeption.ServerErrorCode;
import net.thumbtack.school.practice.exeption.ServerException;
import net.thumbtack.school.practice.model.Candidate;
import net.thumbtack.school.practice.model.EvaluationProposal;
import net.thumbtack.school.practice.model.Proposal;
import net.thumbtack.school.practice.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;


import javax.persistence.NoResultException;
import java.util.List;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    @Override
    public void registerUser(User user) throws ServerException {
        try (Session session = getSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }catch (Exception ex){
            throw new ServerException(ServerErrorCode.VALIDATION_ERROR);
        }
    }

    @Override
    public void logoutUser(String token) throws ServerException {
        try (Session session = getSession()) {
            String str = "from Session where token= :param";
            Query<net.thumbtack.school.practice.model.Session> query =
                    session.createQuery(str, net.thumbtack.school.practice.model.Session.class);
            query.setParameter("param",token);
            net.thumbtack.school.practice.model.Session session1 = query.getSingleResult();
            if(session1 == null){
                throw new ServerException(ServerErrorCode.WRONG_TOKEN);
            }
            session.beginTransaction();
            session.delete(session1);
            session.getTransaction().commit();
        }
    }

    @Override
    public void consentNomination(User user) throws ServerException {
        try (Session session = getSession()) {
            String str = "from Candidate where user.id= :param";
            Query<Candidate> query = session.createQuery(str, Candidate.class);
            query.setParameter("param", user.getId());
            Candidate candidate = query.getSingleResult();
            session.beginTransaction();
            candidate.setConsentToNomination(ConsentToNomination.AGREE);
            session.update(candidate);
            session.getTransaction().commit();
        }catch (NoResultException e){
            throw new ServerException(ServerErrorCode.WRONG_ID);
        }
    }

    @Override
    public void nominateCandidate(User nominated, User suggest) {
        try (Session session = getSession()) {
            User user = session.get(User.class,nominated.getId());
            Candidate candidate = new Candidate(user,ConsentToNomination.DISAGREE);
            session.beginTransaction();
            user.setNomination(Nomination.NOMINATED);
            session.update(user);
            session.save(candidate);
            session.getTransaction().commit();
        }
    }

    @Override
    public void nominateYourself(User nominated) {
        try (Session session = getSession()) {
            User user = session.get(User.class, nominated.getId());
            Candidate candidate = new Candidate(user,ConsentToNomination.AGREE);
            session.beginTransaction();
            user.setNomination(Nomination.NOMINATED);
            session.update(user);
            session.save(candidate);
            session.getTransaction().commit();
        }
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<Proposal> getAllProposalByUser(List<Integer> userList) {
        try (Session session = getSession()) {
            return session.createQuery
                    ("<script> SELECT * FROM Proposal WHERE <foreach item='item' " +
                            "collection='list' separator=','> idUser = item.id </foreach> </script>").list();
        }
    }

    @Override
    public User getUserById(int idUser){
        try (Session session = getSession()) {
            return session.get(User.class, idUser);
        }
    }

    @Override
    public User getUserByLogin(String login, String password) throws ServerException {
        try (Session session = getSession()) {
            String str = "from User where login= :param and password = :param1";
            Query<User> query = session.createQuery(str, User.class);
            query.setParameter("param", login);
            query.setParameter("param1", password);
            User user = query.getSingleResult();
            if (user == null) {
                throw new ServerException(ServerErrorCode.LOGIN_IS_INCORRECT);
            }
            return session.get(User.class,user.getId());
        }
    }

    @Override
    public void rateProposal(EvaluationProposal evaluationProposal) {
        try (Session session = getSession()) {
            session.beginTransaction();
            session.save(evaluationProposal);
            session.getTransaction().commit();
        }
    }

    @Override
    public void cancelRateProposal(Proposal proposal, User user) {
        try (Session session = getSession()) {
            Query<EvaluationProposal> query = session.createQuery(
                    "FROM EvaluationProposal WHERE idUser = :param AND idProposal = :param1",EvaluationProposal.class);
            query.setParameter("param", user.getId());
            query.setParameter("param1",proposal.getId());
            session.beginTransaction();
            session.delete(query.getSingleResult());
            session.getTransaction().commit();
        }
    }

    @Override
    public void addProposal(Proposal proposal) throws ServerException {
        try (Session session = getSession()) {
            session.beginTransaction();
            session.save(proposal);
            session.getTransaction().commit();
        }
    }

    @Override
    public Proposal getProposalById(int idProposal) {
        try (Session session = getSession()) {
            return session.get(Proposal.class,idProposal);

        }
    }

    @Override
    public void deleteAll() {
        try (Session session = getSession()) {
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query<User> deleteUsers = session.createQuery("DELETE FROM User");
            deleteUsers.executeUpdate();
            session.getTransaction().commit();
        }
    }

}
