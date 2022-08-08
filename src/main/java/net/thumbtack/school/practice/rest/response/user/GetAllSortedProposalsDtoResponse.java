package net.thumbtack.school.practice.rest.response.user;

import net.thumbtack.school.practice.model.Proposal;

import java.util.List;

public class GetAllSortedProposalsDtoResponse {
    private List<Proposal> proposalDouble;

    public GetAllSortedProposalsDtoResponse(List<Proposal> proposalDoubleMap) {
        this.proposalDouble = proposalDoubleMap;
    }

    public List<Proposal> getProposalDouble() {
        return proposalDouble;
    }

    public void setProposalDouble(List<Proposal> proposalDouble) {
        this.proposalDouble = proposalDouble;
    }
}
