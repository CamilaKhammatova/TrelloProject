package ru.itis.khammatova.impl.entitys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String firstname;
    private String email;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private Role role = Role.USER;
    @Enumerated(value = EnumType.STRING)
    private State state = State.ACTIVE;

    public enum State {
        ACTIVE, BANNED
    }

    public enum Role {
        USER, ADMIN
    }

    public boolean isActive() { return this.state == State.ACTIVE; }
}


