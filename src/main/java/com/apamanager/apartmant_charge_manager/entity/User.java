package com.apamanager.apartmant_charge_manager.entity;

import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "user_name", unique = true, nullable = false)
    private String username;

    @Column(name = "user_password", nullable = true)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    private Building building;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        var authorities = new HashSet<SimpleGrantedAuthority>();

        for (var r : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + r.getRole()));
            for (var a : r.getAuthorities()) {
                authorities.add(new SimpleGrantedAuthority(a.getAuthority()));
            }
        }

        return authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public @Nullable
    String getPassword() {
        return password;
    }

}
