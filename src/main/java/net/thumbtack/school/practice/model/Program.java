package net.thumbtack.school.practice.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name="program")
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser")
    private User user;

   /*@LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "programProposal",joinColumns = @JoinColumn(name = "idProposal"),inverseJoinColumns = {
            @JoinColumn(name = "idProgram")})
    private List<Proposal> proposal;*/

}
