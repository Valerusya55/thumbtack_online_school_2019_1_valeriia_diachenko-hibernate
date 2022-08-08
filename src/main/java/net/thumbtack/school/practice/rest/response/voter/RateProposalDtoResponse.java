package net.thumbtack.school.practice.rest.response.voter;

import net.thumbtack.school.practice.commonresponse.CommonResponse;

public class RateProposalDtoResponse {
    private CommonResponse result;
    private double rating;

    public RateProposalDtoResponse(CommonResponse result, double rating) {
        this.result = result;
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public CommonResponse getResult() {
        return result;
    }

    public void setResult(CommonResponse result) {
        this.result = result;
    }
}
