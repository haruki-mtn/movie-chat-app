package jp.mtn.moviechat.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.mtn.moviechat.model.BookmarkLogic;
import jp.mtn.moviechat.model.ChatLogic;
import jp.mtn.moviechat.model.User;

@WebServlet("/app/account")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションスコープからログインユーザー（Userオブジェクトのインスタンス）を取得
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");

		// ブックマーク数を取得
		BookmarkLogic bookmarkLogic = new BookmarkLogic();
		int bookmarkCount = bookmarkLogic.countBookmarks(user.getUserId());

		// 総投稿数を取得
		ChatLogic chatLogic = new ChatLogic();
		int messagesCount = chatLogic.countMessages(user.getUserId());

		// リクエストスコープにブックマーク数と総投稿数を保存
		request.setAttribute("bookmarkCount", bookmarkCount);
		request.setAttribute("messagesCount", messagesCount);

		// リクエストスコープにヘッダータイトルとアクティブなナビゲーションを保存
		request.setAttribute("headerTitle", "アカウント");
		request.setAttribute("activeNav", "account");

		// リクエストスコープにモーダルメッセージとモーダルラベルを保存
		request.setAttribute("modalMsg", "ログアウトしますか？");
		request.setAttribute("modalLabel", "ログアウト");

		// account.jspへフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/account.jsp");
        dispatcher.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
	}
}
