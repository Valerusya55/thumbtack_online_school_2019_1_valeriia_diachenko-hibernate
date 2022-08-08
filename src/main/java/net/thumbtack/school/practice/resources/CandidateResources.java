package net.thumbtack.school.practice.resources;
import net.thumbtack.school.practice.rest.request.candidate.*;
import net.thumbtack.school.practice.service.CandidateService;


import javax.ws.rs.*;
import javax.validation.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/api")
public class CandidateResources {

    private CandidateService candidateService = new CandidateService();

    @DELETE
    @Path("/proposal/{id}")
    @Produces("application/json")
    public Response removeProposalFromProgram(@PathParam("id") int id,
                                              @HeaderParam("token") String token) {
        return candidateService.removeProposalFromProgram(id, token);
    }

    @POST
    @Path("/proposal/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addProposalToProgram(@PathParam("id") int id, @HeaderParam("token") String token) {
        return candidateService.addProposalToProgram(id, token);
    }

    @PUT
    @Path("/candidate/")
    @Consumes("application/json")
    @Produces("application/json")
    public Response cancelNomination(@HeaderParam("token") String token) {
        return candidateService.cancelNomination(token);
    }

    @GET
    @Path("/proposal")
    @Produces("application/json")
    public Response getProposalByUserId(@HeaderParam("token") String token) {
        return candidateService.getProposalByUserId(token);
    }

    @GET
    @Path("/candidate/")
    @Produces("application/json")
    public Response getAllCandidatesWithProgram(@HeaderParam("token") String token) {
        return candidateService.getAllCandidatesWithProgram(token);
    }
}
