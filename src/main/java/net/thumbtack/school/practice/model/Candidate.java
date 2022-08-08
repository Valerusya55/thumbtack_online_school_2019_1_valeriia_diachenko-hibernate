package net.thumbtack.school.practice.model;

import net.thumbtack.school.practice.enumformodel.ConsentToNomination;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser")
    private User user;

    @Column(columnDefinition = "ENUM ('AGREE','DISAGREE')")
    @Enumerated(EnumType.STRING)
    private ConsentToNomination consentToNomination;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Candidate() {
    }

    public Candidate(User user, ConsentToNomination consentToNomination) {
        this.user = user;
        this.consentToNomination = consentToNomination;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ConsentToNomination getConsentToNomination() {
        return consentToNomination;
    }

    public void setConsentToNomination(ConsentToNomination consentToNomination) {
        this.consentToNomination = consentToNomination;
    }



}
