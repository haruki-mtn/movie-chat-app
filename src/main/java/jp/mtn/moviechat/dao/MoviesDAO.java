package jp.mtn.moviechat.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.mtn.moviechat.model.Movie;

public class MoviesDAO {
    // データベース接続に使用する情報
    private final String JDBC_URL = "jdbc:mariadb://localhost:3306/movie_chat_db";
    private final String DB_USER = "movie_user";
    private final String DB_PASS = "password";

    public Movie save(int movieId, String movieTitle) {

        Movie movie = null;

        // JDBCドライバ読み込み
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
        // データベース接続
        try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

            // INSERT文を準備
            String sql = "INSERT INTO movies (movie_id, title) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, movieId);
            ps.setString(2, movieTitle);

            // SELECT文を実行し、
            ps.executeUpdate();
            movie = new Movie(movieId, movieTitle);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return movie;
    }

    public Movie findByMovieId(int movieId) {

        Movie movie = null;

        // JDBCドライバ読み込み
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
        // データベース接続
        try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

            // SELECT文を準備
            String sql = "SELECT movie_id, title FROM movies WHERE movie_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, movieId);

            // SELECT文を実行し、結果表を取得
            ResultSet rs = ps.executeQuery();

            // 映画が存在しないならばnullを返す
            if (!rs.next()) {
                return null;
            }

            int id = rs.getInt("movie_id");
            String title = rs.getString("title");
            movie = new Movie(id, title);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return movie;
    }
}
