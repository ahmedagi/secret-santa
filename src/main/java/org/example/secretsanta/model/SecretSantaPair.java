package org.example.secretsanta.model;

import org.springframework.data.annotation.Id;

public class SecretSantaPair {

    @Id
    private Long id;

    private Long giverId;
    private Long receiverId;
    private Long listId;

    public SecretSantaPair() {
    }

    public SecretSantaPair(Long id, Long giverId, Long receiverId, Long listId) {
        this.id = id;
        this.giverId = giverId;
        this.receiverId = receiverId;
        this.listId = listId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGiverId() {
        return giverId;
    }

    public void setGiverId(Long giverId) {
        this.giverId = giverId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }
}
