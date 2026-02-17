package jp.mtn.moviechat.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ChatMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private int messageId;
    private int roomId;
    private int userId;
    private String userName;
    private boolean isQuestion;
    private String content;
    private LocalDateTime createdAt;
    private String formattedCreatedAt;


    public ChatMessage() {}
    public ChatMessage(int roomId, int userId, String userName, boolean isQuestion, String content) {
        this.roomId = roomId;
        this.userId = userId;
        this.userName = userName;
        this.isQuestion = isQuestion;
        this.content = content;
    }

    public int getMessageId() { return messageId; }
    public void setMessageId(int messageId) { this.messageId = messageId; }

    public int getRoomId() { return roomId; }
    public void setRoomId(int roomId) { this.roomId = roomId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public boolean getIsQuestion() { return isQuestion; }
    public void setIsQuestion(boolean isQuestion) { this.isQuestion = isQuestion; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getFormattedCreatedAt() { return formattedCreatedAt; }
    public void setFormattedCreatedAt(String formattedCreatedAt) { this.formattedCreatedAt = formattedCreatedAt; }

}
