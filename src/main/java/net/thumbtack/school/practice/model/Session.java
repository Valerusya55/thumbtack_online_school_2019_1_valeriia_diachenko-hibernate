package net.thumbtack.school.practice.model;

import javax.persistence.*;
import java.util.Objects;
@Entity
@Table(name="session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String token;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="idUser")
    private User user;

    public Session() {
    }

    public Session(String token, User user) {
        this(0,token,user);
    }

    public Session(int id, String token, User user) {
        this.id = id;
        this.token = token;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Session)) return false;
        Session session = (Session) o;
        return getId() == session.getId() && Objects.equals(getToken(), session.getToken()) && Objects.equals(getUser(), session.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getToken(), getUser());
    }
}
