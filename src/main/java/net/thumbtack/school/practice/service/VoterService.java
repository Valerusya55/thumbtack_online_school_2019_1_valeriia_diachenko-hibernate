package net.thumbtack.school.practice.service;

import com.google.gson.Gson;
import net.thumbtack.school.practice.commonresponse.CommonResponse;
import net.thumbtack.school.practice.dao.SessionDao;
import net.thumbtack.school.practice.dao.UserDao;
import net.thumbtack.school.practice.daoimpl.SessionDaoImpl;
import net.thumbtack.school.practice.daoimpl.UserDaoImpl;
import net.thumbtack.school.practice.rest.request.voter.*;
import net.thumbtack.school.practice.rest.response.voter.*;
import net.thumbtack.school.practice.exeption.ServerErrorCode;
import net.thumbtack.school.practice.exeption.ServerException;
import net.thumbtack.school.practice.model.EvaluationProposal;
import net.thumbtack.school.practice.model.Proposal;
import net.thumbtack.school.practice.model.User;
import net.thumbtack.school.practice.utils.ServiceUtils;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class VoterService extends ServiceBase {
    private UserDao userDao;
    private SessionDao sessionDao;
    private static Gson gson;

    public VoterService() {
        this.userDao = new UserDaoImpl();
        this.sessionDao = new SessionDaoImpl();
        gson = new Gson();
    }

    public Response addProposal(AddProposalDtoRequest addProposalDtoRequest, String token) {
        try {
            User user = sessionDao.getUserByToken(token);
            if (user == null) {
                throw new ServerException(ServerErrorCode.WRONG_TOKEN);
            }
            Proposal proposal = new Proposal(user, addProposalDtoRequest.getProposal());
            userDao.addProposal(proposal);
            AddProposalDtoResponse response = new AddProposalDtoResponse(CommonResponse.ADD_PROPOSAL, proposal.getId());
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ServerException ex) {
            return ServiceUtils.failureResponse(ex);
        }
    }

    public Response getProposalById(int id, String token) {
        try {
            User user = sessionDao.getUserByToken(token);
            if (user == null) {
                throw new ServerException(ServerErrorCode.WRONG_TOKEN);
            }
            GetProposalByIdDtoResponse response = new GetProposalByIdDtoResponse
                    (userDao.getProposalById(id));
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ServerException ex) {
            return ServiceUtils.failureResponse(ex);
        }
    }

    public Response rateProposal(RateProposalDtoRequest rateProposalDtoRequest, String token) {
        try {
            Proposal proposal = userDao.getProposalById(rateProposalDtoRequest.getIdProposal());
            if (proposal == null) {
                throw new ServerException(ServerErrorCode.WRONG_ID_PROPOSAL);
            }
            User user = sessionDao.getUserByToken(token);
            if (user == null) {
                throw new ServerException(ServerErrorCode.WRONG_TOKEN);
            }
            EvaluationProposal evaluationProposals = new EvaluationProposal(user, rateProposalDtoRequest.getMark(),proposal.getId());
            userDao.rateProposal(evaluationProposals);
            RateProposalDtoResponse response = new RateProposalDtoResponse(CommonResponse.RATE_PROPOSAL, evaluationProposals.getRating());
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ServerException ex) {
            return ServiceUtils.failureResponse(ex);
        }
    }

    public Response cancelRateProposal(RateProposalDtoRequest cancelRateProposalDtoRequest, String token) {
        try {
            Proposal proposal = userDao.getProposalById(cancelRateProposalDtoRequest.getIdProposal());
            if (proposal == null) {
                throw new ServerException(ServerErrorCode.WRONG_ID);
            }
            User user = sessionDao.getUserByToken(token);
            if (user == null) {
                throw new ServerException(ServerErrorCode.WRONG_TOKEN);
            }
            userDao.cancelRateProposal(proposal, user);
            CancelRateProposalDtoResponse response = new CancelRateProposalDtoResponse
                    (CommonResponse.CANCEL_RATE_PROPOSAL, proposal.getId());
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ServerException ex) {
            return ServiceUtils.failureResponse(ex);
        }
    }

  /*  private void removeRating(Proposal proposal, User user) throws ServerException {
        List<EvaluationProposal> ratings = proposal.getRatings();
        for (int i = 0; i < ratings.size(); i++) {
            if (ratings.get(i).getUser().getId() == user.getId()) {
                ratings.remove(i);
                return;
            }
        }
        throw new ServerException(ServerErrorCode.USER_NOT_RATE);
    }*/
}
