package com.anialopata.registration.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ania on 2018-11-28.
 */
@Getter
@Setter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private String description;

    @OneToMany(mappedBy = "category",
            cascade = {CascadeType.PERSIST,
                        CascadeType.MERGE,
                        CascadeType.REFRESH})
    @JsonIgnore
    private transient Set<Visit> visits = new HashSet<>();

    @OneToMany(mappedBy = "category",
            cascade = {CascadeType.ALL})
    @JsonIgnore
    private Set<Specialist> specialists = new HashSet<>();

    private boolean isActive = true;

    @JsonCreator
    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public void addSpecialist(Specialist specialist) {
        specialist.setCategory(this);
        specialists.add(specialist);
    }
}
