package com.anialopata.registration.dto;

import com.anialopata.registration.model.Gender;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private boolean isActive = true;


}
