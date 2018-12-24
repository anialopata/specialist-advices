package com.anialopata.registration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ania on 2018-10-18.
 */
@Getter
@Setter
@Entity
public class Patient extends User {

    @OneToMany(mappedBy = "patient",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JsonIgnore
    private transient Set<Visit> visits = new HashSet<>();

    private boolean isActive = true;

    public void addVisit(Visit visit) {
        visit.setPatient(this);
        visits.add(visit);
    }
}
