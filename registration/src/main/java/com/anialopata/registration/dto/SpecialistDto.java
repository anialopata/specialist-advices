package com.anialopata.registration.dto;

import com.anialopata.registration.model.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by Ania on 2018-11-02.
 */
//@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpecialistDto extends UserDto {

    @NotNull
    private String degree;

    private Category category;

    @Lob
    private String description;

    @JsonIgnore
    private Set<VisitDto> visits;

    @JsonProperty("specialist_url")
    private String specialistUrl;
}
