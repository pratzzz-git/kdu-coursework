package com.cinestream.entity;

import javax.persistence.*;

@Entity
@Table(name = "directors")
public class DirectorEntity {

    @Id
    private Long id;

    private String name;
    private Integer totalAwards;

    // getters only (no setters for now)
    public Long getId() { return id; }
    public String getName() { return name; }
    public Integer getTotalAwards() { return totalAwards; }
}
