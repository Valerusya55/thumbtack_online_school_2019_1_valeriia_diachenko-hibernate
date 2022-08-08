package net.thumbtack.school.practice.rest.response.voter;

import net.thumbtack.school.practice.model.Proposal;

public class GetProposalByIdDtoResponse {
    private Proposal proposal;

    public GetProposalByIdDtoResponse(Proposal proposal) {
        this.proposal = proposal;
    }

    public Proposal getProposal() {
        return proposal;
    }

    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }
}
