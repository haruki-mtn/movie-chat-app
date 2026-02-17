package jp.mtn.moviechat.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.mtn.moviechat.model.BookmarkLogic;
import jp.mtn.moviechat.model.User;

@WebServlet("/app/bookmark/status")
public class BookmarkStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションスコープからログインユーザー（Userオブジェクトのインスタンス）を取得
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");

        // リクエストパラメーターを取得
        String movieIdStr = request.getParameter("movieId");

        // String → int
        int movieId = Integer.parseInt(movieIdStr);

        // ブックマークされているかどうかを確認する処理を実行
        BookmarkLogic bo = new BookmarkLogic();
        boolean bookmarked = bo.isBookmarked(user.getUserId(), movieId);

        response.setContentType("application/json");
        response.getWriter().write("{\"bookmarked\": " + bookmarked + "}");
	}
}
