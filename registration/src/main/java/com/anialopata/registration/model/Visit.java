package com.anialopata.registration.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by Ania on 2018-10-18.
 */
@Getter
@Setter
@Entity
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime date;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private String note;

    @ManyToOne
            (cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH})
    @JoinColumn(name="category_id")
    private Category category;

    @ManyToOne
            (cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH})
    @JoinColumn(name="specialist_id")
    private Specialist specialist;

    @ManyToOne
            (cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH})
    @JoinColumn(name="patient_id")
    private Patient patient;

    private boolean isActive = true;

}

