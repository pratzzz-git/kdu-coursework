package com.kdu.hospital.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "shift_type")
@Data
@NoArgsConstructor
public class ShiftType {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String description;

    private Boolean active;

    @Column(name = "tenant_id", nullable = false)
    private String tenantId;

    @OneToMany(mappedBy = "shiftType")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private List<Shift> shifts;

}
