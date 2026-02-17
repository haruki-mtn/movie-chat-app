package jp.mtn.moviechat.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import jp.mtn.moviechat.model.ChatLogic;
import jp.mtn.moviechat.model.ChatMessage;
import jp.mtn.moviechat.model.ChatRoom;
import jp.mtn.moviechat.model.Movie;
import jp.mtn.moviechat.model.MovieLogic;
import jp.mtn.moviechat.model.User;

@WebServlet("/app/chat")
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションスコープからChatRoomオブジェクトのインスタンスを取得
		HttpSession session = request.getSession();
		ChatRoom chatRoom = (ChatRoom) session.getAttribute("chatRoom");

		// セッションスコープにchatRoomが存在するならばチャットリストを取得してリクエストスコープに保存
		if (chatRoom != null) {
			ChatLogic bo = new ChatLogic();
			List<ChatMessage> chatMessages = bo.getMessages(chatRoom.getRoomId());
			request.setAttribute("chatMessages", chatMessages);
		}

		// セッションスコープにflashMsgが存在するならば取得してリクエストスコープに保存
		if (session.getAttribute("flashMsg") != null) {
			request.setAttribute("flashMsg", session.getAttribute("flashMsg"));
			request.setAttribute("flashType", session.getAttribute("flashType"));

			// セッションスコープからflashを削除
			session.removeAttribute("flashMsg");
			session.removeAttribute("flashType");
		}

		// リクエストスコープにヘッダータイトルとアクティブなナビゲーションを保存
		request.setAttribute("headerTitle", "チャット");
		request.setAttribute("activeNav", "chat");

		// chat.jspへフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/chat.jsp");
        dispatcher.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメーター（postChatRoomかpostChatContentか）を取得
		String action = request.getParameter("action");

		HttpSession session = request.getSession();

		// 「チャットを開く」からチャットルームを開く場合の処理
		if ("postChatRoom".equals(action)) {
			// リクエストパラメーターを取得
			String movieIdStr = request.getParameter("movieId");
			String movieTitle = request.getParameter("movieTitle");

			// String → int
			int movieId = Integer.parseInt(movieIdStr);

			// 映画登録処理の実行
			MovieLogic movieLogic = new MovieLogic();
			Movie movie = movieLogic.findOrCreate(movieId, movieTitle);

			// セッションスコープにMovieオブジェクトのインスタンスを保存
			session.setAttribute("movie", movie);

			// チャットルーム処理の実行
			ChatLogic chatLogic = new ChatLogic();
			ChatRoom chatRoom = chatLogic.findOrCreate(movie.getMovieId());

			// セッションスコープにChatRoomオブジェクトのインスタンスを保存
			session.setAttribute("chatRoom", chatRoom);

		// チャットを送信した場合の処理
		} else if ("postChatContent".equals(action)) {
			// セッションスコープからログインユーザーとChatRoomオブジェクトのインスタンスを取得
			User loginUser = (User) session.getAttribute("loginUser");
			ChatRoom chatRoom = (ChatRoom) session.getAttribute("chatRoom");

			// リクエストパラメーターを取得
			String isQuestionStr = request.getParameter("isQuestion");
			String chatContent = request.getParameter("chatContent");

			// String → boolean
			boolean isQuestion = "true".equals(isQuestionStr);

			// 入力チェック
			if (chatContent == null || chatContent.isEmpty()) {
				// セッションスコープにフラッシュメッセージを保存
				session.setAttribute("flashMsg", "チャットが未入力です");
				session.setAttribute("flashType", "error");
				// ChatServletにリダイレクト
				response.sendRedirect(request.getContextPath() + "/app/chat");
				return;
			}

			// 投稿処理の実行
			ChatMessage chatMessage = new ChatMessage(chatRoom.getRoomId(), loginUser.getUserId(), loginUser.getName(), isQuestion, chatContent);
			ChatLogic bo = new ChatLogic();
			bo.postMessage(chatMessage);

		}

		// ChatServletにリダイレクト
		response.sendRedirect(request.getContextPath() + "/app/chat");
		return;
	}
}
