package net.thumbtack.school.practice.service;

import com.google.gson.Gson;
import net.thumbtack.school.practice.commonresponse.CommonResponse;
import net.thumbtack.school.practice.dao.CandidateDao;
import net.thumbtack.school.practice.dao.SessionDao;
import net.thumbtack.school.practice.dao.UserDao;
import net.thumbtack.school.practice.dao.VoteDao;
import net.thumbtack.school.practice.daoimpl.CandidateDaoImpl;
import net.thumbtack.school.practice.daoimpl.SessionDaoImpl;
import net.thumbtack.school.practice.daoimpl.UserDaoImpl;
import net.thumbtack.school.practice.daoimpl.VoteDaoImpl;
import net.thumbtack.school.practice.rest.response.vote.*;
import net.thumbtack.school.practice.exeption.ServerErrorCode;
import net.thumbtack.school.practice.exeption.ServerException;
import net.thumbtack.school.practice.model.Candidate;
import net.thumbtack.school.practice.model.User;
import net.thumbtack.school.practice.utils.ServiceUtils;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class VoteService extends ServiceBase {
    private VoteDao voteDao;
    private CandidateDao candidateDao;
    private UserDao userDao;
    private SessionDao sessionDao;
    private static Gson gson;

    public VoteService() {
        this.voteDao = new VoteDaoImpl();
        this.candidateDao = new CandidateDaoImpl();
        this.userDao = new UserDaoImpl();
        this.sessionDao = new SessionDaoImpl();
        gson = new Gson();
    }

    public Response voteForCandidate(String tokenVoter, int idCandidate) {
        try {
            User user = sessionDao.getUserByToken(tokenVoter);
            if (user == null) {
                throw new ServerException(ServerErrorCode.WRONG_TOKEN);
            }
            Candidate candidate = candidateDao.getCandidateByUserId(idCandidate);
            if (candidate == null) {
                throw new ServerException(ServerErrorCode.WRONG_JSON);
            }
            voteDao.voteForCandidate(user, candidate);
            VoteForCandidateDtoResponse response = new VoteForCandidateDtoResponse(CommonResponse.VOTED_FOR_CANDIDATE);
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ServerException ex) {
            return ServiceUtils.failureResponse(ex);
        }
    }

    public Response voteAgainstAll(String token) {
        try {
            User user = sessionDao.getUserByToken(token);
            if (user == null) {
                throw new ServerException(ServerErrorCode.WRONG_TOKEN);
            }
            voteDao.voteAgainstAll(user);
            VoteAgainstAllDtoResponse response = new VoteAgainstAllDtoResponse(CommonResponse.VOTED_AGAINST_ALL);
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ServerException ex) {
            return ServiceUtils.failureResponse(ex);
        }
    }

    public Response countingOfVotes(String json) {
        try {
            CountingOfVotesDtoResponse response = new CountingOfVotesDtoResponse(voteDao.countingOfVotes());
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ServerException ex) {
            return ServiceUtils.failureResponse(ex);
        }
    }
}
