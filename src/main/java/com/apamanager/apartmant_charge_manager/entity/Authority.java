package com.apamanager.apartmant_charge_manager.entity;

import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "authorities")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id")
    private Integer id;

    @Column(name = "authority", unique = true, nullable = false)
    private String authority;

    @Override
    public @Nullable String getAuthority() {
        return authority;
    }
}
