package com.anialopata.registration.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SimpleVisitDto {

    private long id;

    private String date;

    private long duration;

}
