package jp.mtn.moviechat.model;

import java.io.Serializable;

public class Bookmark implements Serializable {
    private static final long serialVersionUID = 1L;

    private int bookmarkId;
    private int userId;
    private int movieId;

    public Bookmark() {}
    public Bookmark(int userId, int movieId) {
        this.userId = userId;
        this.movieId = movieId;
    }

    public int getBookmarkId() { return bookmarkId; }
    public void setBookmarkId(int bookmarkId) { this.bookmarkId = bookmarkId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getMovieId() { return movieId; }
    public void setMovieId(int movieId) { this.movieId = movieId; }
}
