package org.example.secretsanta.model;

public class SecretSantaPairDTO {

    private Long giverId;
    private String giverName;
    private Long receiverId;
    private String receiverName;

    public SecretSantaPairDTO() {
    }

    public SecretSantaPairDTO(Long giverId, String giverName, Long receiverId, String receiverName) {
        this.giverId = giverId;
        this.giverName = giverName;
        this.receiverId = receiverId;
        this.receiverName = receiverName;
    }

    public Long getGiverId() {
        return giverId;
    }

    public void setGiverId(Long giverId) {
        this.giverId = giverId;
    }

    public String getGiverName() {
        return giverName;
    }

    public void setGiverName(String giverName) {
        this.giverName = giverName;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
}
