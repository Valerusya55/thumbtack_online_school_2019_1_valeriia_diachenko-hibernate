package net.thumbtack.school.practice.rest.response.voter;

import net.thumbtack.school.practice.commonresponse.CommonResponse;

public class CancelRateProposalDtoResponse {
    private CommonResponse result;
    private int idProposal;

    public CancelRateProposalDtoResponse(CommonResponse result,int idProposal) {
        this.result = result;
        this.idProposal = idProposal;
    }

    public int getIdProposal() {
        return idProposal;
    }

    public void setIdProposal(int idProposal) {
        this.idProposal = idProposal;
    }

    public CommonResponse getResult() {
        return result;
    }

    public void setResult(CommonResponse result) {
        this.result = result;
    }
}
