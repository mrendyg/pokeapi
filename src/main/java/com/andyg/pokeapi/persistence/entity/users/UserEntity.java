package com.andyg.pokeapi.persistence.entity.users;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;

    @Column(name = "is_enable")
    private boolean isEnable = true; //Esta habilitado

    @Column(name = "account_No_Expired")
    private boolean accountNoExpired = true; //la cuenta no ha expirado

    @Column(name = "account_No_Locked")
    private boolean accountNoLocked = true; //la cuenta no esta bloqueada

    @Column(name = "credential_No_Expired")
    private boolean credentialNoExpired = true; //las credenciales no han expirado

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles= new HashSet<>();
    //tabla de unificacion de Usuario con sus Roles
}
