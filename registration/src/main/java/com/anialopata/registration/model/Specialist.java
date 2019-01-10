package com.anialopata.registration.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ania on 2018-10-18.
 */
@Getter
@Setter
@Entity
public class Specialist extends User {

    private String degree;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private String description;

    @OneToMany(mappedBy = "specialist",
            cascade = {CascadeType.PERSIST,
                    CascadeType.REMOVE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH})
    @JsonIgnore
    private Set<Visit> visits = new HashSet<>();

    @ManyToOne
            (cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH})
    @JoinColumn(name="category_id")
    private Category category;

    public void addVisit(Visit visit) {
        visit.setSpecialist(this);
        visits.add(visit);
    }

    @JsonCreator
    public Specialist() {
    }
}
