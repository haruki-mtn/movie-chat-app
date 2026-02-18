package jp.mtn.moviechat.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.mtn.moviechat.model.BookmarkLogic;
import jp.mtn.moviechat.model.MovieLogic;
import jp.mtn.moviechat.model.User;

@WebServlet("/app/bookmark")
public class BookmarkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションスコープからログインユーザー（Userオブジェクトのインスタンス）を取得
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");

		// ブックマークされた映画IDをリストで取得
		BookmarkLogic bo = new BookmarkLogic();
		List<Integer> movieIdList = bo.getBookmarkedMovieIds(user.getUserId());

		// リクエストスコープにブックマークされた映画IDリストを保存
		request.setAttribute("movieIdList", movieIdList);

        // リクエストスコープにヘッダータイトルとアクティブなナビゲーションを保存
		request.setAttribute("headerTitle", "ブックマーク");
		request.setAttribute("activeNav", "bookmark");

		// bookmark.jspへフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/bookmark.jsp");
        dispatcher.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションスコープからログインユーザー（Userオブジェクトのインスタンス）を取得
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");

		// リクエストパラメーター（addかremoveかmultipleRemoveか）を取得
		String action  = request.getParameter("action");

		// 映画詳細のブックマークボタンからの追加の場合の処理
		if ("add".equals(action)) {
			// リクエストパラメーターを取得
			String movieIdStr = request.getParameter("movieId");
			String movieTitle = request.getParameter("movieTitle");

			// String → int
			int movieId = Integer.parseInt(movieIdStr);

			// moviesテーブルを保証
			MovieLogic movieLogic = new MovieLogic();
			movieLogic.findOrCreateMovie(movieId, movieTitle);

			// ブックマーク追加処理の実行
			BookmarkLogic bookmarkLogic = new BookmarkLogic();
			bookmarkLogic.addBookmark(user.getUserId(), movieId);
			return;

		// 映画詳細のブックマークボタンからの削除の場合の処理
		} else if ("remove".equals(action)) {
			// リクエストパラメーターを取得
			String movieIdStr = request.getParameter("movieId");

			// String → int
			int movieId = Integer.parseInt(movieIdStr);

			// ブックマーク削除処理の実行
			BookmarkLogic bo = new BookmarkLogic();
			bo.removeBookmark(user.getUserId(), movieId);
			return;

		// ブックマークページからの一括削除の場合の処理
		} else if ("multipleRemove".equals(action)) {
			// リクエストパラメーターを取得
			String[] movieIdStrs = request.getParameterValues("movieId");

			// 入力チェック
			if (movieIdStrs == null || movieIdStrs.length == 0) {
				response.sendRedirect(request.getContextPath() + "/app/bookmark");
				return;
			}

			List<Integer> movieIdList = new ArrayList<>();
			for(String movieIdStr : movieIdStrs) {
				// String → int
				int movieId = Integer.parseInt(movieIdStr);
				movieIdList.add(movieId);
			}

			// ブックマーク一括削除処理の実行
			BookmarkLogic bo = new BookmarkLogic();
			bo.removeBookmarks(user.getUserId(), movieIdList);
		}


		// BookmarkServletにリダイレクト
		response.sendRedirect(request.getContextPath() + "/app/bookmark");
		return;
	}
}
