package com.java.aqua.model;


import static jakarta.persistence.GenerationType.UUID;
import static jakarta.persistence.EnumType.STRING;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.java.aqua.Enumerator.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="a_user")
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = UUID)
    private String id;
    private String name;
    private String email;
    private String password;
    
    @Enumerated(STRING)
    @Builder.Default
    private Role role = Role.USER;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;   
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;    
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername(){
        return email;
    }   
    
}