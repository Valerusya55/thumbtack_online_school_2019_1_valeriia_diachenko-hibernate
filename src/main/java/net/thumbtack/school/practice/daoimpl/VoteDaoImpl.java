package net.thumbtack.school.practice.daoimpl;

import net.thumbtack.school.practice.dao.VoteDao;
import net.thumbtack.school.practice.exeption.ServerException;
import net.thumbtack.school.practice.model.Candidate;
import net.thumbtack.school.practice.model.User;

public class VoteDaoImpl implements VoteDao {
    @Override
    public void voteForCandidate(User user, Candidate candidate) {

    }

    @Override
    public void voteAgainstAll(User user) {

    }

    @Override
    public Candidate countingOfVotes() throws ServerException {
        return null;
    }
}
