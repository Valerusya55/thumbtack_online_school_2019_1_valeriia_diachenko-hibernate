package net.thumbtack.school.practice.rest.response.user;

import net.thumbtack.school.practice.commonresponse.CommonResponse;

public class DeleteUserResponse {
    private CommonResponse result;

    public DeleteUserResponse(CommonResponse result) {
        this.result = result;
    }

    public CommonResponse getResult() {
        return result;
    }

    public void setResult(CommonResponse result) {
        this.result = result;
    }
}
