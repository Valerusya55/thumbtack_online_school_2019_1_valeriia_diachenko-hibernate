package net.thumbtack.school.practice.resources;
import net.thumbtack.school.practice.rest.request.voter.*;
import net.thumbtack.school.practice.service.VoterService;


import javax.ws.rs.*;
import javax.validation.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/api")
public class VoterResources {
    private VoterService voterService = new VoterService();

    @POST
    @Path("/proposal/")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addProposal(@Valid AddProposalDtoRequest request, @HeaderParam("token") String token) {
        return voterService.addProposal(request, token);
    }

    @GET
    @Path("/proposal/{id}")
    @Produces("application/json")
    public Response getProposalById(@PathParam("id") int id, @HeaderParam("token") String token) {
        return voterService.getProposalById(id, token);
    }

    @PUT
    @Path("/proposal/")
    @Consumes("application/json")
    @Produces("application/json")
    public Response rateProposal(RateProposalDtoRequest request, @HeaderParam("token") String token) {
        if (request.getMark() != null) {
            return voterService.rateProposal(request, token);
        } else {
            return voterService.cancelRateProposal(request, token);
        }
    }
}