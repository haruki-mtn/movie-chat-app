package jp.mtn.moviechat.model;

import java.util.List;

import jp.mtn.moviechat.dao.BookmarksDAO;

public class BookmarkLogic {
    public boolean add(int userId, int movieId) {
        BookmarksDAO dao = new BookmarksDAO();
        if (dao.exists(userId, movieId)) {
            return false;
        }
        Bookmark bookmark = new Bookmark(userId, movieId);
        return dao.create(bookmark);
    }

    public boolean remove(int userId, int movieId) {
        BookmarksDAO dao = new BookmarksDAO();
        return dao.delete(userId, movieId);
    }

    public boolean multipleRemove(int userId, List<Integer> movieIdList) {
        BookmarksDAO dao = new BookmarksDAO();
        return dao.multipleDelete(userId, movieIdList);
    }

    public List<Integer> getMovieList(int userId) {
        BookmarksDAO dao = new BookmarksDAO();
        return dao.findByUserId(userId);
    }

    public int getBookmarkCount(int userId) {
        BookmarksDAO dao = new BookmarksDAO();
        return dao.countByUserId(userId);
    }

    public boolean isBookmarked(int userId, int movieId) {
        BookmarksDAO dao = new BookmarksDAO();
        return dao.exists(userId, movieId);
    }
}
