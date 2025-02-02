package org.example.secretsanta.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class SecretSantaList {

    @Id
    private Long id;
    private LocalDateTime dateTime;

    public SecretSantaList() {
    }

    public SecretSantaList(Long id, LocalDateTime dateTime) {
        this.id = id;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
