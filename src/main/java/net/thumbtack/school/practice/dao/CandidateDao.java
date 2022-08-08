package net.thumbtack.school.practice.dao;

import net.thumbtack.school.practice.exeption.ServerException;
import net.thumbtack.school.practice.model.Candidate;
import net.thumbtack.school.practice.model.Proposal;

import java.util.List;

public interface CandidateDao {
    void removeProposalFromProgram(Candidate candidate, int proposalId) throws ServerException;

    void addProposalToProgram(Candidate candidate, int proposalId);

    void cancelNomination(Candidate candidate) throws ServerException;

    List<Proposal> getProposalByUserId(Candidate candidate) throws ServerException;

    Candidate getCandidateByUserToken(String token) throws ServerException;

    List<Candidate> getAllCandidatesWithProgram();

    Candidate getCandidateByUserId(Integer id) throws ServerException;

    void deleteAll();
}
