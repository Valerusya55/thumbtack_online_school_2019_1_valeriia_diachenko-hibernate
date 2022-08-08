package net.thumbtack.school.practice.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="proposal")
public class Proposal implements Comparable<Proposal>{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="idUser")
    private User author;
    @Column
    private String proposal;
    /*@Fetch(FetchMode.SELECT)
    @ManyToMany(mappedBy = "proposal",fetch = FetchType.EAGER)
    private List<Program> program;*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private static final int MAX_RATING = 5;

    public Proposal(User author, String proposal) {
        this.author = author;
        this.proposal = proposal;

    }

    public Proposal() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getProposal() {
        return proposal;
    }

    public void setProposal(String proposal) {
        this.proposal = proposal;
    }

    @Override
    public int compareTo(Proposal o) {
        return this.getId() - o.getId();
    }
}
