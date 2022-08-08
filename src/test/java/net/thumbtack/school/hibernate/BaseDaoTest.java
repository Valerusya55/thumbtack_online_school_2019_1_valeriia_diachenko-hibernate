package net.thumbtack.school.hibernate;

import net.thumbtack.school.practice.dao.*;
import net.thumbtack.school.practice.daoimpl.*;
import net.thumbtack.school.practice.model.Candidate;
import net.thumbtack.school.practice.model.Program;
import net.thumbtack.school.practice.model.Proposal;
import net.thumbtack.school.practice.model.User;
import org.junit.Assume;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseDaoTest {
    protected UserDao userDao = new UserDaoImpl();
    protected CandidateDao candidateDao = new CandidateDaoImpl();
    protected SessionDao sessionDao = new SessionDaoImpl();
    protected VoteDao voteDao = new VoteDaoImpl();


    @BeforeAll()
    public static void init() {
        try {
            HibernateUtils.buildSessionFactory();
        } catch (ExceptionInInitializerError ex) {
            Assume.assumeNoException(ex);
        }

    }

    @BeforeEach()
    public void clearDatabase() {
        userDao.deleteAll();
        candidateDao.deleteAll();
        sessionDao.deleteAll();
    }

    protected void checkUserFields(User user1, User user2) {
        assertEquals(user1.getName(), user2.getName());
        assertEquals(user1.getSurname(), user2.getSurname());
        assertEquals(user1.getPatronymic(), user2.getPatronymic());
        assertEquals(user1.getLogin(), user2.getLogin());
        assertEquals(user1.getPassword(), user2.getPassword());
        assertEquals(user1.getStreet(), user2.getStreet());
        assertEquals(user1.getNumberHouse(), user2.getNumberHouse());
        assertEquals(user1.getNumberFlat(), user2.getNumberFlat());
        assertEquals(user1.getNomination(), user2.getNomination());
    }

    protected void checkCandidateFields(Candidate candidate1, Candidate candidate2) {
        assertEquals(candidate1.getUser(),candidate2.getUser());
        assertEquals(candidate1.getConsentToNomination(),candidate2.getConsentToNomination());
    }

    protected void checkUserProposals(List<Proposal> proposalList1, List<Proposal> proposalList2) {
        List<Proposal> list1 = new ArrayList<>(proposalList1);
        List<Proposal> list2 = new ArrayList<>(proposalList2);
        list1.sort((p1, p2) -> (Integer.compare(p1.getId(), p2.getId())));
        list2.sort((p1, p2) -> (Integer.compare(p1.getId(), p2.getId())));
        assertEquals(list1, list2);
    }

    protected void checkCandidateProgram(Program program1, Program program2) {
        assertEquals(program1, program2);
    }
}
