package com.kickdrum.smarthome.entity;

import com.kickdrum.smarthome.util.Role;
import jakarta.persistence.*;

@Entity
@Table(
        name = "house_users",
        indexes = {
                @Index(name = "idx_house_user_house", columnList = "house_id"),
                @Index(name = "idx_house_user_user", columnList = "user_id")
        }
)
public class HouseUser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "house_id", nullable = false)
    private Long houseId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    protected HouseUser() {
        // JPA requirement
    }

    public HouseUser(Long houseId, Long userId, Role role) {
        this.houseId = houseId;
        this.userId = userId;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public Long getHouseId() {
        return houseId;
    }

    public Long getUserId() {
        return userId;
    }

    public Role getRole() {
        return role;
    }

    public void changeRole(Role role) {
        this.role = role;
    }
}
