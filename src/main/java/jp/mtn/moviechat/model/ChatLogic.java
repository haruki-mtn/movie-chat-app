package jp.mtn.moviechat.model;

import java.util.List;

import jp.mtn.moviechat.dao.ChatMessagesDAO;
import jp.mtn.moviechat.dao.ChatRoomsDAO;

public class ChatLogic {
    public ChatRoom findOrCreate(int movieId) {
        ChatRoomsDAO dao = new ChatRoomsDAO();
        ChatRoom chatRoom = dao.findByMovieId(movieId);
        // チャットルームが存在しないならばcreate
        if (chatRoom == null) {
            chatRoom = dao.create(movieId);
        }
        return chatRoom;
    }

    public void postMessage(ChatMessage chatMessage) {
        ChatMessagesDAO dao = new ChatMessagesDAO();
        dao.create(chatMessage);
    }

    public List<ChatMessage> getMessages(int roomId) {
        ChatMessagesDAO dao = new ChatMessagesDAO();
        return dao.findByRoomId(roomId);
    }

    public int getMessagesCount(int userId) {
        ChatMessagesDAO dao = new ChatMessagesDAO();
        return dao.countByUserId(userId);
    }
}
