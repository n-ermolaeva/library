package com.library.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @Getter
    @Setter
    private Set<Role> roles;

    public Users(Integer id,
                 String name,
                 Set<Role> roles) {
        this.id = id;
        this.username = name;
        this.roles = roles;

    }

    public Users() {
    }

}