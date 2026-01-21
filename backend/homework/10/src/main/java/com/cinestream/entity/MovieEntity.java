package com.cinestream.entity;

import javax.persistence.*;

@Entity
@Table(name = "movies")
public class MovieEntity {

    @Id
    private Long id;

    private String title;
    private String genre;

    private Long directorId;

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public Long getDirectorId() { return directorId; }
}
