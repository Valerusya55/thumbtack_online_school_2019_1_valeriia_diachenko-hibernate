package net.thumbtack.school.practice.rest.response.user;

import net.thumbtack.school.practice.commonresponse.CommonResponse;

public class LoginDtoResponse {
    private CommonResponse result;
    private String token;

    public LoginDtoResponse() {
    }

    public LoginDtoResponse(CommonResponse result, String token) {
        this.result = result;
        this.token = token;
    }

    public CommonResponse getResult() {
        return result;
    }

    public void setResult(CommonResponse result) {
        this.result = result;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
