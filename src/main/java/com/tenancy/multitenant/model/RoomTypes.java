package com.tenancy.multitenant.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Setter
@Getter
@Entity
@Table(name = "room_types")
public class RoomTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", length = 20, nullable = false, unique = true)
    private String type;

    @Column (name = "guests_count", nullable = false)
    private Integer guestsCount;

    @Column(name = "is_active", nullable = false)
    @ColumnDefault("1")
    private Boolean isActive;
}
