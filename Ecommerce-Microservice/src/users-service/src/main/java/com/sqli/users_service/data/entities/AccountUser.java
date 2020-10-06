package com.sqli.users_service.data.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Entity
@Table(name = "users")
@Builder
@Data
public class AccountUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String address;
    @NotNull
    @Column(unique = true)
    private String phone;
    @Email
    @Column(unique = true)
    private String email;
    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(name="users_roles",
        joinColumns={@JoinColumn(name="userId")},
        inverseJoinColumns={@JoinColumn(name="roleId")})
    private List<AccountRole> accountRoles = new ArrayList<>();

    public void addNewRole(AccountRole accountRole) {
        accountRoles.add(accountRole);
    }

    @Tolerate
    public AccountUser() {
    }
}
