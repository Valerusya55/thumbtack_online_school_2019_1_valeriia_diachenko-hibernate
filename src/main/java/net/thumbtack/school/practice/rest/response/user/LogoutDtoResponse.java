package net.thumbtack.school.practice.rest.response.user;

import net.thumbtack.school.practice.commonresponse.CommonResponse;

public class LogoutDtoResponse {
    private CommonResponse result;

    public LogoutDtoResponse(CommonResponse result) {
        this.result = result;
    }

    public CommonResponse getResult() {
        return result;
    }

    public void setResult(CommonResponse result) {
        this.result = result;
    }
}
