package net.thumbtack.school.practice.service;

import com.google.gson.Gson;
import net.thumbtack.school.practice.commonresponse.CommonResponse;
import net.thumbtack.school.practice.dao.SessionDao;
import net.thumbtack.school.practice.daoimpl.SessionDaoImpl;
import net.thumbtack.school.practice.daoimpl.UserDaoImpl;
import net.thumbtack.school.practice.rest.request.user.*;
import net.thumbtack.school.practice.rest.response.user.*;
import net.thumbtack.school.practice.exeption.ServerErrorCode;
import net.thumbtack.school.practice.exeption.ServerException;
import net.thumbtack.school.practice.dao.UserDao;
import net.thumbtack.school.practice.model.Session;
import net.thumbtack.school.practice.model.User;
import net.thumbtack.school.practice.utils.ServiceUtils;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

public class UserService extends ServiceBase {
    private UserDao userDao;
    private SessionDao sessionDao;
    private static Gson gson;

    public UserService() {
        this.sessionDao = new SessionDaoImpl();
        this.userDao = new UserDaoImpl();
        gson = new Gson();
    }

    public Response registerUser(RegisterUserDtoRequest registerUserDtoRequest) {
        try {
            User user = new User(registerUserDtoRequest.getName(), registerUserDtoRequest.getSurname(),
                    registerUserDtoRequest.getPatronymic(), registerUserDtoRequest.getStreet(),
                    registerUserDtoRequest.getNumberHouse(), registerUserDtoRequest.getNumberFlat(),
                    registerUserDtoRequest.getLogin(), registerUserDtoRequest.getPassword());
            userDao.registerUser(user);
            String token = UUID.randomUUID().toString();
            Session session = new Session(token, user);
            sessionDao.addToken(session);
            RegisterUserDtoResponse response = new RegisterUserDtoResponse(token, user.getId());
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ServerException ex) {
            return ServiceUtils.failureResponse(ex);
        }
    }

    public Response loginUser(LoginDtoRequest loginDtoRequest) {
        try {
            User user = userDao.getUserByLogin(loginDtoRequest.getLogin(), loginDtoRequest.getPassword());
            if (user == null) {
                throw new ServerException(ServerErrorCode.LOGIN_IS_INCORRECT);
            }
            String token = UUID.randomUUID().toString();
            Session session = new Session(token, user);
            sessionDao.addToken(session);
            LoginDtoResponse response = new LoginDtoResponse(CommonResponse.SUCCESSFULLY_LOGGED,token);
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ServerException ex) {
            return ServiceUtils.failureResponse(ex);
        }
    }

    public Response logOutUser(String token) throws ServerException {
        try {
            User user = sessionDao.getUserByToken(token);
            if (user == null) {
                throw new ServerException(ServerErrorCode.WRONG_TOKEN);
            }
            userDao.logoutUser(token);
            DeleteUserResponse response = new DeleteUserResponse(CommonResponse.YOU_REMOVE_SERVER);
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ServerException ex) {
            return ServiceUtils.failureResponse(ex);
        }
    }

    public Response consentNomination(String token) {
        try {
            User user = sessionDao.getUserByToken(token);
            if (user == null) {
                throw new ServerException(ServerErrorCode.WRONG_ID);
            }
            userDao.consentNomination(user);
            ConsentNominationDtoResponse response = new ConsentNominationDtoResponse
                    (CommonResponse.CONSENT_NOMINATION, token);
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ServerException ex) {
            return ServiceUtils.failureResponse(ex);
        }
    }

    public Response nominateCandidate(int id, String token) {
        try {
            User nominated = sessionDao.getUserByToken(token);
            if (nominated == null) {
                throw new ServerException(ServerErrorCode.WRONG_TOKEN);
            }
            User suggest = userDao.getUserById(id);
            if (suggest == null) {
                throw new ServerException(ServerErrorCode.WRONG_ID);
            }
            userDao.nominateCandidate(nominated, suggest);
            NominateCandidateDtoResponse response = new NominateCandidateDtoResponse
                    (CommonResponse.NOMINATE, token);
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ServerException ex) {
            return ServiceUtils.failureResponse(ex);
        }
    }

    public Response nominateYourself(String token) {
        try {
            User user = sessionDao.getUserByToken(token);
            if (user == null) {
                throw new ServerException(ServerErrorCode.WRONG_TOKEN);
            }
            userDao.nominateYourself(user);
            NominateYourselfDtoResponse response = new NominateYourselfDtoResponse(CommonResponse.NOMINATE);
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ServerException ex) {
            return ServiceUtils.failureResponse(ex);
        }
    }

    public Response getAllProposalByUser(GetAllProposalByUserDtoRequest getAllProposalByUserDtoRequest, String token) {
        try {
            User user = sessionDao.getUserByToken(token);
            if (user == null) {
                throw new ServerException(ServerErrorCode.WRONG_TOKEN);
            }
            GetAllProposalByUserDtoResponse response = new GetAllProposalByUserDtoResponse
                    (userDao.getAllProposalByUser(getAllProposalByUserDtoRequest.getUsersId()));
            return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (ServerException ex) {
            return ServiceUtils.failureResponse(ex);
        }
    }
}
