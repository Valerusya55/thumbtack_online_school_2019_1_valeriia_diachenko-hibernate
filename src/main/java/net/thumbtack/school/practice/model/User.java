package net.thumbtack.school.practice.model;
import net.thumbtack.school.practice.enumformodel.Nomination;
import javax.persistence.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="user")
public class User {
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String patronymic;
    @Column
    private String street;
    @Column
    private String numberHouse;
    @Column
    private String numberFlat;
    @Column
    private String login;
    @Column
    private String password;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(columnDefinition = "ENUM ('NOT_NOMINATED','NOMINATED')")
    @Enumerated(EnumType.STRING)
    private Nomination nomination;

    @OneToMany (cascade =  CascadeType.ALL, mappedBy="author",fetch = FetchType.EAGER)
    private List<Proposal> userProposals = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "user")
    private Candidate candidate;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "user")
    private Session session;

    public User() {
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public User(String name, String surname, String patronymic, String street,
                String numberHouse, String numberFlat, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.street = street;
        this.numberHouse = numberHouse;
        this.numberFlat = numberFlat;
        this.login = login;
        this.password = password;
        this.nomination = Nomination.NOT_NOMINATED;
        this.id = 0;
    }

    public Nomination getNomination() {
        return nomination;
    }

    public void setNomination(Nomination nomination) {
        this.nomination = nomination;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumberHouse() {
        return numberHouse;
    }

    public void setNumberHouse(String numberHouse) {
        this.numberHouse = numberHouse;
    }

    public String getNumberFlat() {
        return numberFlat;
    }

    public void setNumberFlat(String numberFlat) {
        this.numberFlat = numberFlat;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                nomination == user.nomination &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(patronymic, user.patronymic) &&
                Objects.equals(street, user.street) &&
                Objects.equals(numberHouse, user.numberHouse) &&
                Objects.equals(numberFlat, user.numberFlat) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, patronymic, street, numberHouse, numberFlat, login, password, id, nomination);
    }

    public void addProposal(Proposal proposal) {
        userProposals.add(proposal);
    }

    public List<Proposal> getUserProposals() {
        return userProposals;
    }

    public void setUserProposals(List<Proposal> userProposals) {
        this.userProposals = userProposals;
    }

}
