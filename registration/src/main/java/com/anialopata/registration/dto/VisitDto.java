package com.anialopata.registration.dto;

import com.anialopata.registration.model.Category;
import com.anialopata.registration.model.Patient;
import com.anialopata.registration.model.Specialist;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

/**
 * Created by Ania on 2018-11-11.
 */
@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VisitDto {

    private long id;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;

    private String note;

    private Category category;

    private Specialist specialist;

    private Patient patient;

    @JsonProperty("visit_url")
    private String visitUrl;

    private boolean isActive = true;

}
