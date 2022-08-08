package net.thumbtack.school.practice.service;

import com.google.gson.Gson;
import javax.ws.rs.core.Response;
import net.thumbtack.school.practice.commonresponse.CommonResponse;
import net.thumbtack.school.practice.dao.CandidateDao;
import net.thumbtack.school.practice.dao.SessionDao;
import net.thumbtack.school.practice.dao.UserDao;
import net.thumbtack.school.practice.rest.request.candidate.*;
import net.thumbtack.school.practice.rest.response.candidate.*;
import net.thumbtack.school.practice.daoimpl.CandidateDaoImpl;
import net.thumbtack.school.practice.daoimpl.SessionDaoImpl;
import net.thumbtack.school.practice.daoimpl.UserDaoImpl;
import net.thumbtack.school.practice.exeption.ServerErrorCode;
import net.thumbtack.school.practice.exeption.ServerException;
import net.thumbtack.school.practice.model.Candidate;
import net.thumbtack.school.practice.model.User;
import net.thumbtack.school.practice.utils.ServiceUtils;

import javax.ws.rs.core.MediaType;

public class CandidateService extends ServiceBase {
    private UserDao userDao;
    private SessionDao sessionDao;
    private CandidateDao candidateDao;
    private static Gson gson;

    public CandidateService() {
        this.userDao = new UserDaoImpl();
        this.candidateDao = new CandidateDaoImpl();
        this.sessionDao = new SessionDaoImpl();
        gson = new Gson();
    }

    public Response removeProposalFromProgram(int id, String token) {
        try {
            Candidate candidate = candidateDao.getCandidateByUserToken(token);
            if (candidate == null) {
                throw new ServerException(ServerErrorCode.WRONG_TOKEN);
            }
            candidateDao.removeProposalFromProgram(candidate, id);
            RemoveProposalFromProgramDtoResponse response =
                    new RemoveProposalFromProgramDtoResponse(CommonResponse.REMOVE_PROPOSAL);
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ServerException ex) {
            return ServiceUtils.failureResponse(ex);
        }
    }

    public Response addProposalToProgram(int id, String token) {
        try {
            Candidate candidate = candidateDao.getCandidateByUserToken(token);
            if (candidate == null) {
                throw new ServerException(ServerErrorCode.WRONG_TOKEN);
            }
            candidateDao.addProposalToProgram(candidate, id);
            AddProposalToProgramDtoResponse response =
                    new AddProposalToProgramDtoResponse(CommonResponse.ADD_PROPOSAL_TO_PROGRAM);
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ServerException ex) {
            return ServiceUtils.failureResponse(ex);
        }
    }

    public Response cancelNomination(String token) {
        try {
            Candidate candidate = candidateDao.getCandidateByUserToken(token);
            if (candidate == null) {
                throw new ServerException(ServerErrorCode.WRONG_TOKEN);
            }
            candidateDao.cancelNomination(candidate);
            CancelNominationDtoResponse response = new CancelNominationDtoResponse(CommonResponse.YOU_NO_CANDIDATE);
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ServerException ex) {
            return ServiceUtils.failureResponse(ex);
        }
    }

    public Response getProposalByUserId(String token) {
        try {
            Candidate candidate = candidateDao.getCandidateByUserToken(token);
            if (candidate == null) {
                throw new ServerException(ServerErrorCode.WRONG_TOKEN);
            }
            GetProposalByUserIdDtoResponse response =
                    new GetProposalByUserIdDtoResponse(candidateDao.getProposalByUserId(candidate));
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ServerException ex) {
            return ServiceUtils.failureResponse(ex);
        }
    }

    public Response getAllCandidatesWithProgram(String token) {
        try {
            User user = sessionDao.getUserByToken(token);
            if (user == null) {
                throw new ServerException(ServerErrorCode.WRONG_TOKEN);
            }
            GetAllCandidatesWithProgramDtoResponse response =
                    new GetAllCandidatesWithProgramDtoResponse(candidateDao.getAllCandidatesWithProgram());
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ServerException ex) {
            return ServiceUtils.failureResponse(ex);
        }
    }
}
