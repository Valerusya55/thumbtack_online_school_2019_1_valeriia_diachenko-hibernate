package net.thumbtack.school.practice.exeption;

public class ErrorResponse {
    private ServerErrorCode serverErrorCode;

    public ErrorResponse(ServerException serverException) {
        this.serverErrorCode = serverException.getErrorCode();
    }

    public String getErrorCode() {
        return serverErrorCode.getErrorString();
    }
}
