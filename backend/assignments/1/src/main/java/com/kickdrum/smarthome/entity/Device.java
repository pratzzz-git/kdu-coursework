package com.kickdrum.smarthome.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "devices",
        indexes = {
                @Index(name = "idx_devices_house", columnList = "house_id"),
                @Index(name = "idx_devices_room", columnList = "room_id"),
                @Index(name = "idx_devices_kickston", columnList = "kickston_id")
        }
)
public class Device extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "kickston_id", nullable = false)
    private String kickstonId;

    @Column(name = "house_id", nullable = false)
    private Long houseId;

    @Column(name = "room_id")
    private Long roomId;

    protected Device() {
        // JPA requirement
    }

    public Device(String kickstonId, Long houseId, Long roomId) {
        this.kickstonId = kickstonId;
        this.houseId = houseId;
        this.roomId = roomId;
    }

    public Long getId() {
        return id;
    }

    public String getKickstonId() {
        return kickstonId;
    }

    public Long getHouseId() {
        return houseId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void moveToRoom(Long roomId) {
        this.roomId = roomId;
    }
}
