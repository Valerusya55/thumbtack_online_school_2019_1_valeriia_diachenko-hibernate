package net.thumbtack.school.practice.rest.request.candidate;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class GetProposalByUserIdDtoRequest {
    @NotBlank
    private String token;

    public GetProposalByUserIdDtoRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetProposalByUserIdDtoRequest that = (GetProposalByUserIdDtoRequest) o;
        return Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }
}
