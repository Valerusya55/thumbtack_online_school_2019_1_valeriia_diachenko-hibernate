package net.thumbtack.school.practice.rest.response.vote;

import net.thumbtack.school.practice.model.Candidate;

public class CountingOfVotesDtoResponse {
    private Candidate candidate;

    public CountingOfVotesDtoResponse(Candidate candidate) {
        this.candidate = candidate;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}
