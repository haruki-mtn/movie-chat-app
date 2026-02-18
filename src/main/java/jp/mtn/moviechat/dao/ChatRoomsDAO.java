package jp.mtn.moviechat.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.mtn.moviechat.model.ChatRoom;

public class ChatRoomsDAO {
    // データベース接続に使用する情報
    private final String JDBC_URL = "jdbc:mariadb://localhost:3306/movie_chat_db";
    private final String DB_USER = "movie_user";
    private final String DB_PASS = "password";

    public ChatRoom save(int movieId) {

        ChatRoom chatRoom = null;

        // JDBCドライバ読み込み
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
        // データベース接続
        try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

            // INSERT文を準備
            String sql = "INSERT INTO chat_rooms (movie_id) VALUES (?)";
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, movieId);

            // SELECT文を実行し、
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int roomId = rs.getInt(1);
                chatRoom = new ChatRoom(roomId, movieId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return chatRoom;
    }

    public ChatRoom findByMovieId(int movieId) {

        ChatRoom chatRoom = null;

        // JDBCドライバ読み込み
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
        // データベース接続
        try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

            // SELECT文を準備
            String sql = "SELECT room_id FROM chat_rooms WHERE movie_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, movieId);

            // SELECT文を実行し、結果表を取得
            ResultSet rs = ps.executeQuery();

            // チャットルームが存在しないならばnullを返す
            if (!rs.next()) {
                return null;
            }

            int roomId = rs.getInt("room_id");
            chatRoom = new ChatRoom(roomId, movieId);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return chatRoom;
    }
}
