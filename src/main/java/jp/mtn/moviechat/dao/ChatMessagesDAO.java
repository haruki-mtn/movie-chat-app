package jp.mtn.moviechat.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

import jp.mtn.moviechat.model.ChatMessage;

public class ChatMessagesDAO {
    // データベース接続に使用する情報
    private final String JDBC_URL = "jdbc:mariadb://localhost:3306/movie_chat_db";
    private final String DB_USER = "movie_user";
    private final String DB_PASS = "password";

    public void create(ChatMessage chatMessage) {
        // JDBCドライバ読み込み
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
        // データベース接続
        try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

            // INSERT文を準備
            String sql = "INSERT INTO chat_messages (room_id, user_id, is_question, content) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, chatMessage.getRoomId());
            ps.setInt(2, chatMessage.getUserId());
            ps.setBoolean(3, chatMessage.getIsQuestion());
            ps.setString(4, chatMessage.getContent());

            // SELECT文を実行
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // チャット一覧取得
    public List<ChatMessage> findByRoomId(int roomId) {

        List<ChatMessage> chatList = new ArrayList<>();

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

        // JDBCドライバ読み込み
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
        // データベース接続
        try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

            // SELECT文を準備
            String sql = """
                    SELECT m.message_id, u.user_id, u.name, m.is_question, m.content, m.created_at
                    FROM chat_messages m
                    JOIN users u ON m.user_id = u.user_id
                    WHERE m.room_id = ?
                    ORDER BY m.created_at DESC
                    """;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, roomId);

            // SELECT文を実行し、結果表を取得
            ResultSet rs = ps.executeQuery();

            // チャットルームが存在しないならばnullを返す
            while (rs.next()) {
                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setMessageId(rs.getInt("message_id"));
                chatMessage.setRoomId(roomId);
                chatMessage.setUserId(rs.getInt("user_id"));
                chatMessage.setUserName(rs.getString("name"));
                chatMessage.setIsQuestion(rs.getBoolean("is_question"));
                chatMessage.setContent(rs.getString("content"));
                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
                chatMessage.setCreatedAt(createdAt);
                chatMessage.setFormattedCreatedAt(createdAt.format(fmt));
                chatList.add(chatMessage);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chatList;
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
            String sql = "SELECT COUNT(*) FROM chat_messages WHERE user_id = ?";
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
}
