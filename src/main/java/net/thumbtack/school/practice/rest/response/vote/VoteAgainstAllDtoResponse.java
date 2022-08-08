package net.thumbtack.school.practice.rest.response.vote;

import net.thumbtack.school.practice.commonresponse.CommonResponse;

public class VoteAgainstAllDtoResponse {
    private CommonResponse result;

    public VoteAgainstAllDtoResponse(CommonResponse result) {
        this.result = result;
    }

    public CommonResponse getResult() {
        return result;
    }

    public void setResult(CommonResponse result) {
        this.result = result;
    }
}
