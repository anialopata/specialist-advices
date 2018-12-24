package com.anialopata.registration.dto;

import com.anialopata.registration.model.Gender;
import com.anialopata.registration.model.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by Ania on 2018-11-02.
 */
@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private Long id;

    @Pattern(regexp = "^[\\p{L}\\p{M}]{2,15}$")
    private String firstName;

    @Pattern(regexp = "^[\\p{L}\\p{M}]{2,15}$")
    private String lastName;

    @Pattern(message = "Nieprawidlowy adres e-mail", regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private String email;

    @Pattern(regexp = "^([0-9-+()\\s]{9,16})?$")
    private String phoneNumber;

    private Gender gender;

    @Pattern(regexp = "^[0-9]{11}$")
    private String pesel;

    private String username;

    private String password;


}
