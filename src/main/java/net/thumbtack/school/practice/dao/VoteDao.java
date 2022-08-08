package net.thumbtack.school.practice.dao;

import net.thumbtack.school.practice.exeption.ServerException;
import net.thumbtack.school.practice.model.Candidate;
import net.thumbtack.school.practice.model.User;


public interface VoteDao {
    void voteForCandidate(User user, Candidate candidate);

    void voteAgainstAll(User user);

    public Candidate countingOfVotes() throws ServerException;

  //  void deleteAll();
}
