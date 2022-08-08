package net.thumbtack.school.practice.dao;

import net.thumbtack.school.practice.exeption.ServerException;
import net.thumbtack.school.practice.model.EvaluationProposal;
import net.thumbtack.school.practice.model.Proposal;
import net.thumbtack.school.practice.model.User;

import java.util.List;

public interface UserDao {
    void registerUser(User user) throws ServerException;
    void logoutUser(String token) throws ServerException;
    void consentNomination(User user) throws ServerException;
    void nominateCandidate(User nominated, User suggest);
    void nominateYourself(User nominated);
    List<Proposal> getAllProposalByUser(List<Integer> userList);
    User getUserById(int idUser) throws ServerException;
    User getUserByLogin(String login, String password) throws ServerException;
    void rateProposal(EvaluationProposal evaluationProposals);
    void cancelRateProposal(Proposal proposal, User user);
    void addProposal(Proposal proposal) throws ServerException;
    Proposal getProposalById(int idProposal);
    void deleteAll();
}
