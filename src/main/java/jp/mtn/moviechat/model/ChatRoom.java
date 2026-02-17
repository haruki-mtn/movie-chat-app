package jp.mtn.moviechat.model;

import java.io.Serializable;

public class ChatRoom implements Serializable {
    private static final long serialVersionUID = 1L;

    private int roomId;
    private int movieId;

    public ChatRoom() {}
    public ChatRoom(int roomId, int movieId) {
        this.roomId = roomId;
        this.movieId = movieId;
    }

    public int getRoomId() { return roomId; }
    public void setRoomId(int roomId) { this.roomId = roomId; }

    public int getMovieId() { return movieId; }
    public void setMovieId(int movieId) { this.movieId = movieId; }
}
