package com.artur.orange_backend.model;

import com.artur.orange_backend.model.utils.user.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "user_table")
public class User implements UserDetails {
    @Id
    @SequenceGenerator(
            name = "user_id",
            sequenceName = "user_id",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "user_id",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    private String email;
    private String password;
    private String name;
    private byte[] profileImg;

    @Enumerated(EnumType.STRING)
    private UserRole userRole; // TODO: ADD AUTHORITIES
    private Boolean enabled = false;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Order> orders;

    public void addOrder(Order order){
        this.orders.add(order);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.getName());

        return Collections.singleton(authority);
    }

    @Override
    public String getUsername() {
        return email;
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
        return enabled;
    }
}

