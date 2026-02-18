package jp.mtn.moviechat.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import jp.mtn.moviechat.model.Bookmark;

public class BookmarksDAO {
    // データベース接続に使用する情報
    private final String JDBC_URL = "jdbc:mariadb://localhost:3306/movie_chat_db";
    private final String DB_USER = "movie_user";
    private final String DB_PASS = "password";

    public boolean save(Bookmark bookmark) {
        // JDBCドライバ読み込み
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
        // データベース接続
        try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

            // INSERT文を準備
            String sql = "INSERT INTO bookmarks (user_id, movie_id) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, bookmark.getUserId());
            ps.setInt(2, bookmark.getMovieId());

            // SELECT文を実行し、
            int result = ps.executeUpdate();
            return result == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteByUserIdAndMoiveId(int userId, int movieId) {
        // JDBCドライバ読み込み
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
        // データベース接続
        try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

            // DELETE文を準備
            String sql = "DELETE FROM bookmarks WHERE user_id = ? AND movie_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, movieId);

            // SELECT文を実行し、
            int result = ps.executeUpdate();
            return result == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAllByUserIdAndMovieIdList(int userId, List<Integer> movieIdList) {
        // JDBCドライバ読み込み
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
        // データベース接続
        try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

            // DELETE文を準備
            String sql = "DELETE FROM bookmarks WHERE user_id = ? AND movie_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            for (int movieId : movieIdList) {
                ps.setInt(1, userId);
                ps.setInt(2, movieId);
                ps.executeUpdate();
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Integer> findByUserId(int userId) {

        List<Integer> movieIdList = new ArrayList<>();

        // JDBCドライバ読み込み
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
        // データベース接続
        try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

            // SELECT文を準備
            String sql = "SELECT movie_id FROM bookmarks WHERE user_id = ? ORDER BY created_at DESC";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);

            // SELECT文を実行し、結果表を取得
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                movieIdList.add(rs.getInt("movie_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movieIdList;
    }

    public int countByUserId(int userId) {

        int count = 0;

        // JDBCドライバ読み込み
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
        // データベース接続
        try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

            // SELECT文を準備
            String sql = "SELECT COUNT(*) FROM bookmarks WHERE user_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);

            // SELECT文を実行し、結果表を取得
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    public boolean existsByUserIdAndMovieId(int userId, int movieId) {
        // JDBCドライバ読み込み
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
        // データベース接続
        try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

            // DELETE文を準備
            String sql = "SELECT 1 FROM bookmarks WHERE user_id = ? AND movie_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, movieId);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
