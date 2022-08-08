package net.thumbtack.school.practice.rest.response.candidate;

import net.thumbtack.school.practice.commonresponse.CommonResponse;

public class CancelNominationDtoResponse {
    private CommonResponse result;

    public CancelNominationDtoResponse(CommonResponse result) {
        this.result = result;
    }

    public CommonResponse getResult() {
        return result;
    }

    public void setResult(CommonResponse result) {
        this.result = result;
    }
}
