package com.item.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@NoArgsConstructor
public abstract class BaseEntity {
    @Column(name = "created_dt")
    protected LocalDateTime createdDt;
    @Column(name = "updated_dt")
    protected LocalDateTime updatedDt;

    @PrePersist
    protected void create() {
        createdDt = LocalDateTime.now();
        updatedDt = LocalDateTime.now();
    }

    @PreUpdate
    protected void update() {
        updatedDt = LocalDateTime.now();
    }
}
