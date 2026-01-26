package com.kickdrum.smarthome.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "rooms",
        indexes = {
                @Index(name = "idx_rooms_house", columnList = "house_id")
        }
)
public class Room extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "house_id", nullable = false)
    private Long houseId;

    @Column(name = "name", nullable = false)
    private String name;

    protected Room() {
        // JPA requirement
    }

    public Room(Long houseId, String name) {
        this.houseId = houseId;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Long getHouseId() {
        return houseId;
    }

    public String getName() {
        return name;
    }

    public void rename(String name) {
        this.name = name;
    }
}
