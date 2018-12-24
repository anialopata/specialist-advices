package com.anialopata.registration.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;
import javax.validation.constraints.Pattern;
import java.util.Set;

/**
 * Created by Ania on 2018-11-28.
 */
@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDto {

    private long id;

    @Pattern(regexp = "^[\\p{L}\\p{M}]{2,50}$")
    private String name;

    @Lob
    private String description;

    private Set<SpecialistDto> specialists;

    @JsonProperty("category_url")
    private String categoryUrl;


    private boolean isActive = true;
}
