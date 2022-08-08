package net.thumbtack.school.practice.resources;
import net.thumbtack.school.practice.rest.request.vote.VoteAgainstAllDtoRequest;
import net.thumbtack.school.practice.rest.request.vote.VoteForCandidateDtoRequest;
import net.thumbtack.school.practice.service.VoteService;


import javax.ws.rs.*;
import javax.validation.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/api")
public class VoteResources {
    private VoteService voteService = new VoteService();

    @PUT
    @Path("/vote/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response voteForCandidate(@HeaderParam("token") String tokenVoter, @PathParam("id") int id) {
            return voteService.voteForCandidate(tokenVoter, id);
    }

    @PUT
    @Path("/vote")
    @Consumes("application/json")
    @Produces("application/json")
    public Response voteAgainstAll(@HeaderParam("token") String tokenVoter) {
        return voteService.voteAgainstAll(tokenVoter);
    }

    @GET
    @Path("/vote/")
    @Produces("application/json")
    public Response countingOfVotes(@HeaderParam("token") String token) {
        return voteService.countingOfVotes(token);
    }
}