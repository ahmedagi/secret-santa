package org.example.secretsanta.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("list")
public class SecretSantaList {

    @Id
    private Long id;
    private LocalDateTime createdAt ;

    public SecretSantaList() {
    }

    public SecretSantaList(Long id, LocalDateTime dateTime) {
        this.id = id;
        this.createdAt = dateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
