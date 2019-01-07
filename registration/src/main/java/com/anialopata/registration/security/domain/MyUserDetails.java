package com.anialopata.registration.security.domain;

/**
 * Created by Ania on 2018-11-15.
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.anialopata.registration.dto.UserDto;
import com.anialopata.registration.model.*;
import com.anialopata.registration.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {

    private long id;
    private User user;
//    private String firstName;
//    private String lastName;
    private String email;
    private static final long serialVersionUID = 1L;
    private Collection<? extends GrantedAuthority> authorities;
    private String password;
    private String username;
//    private String phoneNumber;
//    private String pesel;

    public MyUserDetails(User user) {
        this.user = user;
        this.id = user.getId();
//        this.firstName = user.getFirstName();
//        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
//        this.phoneNumber = user.getPhoneNumber();
//        this.pesel = user.getPesel();
        this.authorities = translate(user.getRoles());
    }

    private Collection<? extends GrantedAuthority> translate(List<UserRole> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRole role : roles) {
            String name = role.getName().toUpperCase();
            if (!name.startsWith("ROLE_")) {
                name = "ROLE_" + name;
            }
            authorities.add(new SimpleGrantedAuthority(name));
        }
        return authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public long getId() {
        return id;
    }

//    public String getFirstName() {
//        return firstName;
//    }
//
//    public String getPesel() {
//        return pesel;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }

    public String getEmail() {
        return email;
    }

//    public String getPhoneNumber() {
//        return phoneNumber;
//    }

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

}
