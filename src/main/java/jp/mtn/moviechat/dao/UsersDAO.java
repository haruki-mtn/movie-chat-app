package jp.mtn.moviechat.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import jp.mtn.moviechat.model.Login;
import jp.mtn.moviechat.model.Register;
import jp.mtn.moviechat.model.User;

public class UsersDAO {
    // データベース接続に使用する情報
    private final String JDBC_URL = "jdbc:mariadb://localhost:3306/movie_chat_db";
    private final String DB_USER = "movie_user";
    private final String DB_PASS = "password";

    public boolean save(Register register) {
        // JDBCドライバ読み込み
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
        // データベース接続
        try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

            // INSERT文を準備
            String sql = "INSERT INTO users (name, mail, password_hash) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            // bcryptでハッシュ化
            String hashPass = BCrypt.hashpw(register.getPass(), BCrypt.gensalt());
            ps.setString(1, register.getName());
            ps.setString(2, register.getMail());
            ps.setString(3, hashPass);

            int result = ps.executeUpdate();
            return result == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User findByMail(Login login) {

        User user = null;

        // JDBCドライバ読み込み
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
        // データベース接続
        try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

            // SELECT文を準備
            String sql = "SELECT user_id, name, mail, password_hash FROM users WHERE mail = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, login.getMail());

            // SELECT文を実行し、結果表を取得
            ResultSet rs = ps.executeQuery();

            // ユーザーが存在しないならばnullを返す
            if (!rs.next()) {
                return null;
            }

            String hashPass = rs.getString("password_hash");

            // パスワードハッシュが一致しないならばnullを返す
            if (!BCrypt.checkpw(login.getPass(), hashPass)) {
                return null;
            }

            int userId = rs.getInt("user_id");
            String name = rs.getString("name");
            String mail = rs.getString("mail");
            user = new User(userId, name, mail);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return user;
    }

    public boolean deleteByUserId(int userId) {
        // JDBCドライバ読み込み
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
        // データベース接続
        try (Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

            // DELETE文を準備（まず、chat_messagesテーブルから該当ユーザーの投稿を削除）
            String sql1 = "DELETE FROM bookmarks WHERE User_id = ?";
            String sql2 = "DELETE FROM chat_messages WHERE user_id = ?";
            String sql3 = "DELETE FROM users WHERE user_id = ?";
            PreparedStatement ps1 = con.prepareStatement(sql1);
            PreparedStatement ps2 = con.prepareStatement(sql2);
            PreparedStatement ps3 = con.prepareStatement(sql3);

            ps1.setInt(1, userId);
            ps2.setInt(1, userId);
            ps3.setInt(1, userId);

            ps1.executeUpdate();
            ps2.executeUpdate();
            int result = ps3.executeUpdate();
            return result == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
