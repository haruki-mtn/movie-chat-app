package jp.mtn.moviechat.model;

import java.util.List;

import jp.mtn.moviechat.dao.BookmarksDAO;

public class BookmarkLogic {
    public boolean addBookmark(int userId, int movieId) {
        BookmarksDAO dao = new BookmarksDAO();
        if (dao.existsByUserIdAndMovieId(userId, movieId)) {
            return false;
        }
        Bookmark bookmark = new Bookmark(userId, movieId);
        return dao.save(bookmark);
    }

    public boolean removeBookmark(int userId, int movieId) {
        BookmarksDAO dao = new BookmarksDAO();
        return dao.deleteByUserIdAndMoiveId(userId, movieId);
    }

    public boolean removeBookmarks(int userId, List<Integer> movieIdList) {
        BookmarksDAO dao = new BookmarksDAO();
        return dao.deleteAllByUserIdAndMovieIdList(userId, movieIdList);
    }

    public List<Integer> getBookmarkedMovieIds(int userId) {
        BookmarksDAO dao = new BookmarksDAO();
        return dao.findByUserId(userId);
    }

    public int countBookmarks(int userId) {
        BookmarksDAO dao = new BookmarksDAO();
        return dao.countByUserId(userId);
    }

    public boolean isBookmarked(int userId, int movieId) {
        BookmarksDAO dao = new BookmarksDAO();
        return dao.existsByUserIdAndMovieId(userId, movieId);
    }
}
