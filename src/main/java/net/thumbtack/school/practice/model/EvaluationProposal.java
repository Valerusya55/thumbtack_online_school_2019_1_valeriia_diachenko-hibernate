package net.thumbtack.school.practice.model;

import javax.persistence.*;
import java.util.Objects;
@Entity
@Table(name="evaluationProposal")
public class EvaluationProposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="idUser")
    private User user;
    @Column
    private int rating;
    @Column
    private int idProposal;
    public EvaluationProposal(User voter, int rating,int idProposal) {
        this.user = voter;
        this.rating = rating;
        this.idProposal = idProposal;
    }

    public EvaluationProposal() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EvaluationProposal)) return false;
        EvaluationProposal that = (EvaluationProposal) o;
        return getRating() == that.getRating() && Objects.equals(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getRating());
    }
}
