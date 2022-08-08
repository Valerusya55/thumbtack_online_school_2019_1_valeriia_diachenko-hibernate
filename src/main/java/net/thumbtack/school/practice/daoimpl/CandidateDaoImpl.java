package net.thumbtack.school.practice.daoimpl;
import net.thumbtack.school.practice.dao.CandidateDao;
import net.thumbtack.school.practice.enumformodel.ConsentToNomination;
import net.thumbtack.school.practice.enumformodel.Nomination;
import net.thumbtack.school.practice.exeption.ServerErrorCode;
import net.thumbtack.school.practice.exeption.ServerException;
import net.thumbtack.school.practice.model.Candidate;
import net.thumbtack.school.practice.model.Program;
import net.thumbtack.school.practice.model.Proposal;
import net.thumbtack.school.practice.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class CandidateDaoImpl extends BaseDaoImpl implements CandidateDao {

    @Override
    public void removeProposalFromProgram(Candidate candidate, int proposalId) throws ServerException {
        try (Session session = getSession()) {
            Proposal proposal = session.get(Proposal.class, proposalId);
            if(proposal == null){
                throw new ServerException(ServerErrorCode.WRONG_ID);
            }
            session.beginTransaction();
            session.delete(proposal);
            session.getTransaction().commit();
        }
    }

   @Override
   public void addProposalToProgram(Candidate candidate, int proposalId) {
       try (Session session = getSession()) {
           Proposal proposal = session.get(Proposal.class,proposalId);
           //Program program = new Program(candidate.getUser(),new ArrayList<>(proposal.getId()));
           session.beginTransaction();
          // session.save(program);
           session.getTransaction().commit();
       }
   }

   @Override
   public void cancelNomination(Candidate candidate) throws ServerException {
       try (Session session = getSession()) {
           User user = session.get(User.class, candidate.getUser().getId());
           Candidate candidate1 = session.get(Candidate.class,candidate.getId());
           session.beginTransaction();
           user.setNomination(Nomination.NOT_NOMINATED);
           session.delete(candidate1);
           session.update(user);
           session.getTransaction().commit();
       }catch (NoResultException e){
           throw new ServerException(ServerErrorCode.WRONG_ID);
       }
   }

    @Override
    public List<Proposal> getProposalByUserId(Candidate candidate) throws ServerException {
        try (Session session = getSession()) {
            String str = "FROM Proposal WHERE idUser = :param";
            Query<Proposal> query = session.createQuery(str,Proposal.class);
            query.setParameter("param",candidate.getUser().getId());
            if(query.list().isEmpty()){
                throw new ServerException(ServerErrorCode.THE_DATA_IS_INCORRECT);
            }
            return query.list();
        }
    }

   @Override
   public Candidate getCandidateByUserToken(String token) throws ServerException {
       try (Session session = getSession()) {
           String str = "from Session where token=:param";
           Query<net.thumbtack.school.practice.model.Session> query =
                   session.createQuery(str, net.thumbtack.school.practice.model.Session.class);
           query.setParameter("param", token);
           net.thumbtack.school.practice.model.Session session1 = query.getSingleResult();
           String str1 = "from Candidate where user.id= :param";
           Query<Candidate> query1 = session.createQuery(str1, Candidate.class);
           query1.setParameter("param", session1.getUser().getId());
           Candidate candidate = query1.getSingleResult();
           if (candidate == null) {
               throw new ServerException(ServerErrorCode.WRONG_TOKEN);
           }
           return session.get(Candidate.class, candidate.getId());
       }
   }

    @Override
    public List<Candidate> getAllCandidatesWithProgram() {
        try (Session session = getSession()) {
            return session.createQuery("FROM Candidate",Candidate.class).list();
        }
    }

    @Override
    public Candidate getCandidateByUserId(Integer id) throws ServerException {
        try (Session session = getSession()) {
            String str = "from Candidate where user.id= :param";
            Query<Candidate> query = session.createQuery(str, Candidate.class);
            query.setParameter("param", id);
            Candidate candidate = query.getSingleResult();
            if (candidate == null) {
                throw new ServerException(ServerErrorCode.WRONG_ID);
            }
            return session.get(Candidate.class, candidate.getId());
        }
    }

    @Override
    public void deleteAll() {
        try (Session session = getSession()) {
            session.beginTransaction();
            @SuppressWarnings("unchecked")
            Query<User> deleteCandidates = session.createQuery("DELETE FROM Candidate");
            deleteCandidates.executeUpdate();
            session.getTransaction().commit();
        }
    }

}
